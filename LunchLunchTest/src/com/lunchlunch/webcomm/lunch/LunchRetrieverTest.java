package com.lunchlunch.webcomm.lunch;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import android.test.UiThreadTest;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.webcomm.MockExplodingClient;
import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;
import com.lunchlunch.webcomm.person.MockPersonParser;

public class LunchRetrieverTest extends LunchBuddyTestCase {

	private CountDownLatch countdown;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		countdown = new CountDownLatch(1);
	}

	@Override
	protected void tearDown() throws Exception {
		countdown = null;
		super.tearDown();
	}

	public void testImplementsInterface() throws Exception {
		assertEquals(LunchRetrieverInterface.class,
				LunchRetriever.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArguments() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchRetriever lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		assertEquals(httpClientBuilder, lunchHelper.getHttpClientBuilder());
		assertEquals(personParser, lunchHelper.getPersonParser());
		assertEquals(lunchParser, lunchHelper.getLunchParser());
	}

	@UiThreadTest
	public void testGetLunchesWillCallGetLunchesURLWithJSONSerializedPerson()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();

		JSONObject jsonToReturn = new JSONObject("{some:stuff,goes:here}");
		personParser.setJsonToReturn(jsonToReturn);
		
		JSONObject expectedJSON = new JSONObject();
		expectedJSON.put("person", jsonToReturn);
		LunchRetrieverInterface lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockPerson person = new MockPerson();
		lunchHelper.getLunches(person, new MockLunchReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(person, personParser.getPersonForBuildJSON());

		HttpPost httpPost = assertIsOfTypeAndGet(HttpPost.class,
				httpClientToReturn.getRequestPassedToExecute());
		assertEquals(new URI(com.lunchlunch.LunchBuddyConstants.SERVICE_URL
				+ "/getLunches"), httpPost.getURI());
		InputStream content = httpPost.getEntity().getContent();
		byte[] entityContents = new byte[content.available()];
		content.read(entityContents);
		assertEquals(expectedJSON.toString(), new String(entityContents));
		assertEquals("application/json",
				httpPost.getHeaders("Accept")[0].getValue());
		assertEquals("application/json",
				httpPost.getHeaders("Content-type")[0].getValue());
		
	}

	@UiThreadTest
	public void testGetLunchesWillConvertDataFromTheResponseToJSONAndPassItToTheLunchParser()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchRetrieverInterface lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "[{this:should}, {look:like}, {json:somewhat}]";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);
		lunchHelper.getLunches(new MockPerson(), new MockLunchReceiver(
				countdown));
		countdown.await(10, TimeUnit.SECONDS);

		JSONArray lunchJSONPassedIn = lunchParser.getLunchJSONPassedIn();
		assertEquals(3, lunchJSONPassedIn.length());
		assertEquals("should",
				lunchJSONPassedIn.getJSONObject(0).getString("this"));
		assertEquals("like",
				lunchJSONPassedIn.getJSONObject(1).getString("look"));
		assertEquals("somewhat",
				lunchJSONPassedIn.getJSONObject(2).getString("json"));
	}

	@UiThreadTest
	public void testGetLunchesWillPassTheLunchesFromTheLunchParserToTheRetriever()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();
		List<LunchInterface> expectedLunches = new ArrayList<LunchInterface>();
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());

		lunchParser.setLunchesToReturn(expectedLunches);

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchRetrieverInterface lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "[{this:should}, {look:like}, {json:somewhat}]";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);
		MockLunchReceiver lunchReceiver = new MockLunchReceiver(countdown);

		lunchHelper.getLunches(new MockPerson(), lunchReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(expectedLunches, lunchReceiver.getLunchesReceived());

	}

	@UiThreadTest
	public void testExplodingJSONReturnsAnEmptyList() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchRetrieverInterface lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "kablammo";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);

		MockLunchReceiver lunchReceiver = new MockLunchReceiver(countdown);

		lunchHelper.getLunches(new MockPerson(), lunchReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(0, lunchReceiver.getLunchesReceived().size());

	}

	@UiThreadTest
	public void testExplodingRequestReturnAnEmptyListOfLunches()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		httpClientBuilder.setHttpClientToReturn(new MockExplodingClient());
		MockLunchParser lunchParser = new MockLunchParser();

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchRetrieverInterface lunchHelper = new LunchRetriever(httpClientBuilder,
				personParser, lunchParser);

		MockLunchReceiver lunchReceiver = new MockLunchReceiver(countdown);
		lunchHelper.getLunches(new MockPerson(), lunchReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(0, lunchReceiver.getLunchesReceived().size());
	}

}

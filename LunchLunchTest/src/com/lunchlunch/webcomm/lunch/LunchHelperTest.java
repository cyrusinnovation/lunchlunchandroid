package com.lunchlunch.webcomm.lunch;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpGet;
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
import com.lunchlunch.webcomm.MockHttpEntity;
import com.lunchlunch.webcomm.MockHttpResponse;
import com.lunchlunch.webcomm.person.MockPersonParser;

public class LunchHelperTest extends LunchBuddyTestCase {

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
		assertEquals(LunchHelperInterface.class,
				LunchHelper.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArguments() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchHelper lunchHelper = new LunchHelper(httpClientBuilder,
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
		LunchHelperInterface lunchHelper = new LunchHelper(httpClientBuilder,
				personParser, lunchParser);

		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockPerson person = new MockPerson();
		lunchHelper.getLunches(person, new MockLunchReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(person, personParser.getPersonForBuildJSON());

		HttpGet httpGet = assertIsOfTypeAndGet(HttpGet.class,
				httpClientToReturn.getRequestPassedToExecute());
		String encodedPerson = URLEncoder.encode(jsonToReturn.toString(),
				"UTF-8");
		assertEquals(new URI(com.lunchlunch.LunchBuddyConstants.SERVICE_URL
				+ "/getLunches?person=" + encodedPerson), httpGet.getURI());
	}

	@UiThreadTest
	public void testGetLunchesWillConvertDataFromTheResponseToJSONAndPassItToTheLunchParser()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		MockLunchParser lunchParser = new MockLunchParser();

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchHelperInterface lunchHelper = new LunchHelper(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "[{this:should}, {look:like}, {json:somewhat}]";
		setResponseContentsToReturn(httpClientBuilder, responseContents);
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
		LunchHelperInterface lunchHelper = new LunchHelper(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "[{this:should}, {look:like}, {json:somewhat}]";
		setResponseContentsToReturn(httpClientBuilder, responseContents);
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
		List<LunchInterface> expectedLunches = new ArrayList<LunchInterface>();
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());

		lunchParser.setLunchesToReturn(expectedLunches);

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchHelperInterface lunchHelper = new LunchHelper(httpClientBuilder,
				personParser, lunchParser);

		String responseContents = "kablammo";
		setResponseContentsToReturn(httpClientBuilder, responseContents);

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
		List<LunchInterface> expectedLunches = new ArrayList<LunchInterface>();
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());
		expectedLunches.add(new MockLunch());

		lunchParser.setLunchesToReturn(expectedLunches);

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		LunchHelperInterface lunchHelper = new LunchHelper(httpClientBuilder,
				personParser, lunchParser);

		MockLunchReceiver lunchReceiver = new MockLunchReceiver(countdown);
		lunchHelper.getLunches(new MockPerson(), lunchReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(0, lunchReceiver.getLunchesReceived().size());
	}

	private void setResponseContentsToReturn(
			MockHttpClientBuilder httpClientBuilder, String responseContents) {
		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockHttpResponse responseToReturnFromExecute = new MockHttpResponse();
		MockHttpEntity entityFromResponse = new MockHttpEntity();
		responseToReturnFromExecute.setEntity(entityFromResponse);
		httpClientToReturn
				.setResponseToReturnFromExecute(responseToReturnFromExecute);

		ByteArrayInputStream responseContentStream = new ByteArrayInputStream(
				responseContents.getBytes());
		entityFromResponse.setContentToReturn(responseContentStream);
	}

}

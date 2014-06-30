package com.lunchlunch.webcomm.findBuddy;

import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import android.test.UiThreadTest;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.webcomm.MockExplodingClient;
import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;
import com.lunchlunch.webcomm.MockPersonReceiver;
import com.lunchlunch.webcomm.lunch.HttpClientBuilderTestHelper;
import com.lunchlunch.webcomm.person.MockPersonParser;

public class FindBuddyHelperTest extends LunchBuddyTestCase {

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
		assertEquals(FindBuddyHelperInterface.class,
				FindBuddyHelper.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArguments() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		assertEquals(httpClientBuilder, findBuddyHelper.getHttpClientBuilder());
		assertEquals(personParser, findBuddyHelper.getPersonParser());
	}

	@UiThreadTest
	public void testFindBuddyWillCallFindBuddyURLWithJSONSerializedBuddySeeker()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		JSONObject jsonToReturn = new JSONObject("{some:stuff,goes:here}");
		personParser.setJsonToReturn(jsonToReturn);
		JSONObject expectedJSON = new JSONObject();
		expectedJSON.put("person", jsonToReturn);
		
		
		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockPerson buddySeeker = new MockPerson();
		findBuddyHelper.findLunchBuddy(buddySeeker, new MockPersonReceiver(
				countdown));
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(buddySeeker, personParser.getPersonForBuildJSON());

		HttpPost httpPost = assertIsOfTypeAndGet(HttpPost.class,
				httpClientToReturn.getRequestPassedToExecute());
		assertEquals(new URI(com.lunchlunch.LunchBuddyConstants.SERVICE_URL
				+ "/findBuddy"), httpPost.getURI());
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
	public void testFindBuddyWillConvertDataFromTheResponseToJSONAndPassItToThePersonParser()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));

		String responseContents = "{this:should, look:like, json:somewhat}";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);
		findBuddyHelper.findLunchBuddy(new MockPerson(),
				new MockPersonReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);

		JSONObject personJsonPassedIn = personParser.getPersonJsonPassedIn();
		assertEquals("should", personJsonPassedIn.getString("this"));
		assertEquals("like", personJsonPassedIn.getString("look"));
		assertEquals("somewhat", personJsonPassedIn.getString("json"));
	}

	@UiThreadTest
	public void testFindBuddyWillPassTheBuddyReturnedFromTheParserToThePersonReciever()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		MockPerson buddyFound = new MockPerson();
		personParser.setJsonToReturn(new JSONObject("{some:Stuff}"));
		personParser.setPersonToReturn(buddyFound);

		String responseContents = "{this:should, look:like, json:somewhat}";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);

		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		findBuddyHelper.findLunchBuddy(new MockPerson(), personReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(buddyFound, personReceiver.getPersonReceived());

	}

	@UiThreadTest
	public void testExplodingJSONReturnsANullPerson() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		personParser.setJsonToReturn(new JSONObject("{some:Stuff}"));

		String responseContents = "boom goes the dynamite";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);

		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		findBuddyHelper.findLunchBuddy(new MockPerson(), personReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(NullPerson.NULL, personReceiver.getPersonReceived());
	}

	@UiThreadTest
	public void testExplodingRequestReturnANullPerson() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		httpClientBuilder.setHttpClientToReturn(new MockExplodingClient());
		FindBuddyHelper findBuddyHelper = new FindBuddyHelper(
				httpClientBuilder, personParser);

		personParser.setJsonToReturn(new JSONObject("{some:stuff,goes:here}"));
		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		findBuddyHelper.findLunchBuddy(new MockPerson(), personReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(NullPerson.NULL, personReceiver.getPersonReceived());
	}

}

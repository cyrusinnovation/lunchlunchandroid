package com.lunchlunch.webcomm.login;

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

public class LoginHelperTest extends LunchBuddyTestCase {

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

	public void testConstructorAndGets() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelper loginProvider = new LoginHelper(httpClientBuilder,
				personParser);
		assertEquals(personParser, loginProvider.getPersonParser());
		assertEquals(httpClientBuilder, loginProvider.getHttpClientBuilder());
	}

	@UiThreadTest
	public void testLoginWillCallLoginURLUsingClientBuilder() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);

		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);

		String email = "thisistheemail@someplace.com";
		JSONObject emailJson = new JSONObject();
		emailJson.accumulate("email", email);
		loginProvider.login(email, new MockPersonReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);
		
		HttpPost httpPost = assertIsOfTypeAndGet(HttpPost.class,
				httpClientToReturn.getRequestPassedToExecute());
		assertEquals(new URI(com.lunchlunch.LunchBuddyConstants.SERVICE_URL
				+ "/login"), httpPost.getURI());
		InputStream content = httpPost.getEntity().getContent();
		byte[] entityContents = new byte[content.available()];
		content.read(entityContents);
		assertEquals(emailJson.toString(), new String(entityContents));
		assertEquals("application/json",
				httpPost.getHeaders("Accept")[0].getValue());
		assertEquals("application/json",
				httpPost.getHeaders("Content-type")[0].getValue());
		
	}

	@UiThreadTest
	public void testLoginWillConvertDataFromTheResponseToJSONAndPassItToThePersonParser()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);
		String responseContents = "{this:should, look:like, json:somewhat}";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);
		loginProvider.login("thisistheemail@someplace.com",
				new MockPersonReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);

		JSONObject personJsonPassedIn = personParser.getPersonJsonPassedIn();
		assertEquals("should", personJsonPassedIn.getString("this"));
		assertEquals("like", personJsonPassedIn.getString("look"));
		assertEquals("somewhat", personJsonPassedIn.getString("json"));
	}

	@UiThreadTest
	public void testLoginReturnsPersonFromTheParser() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);

		MockPerson expectedPerson = new MockPerson();
		personParser.setPersonToReturn(expectedPerson);
		String responseContents = "{this:should, look:like, json:somewhat}";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);

		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		loginProvider.login("kdsghsdlk", personReceiver);

		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(expectedPerson, personReceiver.getPersonReceived());

	}

	@UiThreadTest
	public void testExplodingJSONReturnsNullPerson() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);

		String responseContents = "kablammo";
		HttpClientBuilderTestHelper.setResponseContentsToReturn(
				httpClientBuilder, responseContents);

		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		loginProvider.login("kdsghsdlk", personReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(NullPerson.NULL, personReceiver.getPersonReceived());

	}

	@UiThreadTest
	public void testExplodingRequestReturnsNullPerson() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);
		httpClientBuilder.setHttpClientToReturn(new MockExplodingClient());

		MockPersonReceiver personReceiver = new MockPersonReceiver(countdown);
		loginProvider.login("kdsghsdlk", personReceiver);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(NullPerson.NULL, personReceiver.getPersonReceived());

	}

}

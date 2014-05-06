package com.lunchlunch.webcomm.login;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import android.test.UiThreadTest;

import com.lunchlunch.LunchTestCase;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.webcomm.MockExplodingClient;
import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;
import com.lunchlunch.webcomm.MockHttpEntity;
import com.lunchlunch.webcomm.MockHttpResponse;
import com.lunchlunch.webcomm.MockPersonReceiver;
import com.lunchlunch.webcomm.person.MockPersonParser;

public class LoginHelperTest extends LunchTestCase {

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
		loginProvider.login(email, new MockPersonReceiver(countdown));
		countdown.await(10, TimeUnit.SECONDS);
		HttpGet httpGet = assertIsOfTypeAndGet(HttpGet.class,
				httpClientToReturn.getRequestPassedToExecute());

		assertEquals(new URI("http://localhost:3000/login?email=" + email),
				httpGet.getURI());
	}

	@UiThreadTest
	public void testLoginWillConvertDataFromTheResponseToJSONAndPassItToThePersonParser()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockPersonParser personParser = new MockPersonParser();
		LoginHelperInterface loginProvider = new LoginHelper(httpClientBuilder,
				personParser);
		String responseContents = "{this:should, look:like, json:somewhat}";
		setResponseContentsToReturn(httpClientBuilder, responseContents);
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
		setResponseContentsToReturn(httpClientBuilder, responseContents);

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
		setResponseContentsToReturn(httpClientBuilder, responseContents);

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

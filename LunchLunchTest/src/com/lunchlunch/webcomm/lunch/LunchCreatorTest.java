package com.lunchlunch.webcomm.lunch;

import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import android.test.UiThreadTest;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.webcomm.MockExplodingClient;
import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;

public class LunchCreatorTest extends LunchBuddyTestCase {

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

	public void testCanGetConstructorArguments() throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchCreator lunchCreator = new LunchCreator(httpClientBuilder,
				lunchParser);

		assertEquals(httpClientBuilder, lunchCreator.getHttpClientBuilder());
		assertEquals(lunchParser, lunchCreator.getLunchParser());
	}

	@UiThreadTest
	public void testCreateLunchWillCallCreateLunchURLWithJSONSerializedLunch()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchCreatorInterface lunchCreator = new LunchCreator(
				httpClientBuilder, lunchParser);

		JSONObject jsonToReturn = new JSONObject("{some:stuff,goes:here}");
		lunchParser.setLunchJSONToReturn(jsonToReturn);
		MockHttpClient httpClient = HttpClientBuilderTestHelper
				.setResponseStatusCodeToReturn(httpClientBuilder, 200);
		MockLunch mockLunch = new MockLunch();
		MockLunchCreationHandler lunchCreationHandler = new MockLunchCreationHandler(
				countdown);

		lunchCreator.createLunch(mockLunch, lunchCreationHandler);
		countdown.await(10, TimeUnit.SECONDS);

		assertEquals(mockLunch, lunchParser.getLunchUsedToCreateJSON());

		HttpPost httpPost = assertIsOfTypeAndGet(HttpPost.class,
				httpClient.getRequestPassedToExecute());
		assertEquals(new URI(com.lunchlunch.LunchBuddyConstants.SERVICE_URL
				+ "/createLunch"), httpPost.getURI());
		InputStream content = httpPost.getEntity().getContent();
		byte[] entityContents = new byte[content.available()];
		content.read(entityContents);
		assertEquals(jsonToReturn.toString(), new String(entityContents));
		assertEquals("application/json",
				httpPost.getHeaders("Accept")[0].getValue());
		assertEquals("application/json",
				httpPost.getHeaders("Content-type")[0].getValue());
		assertTrue(lunchCreationHandler.lunchCreatedWasCalled());

	}

	@UiThreadTest
	public void testExplodingRequestWillCallLunchCreationFailed()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchCreatorInterface lunchCreator = new LunchCreator(
				httpClientBuilder, lunchParser);
		httpClientBuilder.setHttpClientToReturn(new MockExplodingClient());

		JSONObject jsonToReturn = new JSONObject("{some:stuff,goes:here}");
		lunchParser.setLunchJSONToReturn(jsonToReturn);

		MockLunchCreationHandler lunchCreationHandler = new MockLunchCreationHandler(
				countdown);
		lunchCreator.createLunch(new MockLunch(), lunchCreationHandler);
		countdown.await(10, TimeUnit.SECONDS);

		assertTrue(lunchCreationHandler.lunchCreationFailedWasCalled());

	}

	@UiThreadTest
	public void testAnythingExceptForASuccesfulCodeWillCallLunchCreationFailed()
			throws Exception {
		MockHttpClientBuilder httpClientBuilder = new MockHttpClientBuilder();
		MockLunchParser lunchParser = new MockLunchParser();
		LunchCreatorInterface lunchCreator = new LunchCreator(
				httpClientBuilder, lunchParser);
		JSONObject jsonToReturn = new JSONObject("{some:stuff,goes:here}");
		lunchParser.setLunchJSONToReturn(jsonToReturn);
		checkFailureWithCode(httpClientBuilder, lunchCreator, 400);
		checkFailureWithCode(httpClientBuilder, lunchCreator, 300);
		checkFailureWithCode(httpClientBuilder, lunchCreator, 500);
		checkFailureWithCode(httpClientBuilder, lunchCreator, 199);
		checkSuccessWithCode(httpClientBuilder, lunchCreator, 200);
		checkSuccessWithCode(httpClientBuilder, lunchCreator, 201);
		checkSuccessWithCode(httpClientBuilder, lunchCreator, 299);
	}

	private void checkFailureWithCode(MockHttpClientBuilder httpClientBuilder,
			LunchCreatorInterface lunchCreator, int responseCode)
			throws InterruptedException {
		MockLunchCreationHandler lunchCreationHandler = fireCreateLunchAndReturnCode(
				httpClientBuilder, lunchCreator, responseCode);

		assertTrue(lunchCreationHandler.lunchCreationFailedWasCalled());
	}

	private void checkSuccessWithCode(MockHttpClientBuilder httpClientBuilder,
			LunchCreatorInterface lunchCreator, int responseCode)
			throws InterruptedException {
		MockLunchCreationHandler lunchCreationHandler = fireCreateLunchAndReturnCode(
				httpClientBuilder, lunchCreator, responseCode);

		assertTrue(lunchCreationHandler.lunchCreatedWasCalled());
	}

	private MockLunchCreationHandler fireCreateLunchAndReturnCode(
			MockHttpClientBuilder httpClientBuilder,
			LunchCreatorInterface lunchCreator, int responseCode)
			throws InterruptedException {
		HttpClientBuilderTestHelper.setResponseStatusCodeToReturn(
				httpClientBuilder, responseCode);

		CountDownLatch latch = new CountDownLatch(1);
		MockLunchCreationHandler lunchCreationHandler = new MockLunchCreationHandler(
				latch);
		lunchCreator.createLunch(new MockLunch(), lunchCreationHandler);
		latch.await(10, TimeUnit.SECONDS);
		return lunchCreationHandler;
	}
}

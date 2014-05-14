package com.lunchlunch.webcomm.lunch;

import java.io.ByteArrayInputStream;

import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;
import com.lunchlunch.webcomm.MockHttpEntity;
import com.lunchlunch.webcomm.MockHttpResponse;

public class HttpClientBuilderTestHelper {
	public static MockHttpClient setResponseContentsToReturn(
			MockHttpClientBuilder httpClientBuilder, String responseContents) {
		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockHttpResponse responseToReturnFromExecute = new MockHttpResponse();
		MockHttpEntity entityFromResponse = new MockHttpEntity();

		ByteArrayInputStream responseContentStream = new ByteArrayInputStream(
				responseContents.getBytes());
		entityFromResponse.setContentToReturn(responseContentStream);

		responseToReturnFromExecute.setEntity(entityFromResponse);
		httpClientToReturn
				.setResponseToReturnFromExecute(responseToReturnFromExecute);
		return httpClientToReturn;
	}

	public static MockHttpClient setResponseStatusCodeToReturn(
			MockHttpClientBuilder httpClientBuilder, int code) {
		MockHttpClient httpClientToReturn = new MockHttpClient();

		httpClientBuilder.setHttpClientToReturn(httpClientToReturn);
		MockHttpResponse responseToReturnFromExecute = new MockHttpResponse();
		responseToReturnFromExecute.setStatusCode(code);
		httpClientToReturn
				.setResponseToReturnFromExecute(responseToReturnFromExecute);
		return httpClientToReturn;
	}

}

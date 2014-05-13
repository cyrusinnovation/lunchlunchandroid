package com.lunchlunch.webcomm.lunch;

import java.io.ByteArrayInputStream;

import com.lunchlunch.webcomm.MockHttpClient;
import com.lunchlunch.webcomm.MockHttpClientBuilder;
import com.lunchlunch.webcomm.MockHttpEntity;
import com.lunchlunch.webcomm.MockHttpResponse;

public class HttpClientBuilderTestHelper {
	public static void setResponseContentsToReturn(
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

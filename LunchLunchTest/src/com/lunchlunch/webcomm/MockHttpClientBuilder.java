package com.lunchlunch.webcomm;

import org.apache.http.client.HttpClient;

public class MockHttpClientBuilder implements HttpClientBuilderInterface {

	private HttpClient httpClientToReturn = new MockHttpClient();

	@Override
	public HttpClient buildConnection() {
		return httpClientToReturn;
	}
	public void setHttpClientToReturn(HttpClient httpClientToReturn) {
		this.httpClientToReturn = httpClientToReturn;
	}

}

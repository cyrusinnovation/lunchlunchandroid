package com.lunchlunch.webcomm;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

public class HttpClientBuilder implements HttpClientBuilderInterface {

	public static final HttpClientBuilderInterface SINGLETON = new HttpClientBuilder();
	private HttpClientBuilder() {
	}

	@Override
	public HttpClient buildConnection() {
		return new DefaultHttpClient(new BasicHttpParams());
		
	}


}


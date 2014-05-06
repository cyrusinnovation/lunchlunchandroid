package com.lunchlunch.webcomm;

import org.apache.http.client.HttpClient;

public interface HttpClientBuilderInterface {

	public abstract HttpClient buildConnection();

}
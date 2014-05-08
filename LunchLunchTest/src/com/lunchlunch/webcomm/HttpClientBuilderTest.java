package com.lunchlunch.webcomm;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import com.lunchlunch.LunchBuddyTestCase;

public class HttpClientBuilderTest extends LunchBuddyTestCase {

	public void testWillBuildHttpClient() throws Exception {

		DefaultHttpClient client = assertIsOfTypeAndGet(
				DefaultHttpClient.class,
				HttpClientBuilder.SINGLETON.buildConnection());
		assertIsOfTypeAndGet(BasicHttpParams.class, client.getParams());
	}

	public void testImplementsInterface() throws Exception {
		assertEquals(HttpClientBuilderInterface.class,
				HttpClientBuilder.class.getInterfaces()[0]);
	}

	public void testIsASingleton() throws Exception {
		assertEquals(0, HttpClientBuilder.class.getConstructors().length);
		assertEquals(HttpClientBuilder.class,
				HttpClientBuilder.SINGLETON.getClass());
		assertSame(HttpClientBuilder.SINGLETON, HttpClientBuilder.SINGLETON);
	}
}

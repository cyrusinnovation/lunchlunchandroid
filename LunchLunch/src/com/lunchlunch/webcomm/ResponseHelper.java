package com.lunchlunch.webcomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class ResponseHelper {

	public static String getResponseContentsAsString(HttpResponse response)
			throws IllegalStateException, IOException {
		HttpEntity entity = response.getEntity();
		InputStream responseContents = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				responseContents));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

}

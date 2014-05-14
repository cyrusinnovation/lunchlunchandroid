package com.lunchlunch.webcomm.lunch;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.webcomm.HttpClientBuilderInterface;

public class LunchCreator implements LunchCreatorInterface {

	private HttpClientBuilderInterface httpClientBuilder;
	private LunchParserInterface lunchParser;

	public LunchCreator(HttpClientBuilderInterface httpClientBuilder,
			LunchParserInterface lunchParser) {
		this.httpClientBuilder = httpClientBuilder;
		this.lunchParser = lunchParser;
	}

	@Override
	public void createLunch(LunchInterface lunch,
			LunchCreationHandler lunchCreationHandler) {
		CreateLunchTask createLunchTask = new CreateLunchTask(lunch,
				lunchCreationHandler);
		createLunchTask.execute();

	}

	public HttpClientBuilderInterface getHttpClientBuilder() {
		return httpClientBuilder;
	}

	public LunchParserInterface getLunchParser() {
		return lunchParser;
	}

	private class CreateLunchTask extends AsyncTask<String, Void, Boolean> {

		private LunchCreationHandler creationHandler;
		private LunchInterface lunch;

		public CreateLunchTask(LunchInterface lunch,
				LunchCreationHandler lunchCreationHandler) {
			this.lunch = lunch;
			creationHandler = lunchCreationHandler;
		}

		@Override
		protected Boolean doInBackground(String... params) {
			JSONObject lunchJSON = lunchParser.createLunchJSON(lunch);

			HttpClient client = httpClientBuilder.buildConnection();
			HttpPost post = new HttpPost(LunchBuddyConstants.SERVICE_URL
					+ "/createLunch");
			try {
				String jsonString = lunchJSON.toString();
				post.setEntity(new StringEntity(jsonString));
				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");
				HttpResponse response = client.execute(post);
				if (responseHasASuccessCode(response)) {
					return true;
				}
			} catch (IOException e) {
			}
			return false;
		}

		private boolean responseHasASuccessCode(HttpResponse response) {
			int statusCode = response.getStatusLine().getStatusCode();
			return statusCode > 199 && statusCode < 300;
		}

		@Override
		protected void onPostExecute(Boolean sucess) {
			if (sucess) {
				creationHandler.lunchCreated();
			} else {
				creationHandler.lunchCreationFailed();
			}
			super.onPostExecute(sucess);
		}

	}

}

package com.lunchlunch.webcomm.findBuddy;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.HttpClientBuilderInterface;
import com.lunchlunch.webcomm.ResponseHelper;
import com.lunchlunch.webcomm.person.PersonParserInterface;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class FindBuddyHelper implements FindBuddyHelperInterface {

	private HttpClientBuilderInterface httpClientBuilder;
	private PersonParserInterface personParser;

	public FindBuddyHelper(HttpClientBuilderInterface httpClientBuilder,
			PersonParserInterface personParser) {
		this.httpClientBuilder = httpClientBuilder;
		this.personParser = personParser;
	}

	@Override
	public void findLunchBuddy(PersonInterface buddySeeker,
			PersonReceiver personReceiver) {
		FindBuddyTask task = new FindBuddyTask(buddySeeker, personReceiver);
		task.execute();

	}

	public HttpClientBuilderInterface getHttpClientBuilder() {
		return httpClientBuilder;
	}

	public PersonParserInterface getPersonParser() {
		return personParser;
	}

	private class FindBuddyTask extends
			AsyncTask<String, Void, PersonInterface> {

		private PersonInterface buddySeeker;
		private PersonReceiver personReceiver;

		public FindBuddyTask(PersonInterface buddySeeker,
				PersonReceiver personReceiver) {
			this.buddySeeker = buddySeeker;
			this.personReceiver = personReceiver;
		}

		@Override
		protected PersonInterface doInBackground(String... params) {

			HttpClient client = httpClientBuilder.buildConnection();
			JSONObject personJSON = personParser
					.buildJSONFromPerson(buddySeeker);
			JSONObject bodyJSON = new JSONObject();
			try {
				bodyJSON.put("person", personJSON);
				HttpPost post = new HttpPost(LunchBuddyConstants.SERVICE_URL
						+ "/findBuddy");
				String jsonString = bodyJSON.toString();
				post.setEntity(new StringEntity(jsonString));
				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");
				HttpResponse response = client.execute(post);
				
				String result = ResponseHelper
						.getResponseContentsAsString(response);
				PersonInterface buildPersonFromJSON = personParser
						.buildPersonFromJSON(new JSONObject(result));
				return buildPersonFromJSON;
			} catch (IOException | JSONException e) {
				return NullPerson.NULL;
			}
		}

		@Override
		protected void onPostExecute(PersonInterface result) {
			personReceiver.personReceived(result);
			super.onPostExecute(result);
		}
	}
}

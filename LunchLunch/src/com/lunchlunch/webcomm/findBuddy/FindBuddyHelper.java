package com.lunchlunch.webcomm.findBuddy;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
			try {
				String encodedPerson = URLEncoder.encode(personJSON.toString(),
						"UTF-8");
				HttpGet httpGet = new HttpGet(LunchBuddyConstants.SERVICE_URL
						+ "/findBuddy?person=" + encodedPerson);
				HttpResponse response = client.execute(httpGet);
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

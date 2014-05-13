package com.lunchlunch.webcomm.lunch;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.lunchlunch.LunchBuddyConstants;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.HttpClientBuilderInterface;
import com.lunchlunch.webcomm.ResponseHelper;
import com.lunchlunch.webcomm.person.PersonParserInterface;

public class LunchHelper implements LunchHelperInterface {

	private HttpClientBuilderInterface httpClientBuilder;
	private PersonParserInterface personParser;
	private LunchParserInterface lunchParser;

	public LunchHelper(HttpClientBuilderInterface httpClientBuilder,
			PersonParserInterface personParser, LunchParserInterface lunchParser) {
		this.httpClientBuilder = httpClientBuilder;
		this.personParser = personParser;
		this.lunchParser = lunchParser;
	}

	public HttpClientBuilderInterface getHttpClientBuilder() {
		return httpClientBuilder;
	}

	public PersonParserInterface getPersonParser() {
		return personParser;
	}

	public LunchParserInterface getLunchParser() {
		return lunchParser;
	}

	@Override
	public void getLunches(PersonInterface person, LunchReceiver lunchReceiver) {
		GetLunchTask getLunchTask = new GetLunchTask(person, lunchReceiver);
		getLunchTask.execute();

	}

	private class GetLunchTask extends
			AsyncTask<String, Void, List<LunchInterface>> {

		private LunchReceiver lunchReceiver;
		private PersonInterface person;

		public GetLunchTask(PersonInterface person, LunchReceiver lunchReceiver) {
			this.person = person;
			this.lunchReceiver = lunchReceiver;
		}

		@Override
		protected List<LunchInterface> doInBackground(String... params) {
			List<LunchInterface> lunches = new ArrayList<LunchInterface>();
			try {
				HttpClient client = httpClientBuilder.buildConnection();
				JSONObject personJSON = personParser
						.buildJSONFromPerson(person);
				String encodedPerson = URLEncoder.encode(personJSON.toString(),
						"UTF-8");
				HttpGet httpGet = new HttpGet(LunchBuddyConstants.SERVICE_URL
						+ "/getLunches?person=" + encodedPerson);

				HttpResponse response = client.execute(httpGet);
				String result = ResponseHelper
						.getResponseContentsAsString(response);
				lunches = lunchParser.parseLunches(new JSONArray(result));
			} catch (IOException | JSONException e) {
			}
			return lunches;
		}

		@Override
		protected void onPostExecute(List<LunchInterface> result) {
			lunchReceiver.lunchesReceived(result);

		}
	}
}

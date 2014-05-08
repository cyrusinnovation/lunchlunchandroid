package com.lunchlunch.webcomm.login;

import java.io.IOException;

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

public class LoginHelper implements LoginHelperInterface {

	private class LoginTask extends AsyncTask<String, Void, PersonInterface> {

		private String email;
		private PersonReceiver personReceiver;

		public LoginTask(String email, PersonReceiver personReceiver) {
			this.email = email;
			this.personReceiver = personReceiver;
		}

		@Override
		protected PersonInterface doInBackground(String... params) {
			HttpClient client = httpClientBuilder.buildConnection();
			try {
				HttpGet httpGet = new HttpGet(LunchBuddyConstants.SERVICE_URL
						+ "/login?email=" + email);

				HttpResponse response = client.execute(httpGet);
				String result = ResponseHelper
						.getResponseContentsAsString(response);

				return personParser.buildPersonFromJSON(new JSONObject(result));
			} catch (IOException | JSONException e) {
				return NullPerson.NULL;
			}
		}

		@Override
		protected void onPostExecute(PersonInterface result) {
			personReceiver.personReceived(result);
		}

	}

	private HttpClientBuilderInterface httpClientBuilder;
	private PersonParserInterface personParser;

	public LoginHelper(HttpClientBuilderInterface httpClientBuilder,
			PersonParserInterface personParser) {
		this.httpClientBuilder = httpClientBuilder;
		this.personParser = personParser;

	}

	public HttpClientBuilderInterface getHttpClientBuilder() {
		return httpClientBuilder;
	}

	public PersonParserInterface getPersonParser() {
		return personParser;
	}

	@Override
	public void login(String email, PersonReceiver personReceiver) {
		LoginTask loginTask = new LoginTask(email, personReceiver);
		loginTask.execute();

	}

}

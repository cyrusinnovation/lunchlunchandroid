package com.lunchlunch.controller;

import android.app.Activity;
import android.content.Intent;

public class MockActivity extends Activity {
	private Intent[] intentsToStart;
	private Intent intentToStart;

	@Override
	public void startActivities(Intent[] intents) {
		intentsToStart = intents;
	}

	public Intent[] getIntentsToStart() {
		return intentsToStart;
	}

	@Override
	public void startActivity(Intent intent) {
		this.intentToStart = intent;
	}

	public Intent getIntentToStart() {
		return intentToStart;
	}

	@Override
	public String getPackageName() {
		return "";
	}

}

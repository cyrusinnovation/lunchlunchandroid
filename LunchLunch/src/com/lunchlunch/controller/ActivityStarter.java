package com.lunchlunch.controller;

import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

public class ActivityStarter implements ActivityStarterInterface {

	public static ActivityStarter SINGLETON = new ActivityStarter();

	private ActivityStarter() {
	}

	@Override
	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart) {
		Intent intent = new Intent(baseActivity, activityClassToStart);
		baseActivity.startActivity(intent);
	}

	@Override
	public void startActivityWithExtras(Activity baseActivity,
			Class<? extends Activity> activityClassToStart,
			Map<String, Parcelable> extraInformation) {
		Intent intent = new Intent(baseActivity, activityClassToStart);
		for (Entry<String, Parcelable> information : extraInformation
				.entrySet()) {
			intent.putExtra(information.getKey(), information.getValue());
		}
		baseActivity.startActivity(intent);

	}

}

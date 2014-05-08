package com.lunchlunch.controller;

import java.util.Map;

import android.app.Activity;
import android.os.Parcelable;

public interface ActivityStarterInterface {

	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart);

	public void startActivityWithExtras(Activity baseActivity,
			Class<? extends Activity> activityClassToStart,
			Map<String, Parcelable> extraInformation);
}

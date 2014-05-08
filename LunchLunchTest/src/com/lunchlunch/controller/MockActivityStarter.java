package com.lunchlunch.controller;

import java.util.Map;

import android.app.Activity;
import android.os.Parcelable;

public class MockActivityStarter implements ActivityStarterInterface {

	private Activity baseActivity;
	private Class<? extends Activity> activityClassToStart;
	private Map<String, Parcelable> extraInformationPassedToStart;

	@Override
	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart) {
		this.baseActivity = baseActivity;
		this.activityClassToStart = activityClassToStart;

	}

	public Class<? extends Activity> getActivityClassToStart() {
		return activityClassToStart;
	}

	public Activity getBaseActivity() {
		return baseActivity;
	}

	@Override
	public void startActivityWithExtras(Activity baseActivity,
			Class<? extends Activity> activityClassToStart,
			Map<String, Parcelable> extraInformation) {
		this.baseActivity = baseActivity;
		this.activityClassToStart = activityClassToStart;
		this.extraInformationPassedToStart = extraInformation;
	}

	public Map<String, Parcelable> getExtraInformationPassedToStart() {
		return extraInformationPassedToStart;
	}
}

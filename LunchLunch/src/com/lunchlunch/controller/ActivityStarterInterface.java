package com.lunchlunch.controller;

import android.app.Activity;

public interface ActivityStarterInterface {

	public void startActivity(Activity baseActivity,
			Class<? extends Activity> activityClassToStart);
}

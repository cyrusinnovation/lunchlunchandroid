package com.lunchlunch.controller;

import android.app.Activity;

public class StartActivityCommand implements Command {

	private Activity baseActivity;
	private Class<? extends Activity> activityClassToStart;
	private ActivityStarterInterface activityStarter;

	public StartActivityCommand(Activity baseActivity,
			Class<? extends Activity> activityClassToStart,
			ActivityStarterInterface activityStarter) {
		this.baseActivity = baseActivity;
		this.activityClassToStart = activityClassToStart;
		this.activityStarter = activityStarter;
	}

	@Override
	public void execute() {
		activityStarter.startActivity(baseActivity, activityClassToStart);

	}

	public Class<? extends Activity> getTargetActivity() {
		return activityClassToStart;
	}

	public Activity getBaseActivity() {
		return baseActivity;
	}

	public ActivityStarterInterface getActivityStarter() {
		return activityStarter;
	}

}

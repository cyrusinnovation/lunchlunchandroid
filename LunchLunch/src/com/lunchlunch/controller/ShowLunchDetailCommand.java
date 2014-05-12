package com.lunchlunch.controller;

import java.util.HashMap;

import android.app.Activity;
import android.os.Parcelable;

import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.view.lunches.LunchDetailActivity;
import com.lunchlunch.view.lunches.LunchDetailFragment;

public class ShowLunchDetailCommand implements Command {

	private LunchInterface lunch;
	private Activity lunchListActivity;
	private ActivityStarterInterface activityStarter;

	public ShowLunchDetailCommand(LunchInterface lunch,
			Activity lunchListActivity, ActivityStarterInterface activityStarter) {
		this.lunch = lunch;
		this.lunchListActivity = lunchListActivity;
		this.activityStarter = activityStarter;
	}

	@Override
	public void execute() {
		HashMap<String, Parcelable> extraInfo = new HashMap<String, Parcelable>();
		extraInfo.put(LunchDetailFragment.DETAILED_LUNCH_KEY, lunch);
		activityStarter.startActivityWithExtras(lunchListActivity,
				LunchDetailActivity.class, extraInfo);

	}

	public LunchInterface getLunch() {
		return lunch;
	}

	public Activity getLunchListActivity() {
		return lunchListActivity;
	}

	public ActivityStarterInterface getActivityStarter() {
		return activityStarter;
	}
}

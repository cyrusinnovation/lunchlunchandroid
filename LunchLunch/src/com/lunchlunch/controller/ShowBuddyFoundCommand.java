package com.lunchlunch.controller;

import java.util.HashMap;

import android.app.Activity;
import android.os.Parcelable;

import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.view.lunches.FindLunchBuddyActivity;

public class ShowBuddyFoundCommand implements Command {

	private PersonInterface lunchBuddyFound;
	private Activity lunchListActivity;
	private ActivityStarterInterface activityStarter;

	public ShowBuddyFoundCommand(PersonInterface lunchBuddyFound,
			Activity lunchListActivity, ActivityStarterInterface activityStarter) {
		this.lunchBuddyFound = lunchBuddyFound;
		this.lunchListActivity = lunchListActivity;
		this.activityStarter = activityStarter;
	}

	@Override
	public void execute() {
		HashMap<String, Parcelable> extraInformation = new HashMap<String, Parcelable>();
		extraInformation.put(FindLunchBuddyActivity.LUNCH_BUDDY_KEY,
				lunchBuddyFound);
		this.activityStarter.startActivityWithExtras(lunchListActivity,
				FindLunchBuddyActivity.class, extraInformation);

	}

	public PersonInterface getLunchBuddyFound() {
		return lunchBuddyFound;
	}

	public Activity getOriginatingActivity() {
		return lunchListActivity;
	}

	public ActivityStarterInterface getActivityStarter() {
		return activityStarter;
	}

}

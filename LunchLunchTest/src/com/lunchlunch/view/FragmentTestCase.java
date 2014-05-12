package com.lunchlunch.view;

import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

public abstract class FragmentTestCase<T extends FragmentActivity> extends
		ActivityInstrumentationTestCase2<T> {

	private Class<T> activityClass;

	public FragmentTestCase(Class<T> activityClass) {
		super(activityClass);
		this.activityClass = activityClass;
	}

	public Fragment startFragment(Fragment fragment) {
		setActivityIntent(new HashMap<String, Parcelable>());
		T activity = getActivity();
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(android.R.id.content, fragment).commit();
		getInstrumentation().waitForIdleSync();
		return fragmentManager.findFragmentById(android.R.id.content);
	}

	public Fragment startFragmentWithExtras(Fragment fragment,
			HashMap<String, Parcelable> extraParcelables) {
		setActivityIntent(extraParcelables);
		T activity = getActivity();
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(android.R.id.content, fragment).commit();
		getInstrumentation().waitForIdleSync();
		return fragmentManager.findFragmentById(android.R.id.content);
	}

	private void setActivityIntent(HashMap<String, Parcelable> extraParcelables) {
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				activityClass);
		for (Entry<String, Parcelable> entry : extraParcelables.entrySet()) {
			intent.putExtra(entry.getKey(), entry.getValue());
		}
		setActivityIntent(intent);
	}
}

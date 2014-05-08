package com.lunchlunch.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;

import com.lunchlunch.view.login.Login;

public abstract class FragmentTestCase<T extends FragmentActivity> extends
		ActivityUnitTestCase<T> {

	public FragmentTestCase(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				Login.class);
		startActivity(intent, null, null);

	}

	public Fragment startFragment(Fragment fragment) {

		T activity = getActivity();
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(android.R.id.content, fragment).commit();
		fragmentManager.executePendingTransactions();
		return fragmentManager.findFragmentById(android.R.id.content);
	}
}

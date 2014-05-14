package com.lunchlunch.view;

import android.app.TimePickerDialog;
import android.content.Context;

public class MockTimePickerDialog extends TimePickerDialog {

	private boolean showCalled;

	public MockTimePickerDialog(Context mContext) {
		super(mContext, null, 0, 0, false);
	}

	@Override
	public void show() {
		showCalled = true;
	}

	public boolean wasShowCalled() {
		return showCalled;
	}

}

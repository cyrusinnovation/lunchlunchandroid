package com.lunchlunch.view;

import android.app.DatePickerDialog;
import android.content.Context;

public class MockDatePickerDialog extends DatePickerDialog {

	private boolean showWasCalled;

	public MockDatePickerDialog(Context mContext) {
		super(mContext, 0, new MockOnDateSetListener(), 1, 1, 1);
	}

	@Override
	public void show() {
		showWasCalled = true;
	}

	public boolean wasShowCalled() {
		return showWasCalled;
	}
}

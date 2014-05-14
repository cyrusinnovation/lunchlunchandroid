package com.lunchlunch.view;

import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;

public class MockOnDateSetListener implements OnDateSetListener {

	private boolean dateSetCalled;

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		dateSetCalled = true;

	}

	public boolean wasDateSetCalled() {
		return dateSetCalled;
	}

}

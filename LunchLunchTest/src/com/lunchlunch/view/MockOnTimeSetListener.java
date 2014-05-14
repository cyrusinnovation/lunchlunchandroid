package com.lunchlunch.view;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.TimePicker;

public class MockOnTimeSetListener implements OnTimeSetListener {

	private boolean onTimeSetCalled;

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		onTimeSetCalled = true;

	}

	public boolean wasOnTimeSetCalled() {
		return onTimeSetCalled;
	}

}

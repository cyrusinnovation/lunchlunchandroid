package com.lunchlunch.view;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public interface DialogHandlerInterface {

	public abstract void showErrorDialog(Context baseContext,
			String errorMessage);

	public abstract void showDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day);

	public abstract void showTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute);
}
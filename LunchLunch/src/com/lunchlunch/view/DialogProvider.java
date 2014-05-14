package com.lunchlunch.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public class DialogProvider implements DialogProviderInterface {

	public static final DialogProviderInterface SINGLETON = new DialogProvider();

	private DialogProvider() {
	}

	@Override
	public AlertDialog.Builder provideAlertDialogBuilder(Context context) {
		return new AlertDialog.Builder(context);
	}

	@Override
	public DatePickerDialog provideDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day) {
		return new DatePickerDialog(baseContext, dateSetListener, year, month,
				day);
	}

	@Override
	public TimePickerDialog provideTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute) {
		return new TimePickerDialog(baseContext, timeSetListener, hour, minute,
				false);
	}
}

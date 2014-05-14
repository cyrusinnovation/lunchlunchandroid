package com.lunchlunch.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public interface DialogProviderInterface {

	public abstract AlertDialog.Builder provideAlertDialogBuilder(
			Context context);

	public DatePickerDialog provideDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day);

	public TimePickerDialog provideTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute);

}
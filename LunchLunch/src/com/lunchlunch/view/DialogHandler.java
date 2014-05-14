package com.lunchlunch.view;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public class DialogHandler implements DialogHandlerInterface {

	private DialogProviderInterface dialogProvider;

	public DialogHandler(DialogProviderInterface alertDialogProvider) {
		this.dialogProvider = alertDialogProvider;
	}

	@Override
	public void showErrorDialog(Context baseContext, String errorMessage) {
		Builder builder = dialogProvider.provideAlertDialogBuilder(baseContext);
		builder.setTitle("Error");
		builder.setMessage(errorMessage);
		builder.setPositiveButton("Ok", null);
		builder.show();
	}

	@Override
	public void showDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day) {
		DatePickerDialog datePickerDialog = dialogProvider
				.provideDatePickerDialog(baseContext, dateSetListener, year,
						month, day);
		datePickerDialog.show();
	}

	@Override
	public void showTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute) {
		TimePickerDialog timePickerDialog = dialogProvider
				.provideTimePickerDialog(baseContext, timeSetListener, hour,
						minute);
		timePickerDialog.show();
	}

	public DialogProviderInterface getDialogProvider() {
		return dialogProvider;
	}

}

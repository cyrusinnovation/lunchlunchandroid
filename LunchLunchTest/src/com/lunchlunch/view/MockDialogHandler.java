package com.lunchlunch.view;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public class MockDialogHandler implements DialogHandlerInterface {

	private Context baseContextForLastErrorDialog;
	private String errorMessageForLastErrorDialog;
	private Context baseContextForShowDatePicker;
	private OnDateSetListener dateSetListenerForShowDatePicker;
	private int yearForShowDatePicker;
	private int monthForShowDatePicker;
	private int dayForShowDatePicker;
	private Context baseContextForShowTimePicker;
	private OnTimeSetListener timeSetListenerForShowTimePicker;
	private int hourForShowTimePicker;
	private int minuteForShowTimePicker;

	@Override
	public void showErrorDialog(Context baseContext, String errorMessage) {
		this.baseContextForLastErrorDialog = baseContext;
		this.errorMessageForLastErrorDialog = errorMessage;

	}

	@Override
	public void showDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day) {
		this.baseContextForShowDatePicker = baseContext;
		dateSetListenerForShowDatePicker = dateSetListener;
		this.yearForShowDatePicker = year;
		this.monthForShowDatePicker = month;
		this.dayForShowDatePicker = day;
	}

	@Override
	public void showTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute) {
		this.baseContextForShowTimePicker = baseContext;
		this.timeSetListenerForShowTimePicker = timeSetListener;
		this.hourForShowTimePicker = hour;
		this.minuteForShowTimePicker = minute;

	}

	public Context getBaseContextForShowTimePicker() {
		return baseContextForShowTimePicker;
	}

	public int getHourForShowTimePicker() {
		return hourForShowTimePicker;
	}

	public int getMinuteForShowTimePicker() {
		return minuteForShowTimePicker;
	}

	public OnTimeSetListener getTimeSetListenerForShowTimePicker() {
		return timeSetListenerForShowTimePicker;
	}

	public Context getBaseContextForShowDatePicker() {
		return baseContextForShowDatePicker;
	}

	public OnDateSetListener getDateSetListenerForShowDatePicker() {
		return dateSetListenerForShowDatePicker;
	}

	public int getDayForShowDatePicker() {
		return dayForShowDatePicker;
	}

	public int getMonthForShowDatePicker() {
		return monthForShowDatePicker;
	}

	public int getYearForShowDatePicker() {
		return yearForShowDatePicker;
	}

	public Context getBaseContextForLastErrorDialog() {
		return baseContextForLastErrorDialog;
	}

	public String getErrorMessageForLastErrorDialog() {
		return errorMessageForLastErrorDialog;
	}

}

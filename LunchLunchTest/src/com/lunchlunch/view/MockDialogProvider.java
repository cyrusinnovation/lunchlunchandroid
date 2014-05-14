package com.lunchlunch.view;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

public class MockDialogProvider implements DialogProviderInterface {

	private Context contextPassedToAlertDialogBuilder;
	private Builder builderToProvide;
	private Context baseContextForProvideDatePicker;
	private OnDateSetListener dateSetListenerForProvideDatePicker;
	private int yearForProvideDatePicker;
	private int monthForProvideDatePicker;
	private int dayForProvideDatePicker;
	private DatePickerDialog datePickerToReturn;
	private Context baseContextForProvideTimePicker;
	private OnTimeSetListener timeSetListenerForProvideTimePicker;
	private int hourForProvideTimePicker;
	private int minuteForProvideTimePicker;
	private TimePickerDialog timePickerToReturn;

	@Override
	public Builder provideAlertDialogBuilder(Context context) {
		this.contextPassedToAlertDialogBuilder = context;
		return builderToProvide;
	}

	public void setBuilderToProvide(Builder builderToProvide) {
		this.builderToProvide = builderToProvide;
	}

	public Context getContextPassedToAlertDialogBuilder() {
		return contextPassedToAlertDialogBuilder;
	}

	@Override
	public DatePickerDialog provideDatePickerDialog(Context baseContext,
			OnDateSetListener dateSetListener, int year, int month, int day) {
		this.baseContextForProvideDatePicker = baseContext;
		this.dateSetListenerForProvideDatePicker = dateSetListener;
		this.yearForProvideDatePicker = year;
		this.monthForProvideDatePicker = month;
		this.dayForProvideDatePicker = day;
		return datePickerToReturn;
	}

	public Context getBaseContextForProvideDatePicker() {
		return baseContextForProvideDatePicker;
	}

	public OnDateSetListener getDateSetListenerForProvideDatePicker() {
		return dateSetListenerForProvideDatePicker;
	}

	public int getDayForProvideDatePicker() {
		return dayForProvideDatePicker;
	}

	public int getMonthForProvideDatePicker() {
		return monthForProvideDatePicker;
	}

	public int getYearForProvideDatePicker() {
		return yearForProvideDatePicker;
	}

	public void setDatePickerToReturn(DatePickerDialog datePickerToReturn) {
		this.datePickerToReturn = datePickerToReturn;
	}

	@Override
	public TimePickerDialog provideTimePickerDialog(Context baseContext,
			OnTimeSetListener timeSetListener, int hour, int minute) {
		this.baseContextForProvideTimePicker = baseContext;
		this.timeSetListenerForProvideTimePicker = timeSetListener;
		this.hourForProvideTimePicker = hour;
		this.minuteForProvideTimePicker = minute;
		return timePickerToReturn;
	}

	public Context getBaseContextForProvideTimePicker() {
		return baseContextForProvideTimePicker;
	}

	public OnTimeSetListener getTimeSetListenerForProvideTimePicker() {
		return timeSetListenerForProvideTimePicker;
	}

	public int getHourForProvideTimePicker() {
		return hourForProvideTimePicker;
	}

	public int getMinuteForProvideTimePicker() {
		return minuteForProvideTimePicker;
	}

	public void setTimePickerToReturn(TimePickerDialog timePickerToReturn) {
		this.timePickerToReturn = timePickerToReturn;
	}

}

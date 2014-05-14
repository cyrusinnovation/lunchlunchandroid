package com.lunchlunch.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.test.AndroidTestCase;

import com.lunchlunch.LunchBuddyTestCase;

public class DialogProviderTest extends AndroidTestCase {
	public void testIsOfInterface() throws Exception {
		assertEquals(DialogProviderInterface.class,
				DialogProvider.class.getInterfaces()[0]);
	}

	public void testSingleton() throws Exception {
		assertEquals(0, DialogProvider.class.getConstructors().length);
		LunchBuddyTestCase.assertIsOfTypeAndGet(DialogProvider.class,
				DialogProvider.SINGLETON);
	}

	public void testProvideAlertDialogBuilder() throws Exception {
		LunchBuddyTestCase.assertIsOfTypeAndGet(AlertDialog.Builder.class,
				DialogProvider.SINGLETON.provideAlertDialogBuilder(mContext));
	}

	public void testProvideDatePickerDialog() throws Exception {
		int year = 2005;
		int month = 2;
		int day = 11;
		MockOnDateSetListener dateSetListener = new MockOnDateSetListener();
		DatePickerDialog datePickerDialog = LunchBuddyTestCase
				.assertIsOfTypeAndGet(DatePickerDialog.class,
						DialogProvider.SINGLETON.provideDatePickerDialog(
								mContext, dateSetListener, year, month, day));
		assertEquals(year, datePickerDialog.getDatePicker().getYear());
		assertEquals(month, datePickerDialog.getDatePicker().getMonth());
		assertEquals(day, datePickerDialog.getDatePicker().getDayOfMonth());

		datePickerDialog.onClick(datePickerDialog, 0);
		assertTrue(dateSetListener.wasDateSetCalled());

	}

	public void testProvideTimePickerDialog() throws Exception {
		MockOnTimeSetListener timeSetListener = new MockOnTimeSetListener();
		int hour = 15;
		int minute = 12;
		TimePickerDialog timePickerDialog = LunchBuddyTestCase
				.assertIsOfTypeAndGet(TimePickerDialog.class,
						DialogProvider.SINGLETON.provideTimePickerDialog(
								mContext, timeSetListener, hour, minute));

		Bundle bundle = timePickerDialog.onSaveInstanceState();

		assertEquals(hour, bundle.getInt("hour"));
		assertEquals(minute, bundle.getInt("minute"));
		assertFalse(bundle.getBoolean("is24Hour"));

		timePickerDialog.onClick(timePickerDialog, 0);

		assertTrue(timeSetListener.wasOnTimeSetCalled());
	}
}

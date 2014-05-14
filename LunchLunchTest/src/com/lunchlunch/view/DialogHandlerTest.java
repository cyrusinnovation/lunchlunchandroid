package com.lunchlunch.view;

import android.test.AndroidTestCase;

import com.lunchlunch.controller.MockActivity;

public class DialogHandlerTest extends AndroidTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(DialogHandlerInterface.class,
				DialogHandler.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArgs() throws Exception {
		MockDialogProvider alertDialogProvider = new MockDialogProvider();
		DialogHandler dialogHandler = new DialogHandler(alertDialogProvider);
		assertEquals(alertDialogProvider, dialogHandler.getDialogProvider());
	}

	public void testShowErrorDialog() throws Exception {
		MockDialogProvider dialogProvider = new MockDialogProvider();
		MockAlertDialogBuilder builderToProvide = new MockAlertDialogBuilder(
				mContext);
		dialogProvider.setBuilderToProvide(builderToProvide);

		DialogHandlerInterface dialogHandler = new DialogHandler(dialogProvider);

		MockActivity baseContext = new MockActivity();
		String expectedErrorMessage = "This is gonna be the error";
		dialogHandler.showErrorDialog(baseContext, expectedErrorMessage);

		assertEquals("Error", builderToProvide.getTitleSet());
		assertEquals("Ok", builderToProvide.getPositiveButtonText());
		assertEquals(expectedErrorMessage, builderToProvide.getMessageSet());
		assertEquals(baseContext,
				dialogProvider.getContextPassedToAlertDialogBuilder());
		assertTrue(builderToProvide.wasShowCalled());
	}

	public void testShowDatePickerDialog() throws Exception {
		MockDialogProvider dialogProvider = new MockDialogProvider();
		MockDatePickerDialog datePickerDialog = new MockDatePickerDialog(
				mContext);
		dialogProvider.setDatePickerToReturn(datePickerDialog);

		DialogHandlerInterface dialogHandler = new DialogHandler(dialogProvider);

		int day = 11;
		int month = 4;
		int year = 2000;
		MockOnDateSetListener dateSetListener = new MockOnDateSetListener();
		MockActivity baseContext = new MockActivity();
		dialogHandler.showDatePickerDialog(baseContext, dateSetListener, year,
				month, day);

		assertEquals(baseContext,
				dialogProvider.getBaseContextForProvideDatePicker());
		assertEquals(year, dialogProvider.getYearForProvideDatePicker());
		assertEquals(month, dialogProvider.getMonthForProvideDatePicker());
		assertEquals(day, dialogProvider.getDayForProvideDatePicker());
		assertEquals(dateSetListener,
				dialogProvider.getDateSetListenerForProvideDatePicker());

		assertTrue(datePickerDialog.wasShowCalled());
	}

	public void testShowTimePickerDialog() throws Exception {
		MockDialogProvider dialogProvider = new MockDialogProvider();
		MockTimePickerDialog timePicker = new MockTimePickerDialog(mContext);
		dialogProvider.setTimePickerToReturn(timePicker);

		DialogHandlerInterface dialogHandler = new DialogHandler(dialogProvider);

		MockActivity baseContext = new MockActivity();
		MockOnTimeSetListener mockOnTimeSetListener = new MockOnTimeSetListener();
		int minute = 6;
		int hour = 23;
		dialogHandler.showTimePickerDialog(baseContext, mockOnTimeSetListener,
				hour, minute);

		assertEquals(baseContext,
				dialogProvider.getBaseContextForProvideTimePicker());
		assertEquals(hour, dialogProvider.getHourForProvideTimePicker());
		assertEquals(minute, dialogProvider.getMinuteForProvideTimePicker());
		assertEquals(mockOnTimeSetListener,
				dialogProvider.getTimeSetListenerForProvideTimePicker());

	}
}

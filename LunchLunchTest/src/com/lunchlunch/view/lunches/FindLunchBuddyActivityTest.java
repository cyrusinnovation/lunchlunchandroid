package com.lunchlunch.view.lunches;

import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.R;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.view.DialogHandlerProviderTestUtility;
import com.lunchlunch.view.MockDialogHandler;

public class FindLunchBuddyActivityTest extends
		ActivityInstrumentationTestCase2<FindLunchBuddyActivity> {

	private MockDialogHandler dialogHandler;

	public FindLunchBuddyActivityTest() {
		super(FindLunchBuddyActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dialogHandler = new MockDialogHandler();
		DialogHandlerProviderTestUtility
				.setDialogHandlerToProvide(dialogHandler);
	}

	@Override
	protected void tearDown() throws Exception {
		DialogHandlerProviderTestUtility.resetDialogHandlerProvider();
		super.tearDown();
	}

	public void testOnCreateWillPopulateFieldsUsingPersonFromIntentExtras()
			throws Exception {
		String expectedFirstName = "Jonas";
		String expectedLastName = "Venture";
		String expectedEmail = "jvenjr@venture.net";
		Person lunchBuddy = new Person("2352", expectedFirstName,
				expectedLastName, expectedEmail);
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);
		FindLunchBuddyActivity activity = getActivity();

		TextView firstNameValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.firstNameTextValue));
		TextView lastNameValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.lastNameTextValue));
		TextView emailValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.emailTextValue));

		assertEquals(expectedFirstName, firstNameValueLabel.getText());
		assertEquals(expectedLastName, lastNameValueLabel.getText());
		assertEquals(expectedEmail, emailValueLabel.getText());
	}

	public void testOnSelectDateClicked() throws Exception {

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		FindLunchBuddyActivity activity = getActivity();
		activity.selectDateClicked(new TextView(activity));
		Calendar now = Calendar.getInstance();

		assertEquals(activity, dialogHandler.getBaseContextForShowDatePicker());
		assertEquals(now.get(Calendar.YEAR),
				dialogHandler.getYearForShowDatePicker());
		assertEquals(now.get(Calendar.DAY_OF_MONTH),
				dialogHandler.getDayForShowDatePicker());
		assertEquals(now.get(Calendar.MONTH),
				dialogHandler.getMonthForShowDatePicker());
	}

	public void testTheListenerUsedToBuildTheDatePickerWillUpdateDateLabelWhenFired()
			throws Throwable {

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		final FindLunchBuddyActivity activity = getActivity();
		TextView selectedDateText = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.selectedDateText));

		activity.selectDateClicked(new TextView(activity));

		assertEquals("", selectedDateText.getText());

		final OnDateSetListener onDateSetListener = dialogHandler
				.getDateSetListenerForShowDatePicker();

		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				onDateSetListener.onDateSet(new DatePicker(activity), 2005, 7,
						12);
			}
		});

		assertEquals("8/12/2005", selectedDateText.getText());

	}

	public void testOnSelectTimeClicked() throws Exception {

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		FindLunchBuddyActivity activity = getActivity();
		activity.selectTimeClicked(new TextView(activity));

		assertEquals(activity, dialogHandler.getBaseContextForShowTimePicker());
		assertEquals(12, dialogHandler.getHourForShowTimePicker());
		assertEquals(0, dialogHandler.getMinuteForShowTimePicker());
	}

	public void testTheListenerUsedToBuildTheTimePickerWillUpdateTimeLabelWhenFired()
			throws Throwable {

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		final FindLunchBuddyActivity activity = getActivity();
		TextView selectedTimeText = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.selectedTimeText));

		activity.selectTimeClicked(new TextView(activity));

		assertEquals("", selectedTimeText.getText());

		final OnTimeSetListener onTimeSetListener = dialogHandler
				.getTimeSetListenerForShowTimePicker();

		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				onTimeSetListener.onTimeSet(new TimePicker(activity), 14, 12);
			}
		});

		assertEquals("2:12 PM", selectedTimeText.getText());

	}
}

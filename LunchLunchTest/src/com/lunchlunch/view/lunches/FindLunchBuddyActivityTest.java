package com.lunchlunch.view.lunches;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.R;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.view.DialogHandlerProviderTestUtility;
import com.lunchlunch.view.MockDialogHandler;
import com.lunchlunch.view.MockMenuItem;
import com.lunchlunch.webcomm.lunch.LunchCreationHandler;
import com.lunchlunch.webcomm.lunch.LunchCreatorProviderTestUtility;
import com.lunchlunch.webcomm.lunch.MockLunchCreator;

public class FindLunchBuddyActivityTest extends
		ActivityInstrumentationTestCase2<FindLunchBuddyActivity> {

	private MockDialogHandler dialogHandler;
	private MockLunchCreator lunchCreator;

	public FindLunchBuddyActivityTest() {
		super(FindLunchBuddyActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dialogHandler = new MockDialogHandler();
		lunchCreator = new MockLunchCreator();
		LunchCreatorProviderTestUtility.setLunchCreatorToProvide(lunchCreator);
		DialogHandlerProviderTestUtility
				.setDialogHandlerToProvide(dialogHandler);
	}

	@Override
	protected void tearDown() throws Exception {
		DialogHandlerProviderTestUtility.resetDialogHandlerProvider();
		LunchCreatorProviderTestUtility.resetLunchCreatorProvider();
		dialogHandler = null;
		lunchCreator = null;
		super.tearDown();
	}

	public void testIsALunchCreationHandler() throws Exception {
		assertEquals(LunchCreationHandler.class,
				FindLunchBuddyActivity.class.getInterfaces()[0]);
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

	public void testClickingTheCreateLunchButton() throws Throwable {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		final FindLunchBuddyActivity activity = getActivity();
		final String date = "10/14/2015";
		final String time = "4:45 PM";
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {

				TextView selectedTimeText = LunchBuddyTestCase
						.assertIsOfTypeAndGet(TextView.class,
								activity.findViewById(R.id.selectedTimeText));
				TextView selectedDateText = LunchBuddyTestCase
						.assertIsOfTypeAndGet(TextView.class,
								activity.findViewById(R.id.selectedDateText));

				selectedDateText.setText(date);
				selectedTimeText.setText(time);
				activity.onOptionsItemSelected(new MockMenuItem(
						com.lunchlunch.R.id.createLunchButton));
			}
		});

		SimpleDateFormat dateMaker = new SimpleDateFormat("MM/dd/yyyy hh:mm a",
				Locale.getDefault());

		Lunch lunchForCreate = LunchBuddyTestCase.assertIsOfTypeAndGet(
				Lunch.class, lunchCreator.getLunchForCreate());
		assertEquals(loggedInPerson, lunchForCreate.getPerson1());
		assertEquals(lunchBuddy, lunchForCreate.getPerson2());
		assertEquals(dateMaker.parse(date + " " + time),
				lunchForCreate.getDateTime());
		assertEquals(activity, lunchCreator.getLunchCreationHandlerForCreate());
	}

	public void testClickingTheCreateLunchButtonWithABadDateWillShowErrorMessage()
			throws Throwable {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		final FindLunchBuddyActivity activity = getActivity();
		checkBadDateTimeWillShowErrorMessage(activity, "12/14/2015", "bad time");

		checkBadDateTimeWillShowErrorMessage(activity, "bad dates", "1:45 PM");
		checkBadDateTimeWillShowErrorMessage(activity, "", "1:45 PM");
		checkBadDateTimeWillShowErrorMessage(activity, "12/14/2015", "");
		checkBadDateTimeWillShowErrorMessage(activity, "", "");
	}

	private void checkBadDateTimeWillShowErrorMessage(
			final FindLunchBuddyActivity activity, final String date,
			final String time) throws Throwable {
		dialogHandler.reset();
		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {
				TextView selectedTimeText = LunchBuddyTestCase
						.assertIsOfTypeAndGet(TextView.class,
								activity.findViewById(R.id.selectedTimeText));
				TextView selectedDateText = LunchBuddyTestCase
						.assertIsOfTypeAndGet(TextView.class,
								activity.findViewById(R.id.selectedDateText));

				selectedDateText.setText(date);
				selectedTimeText.setText(time);
				activity.onOptionsItemSelected(new MockMenuItem(
						com.lunchlunch.R.id.createLunchButton));
			}
		});
		assertEquals(activity, dialogHandler.getBaseContextForLastErrorDialog());
		assertEquals("Please enter a date to schedule your lunch",
				dialogHandler.getErrorMessageForLastErrorDialog());
	}

	public void testClickingAnotherButtonWillNotCreateALunch() throws Throwable {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		final FindLunchBuddyActivity activity = getActivity();

		runTestOnUiThread(new Runnable() {
			@Override
			public void run() {

				activity.onOptionsItemSelected(new MockMenuItem(
						com.lunchlunch.R.id.dateLabel));
			}
		});

		assertNull(lunchCreator.getLunchForCreate());
	}

	public void testLunchCreationFailedWillShowErrorMessage() throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		FindLunchBuddyActivity activity = getActivity();
		activity.lunchCreationFailed();
		assertEquals(activity, dialogHandler.getBaseContextForLastErrorDialog());
		assertEquals(
				"Error communicating with web service, please ensure you are connected to the internet and try again",
				dialogHandler.getErrorMessageForLastErrorDialog());
	}

	public void testLunchCreatedWillFinishActivity() throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);

		Person lunchBuddy = new Person("2352", "Jonas", "Venture",
				"jvenjr@venture.net");
		Intent intent = new Intent();
		intent.putExtra(FindLunchBuddyActivity.LUNCH_BUDDY_KEY, lunchBuddy);
		setActivityIntent(intent);

		FindLunchBuddyActivity activity = getActivity();
		activity.lunchCreated();

		assertTrue(activity.isFinishing());
	}
}
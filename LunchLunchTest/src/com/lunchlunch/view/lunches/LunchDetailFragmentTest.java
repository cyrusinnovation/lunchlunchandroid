package com.lunchlunch.view.lunches;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.R;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.person.Person;
import com.lunchlunch.view.FragmentTestCase;

public class LunchDetailFragmentTest extends
		FragmentTestCase<LunchDetailActivity> {

	public LunchDetailFragmentTest() {
		super(LunchDetailActivity.class);
	}

	public void testOnCreateViewWillShowDetailsInLabelsOnActivity()
			throws Exception {
		Person loggedInPerson = new Person("_someId", "Joe", "Danger",
				"Jdang@yahoo.com");
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		Person person2 = new Person("otherdude", "Byron", "Orpheus",
				"byoph@prodigy.net");

		SimpleDateFormat dateMaker = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date date = dateMaker.parse("02-21-2001 16:35");

		Lunch lunch = new Lunch(loggedInPerson, person2, date);

		HashMap<String, Parcelable> extras = new HashMap<String, Parcelable>();
		extras.put(LunchDetailFragment.DETAILED_LUNCH_KEY, lunch);
		Fragment fragment = startFragmentWithExtras(new LunchDetailFragment(),
				extras);

		FragmentActivity activity = fragment.getActivity();
		LayoutInflater layoutInflater = new LayoutInflater(activity) {

			@Override
			public LayoutInflater cloneInContext(Context newContext) {
				return null;
			}

		};
		fragment.onCreateView(layoutInflater, null, null);
		TextView timeValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.timeValueLabel));
		TextView dateValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.dateValueLabel));
		TextView whomValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.whomValueLabel));

		assertEquals("Byron Orpheus", whomValueLabel.getText());
		assertEquals("2/21/2001", dateValueLabel.getText());
		assertEquals("4:35 PM", timeValueLabel.getText());

	}

	public void testOnCreateViewWillShowDetailsInLabelsOnActivity_LoggedInPersonIsPerson2()
			throws Exception {
		Person loggedInPerson = new Person("_someId", "Brock", "Samson",
				"bsam@osi.gov");
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		Person person1 = new Person("otherdude", "Rusty", "Venture",
				"tventure@venture.com");

		SimpleDateFormat dateMaker = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date date = dateMaker.parse("12-1-2021 9:35");

		Lunch lunch = new Lunch(person1, loggedInPerson, date);

		HashMap<String, Parcelable> extras = new HashMap<String, Parcelable>();
		extras.put(LunchDetailFragment.DETAILED_LUNCH_KEY, lunch);
		Fragment fragment = startFragmentWithExtras(new LunchDetailFragment(),
				extras);

		FragmentActivity activity = fragment.getActivity();
		LayoutInflater layoutInflater = new LayoutInflater(activity) {

			@Override
			public LayoutInflater cloneInContext(Context newContext) {
				return null;
			}

		};
		fragment.onCreateView(layoutInflater, null, null);
		TextView timeValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.timeValueLabel));
		TextView dateValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.dateValueLabel));
		TextView whomValueLabel = LunchBuddyTestCase.assertIsOfTypeAndGet(
				TextView.class, activity.findViewById(R.id.whomValueLabel));

		assertEquals("Rusty Venture", whomValueLabel.getText());
		assertEquals("12/1/2021", dateValueLabel.getText());
		assertEquals("9:35 AM", timeValueLabel.getText());

	}
}

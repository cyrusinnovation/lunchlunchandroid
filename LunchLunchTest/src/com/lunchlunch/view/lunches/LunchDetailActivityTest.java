package com.lunchlunch.view.lunches;

import java.util.Date;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.person.Person;

public class LunchDetailActivityTest extends
		ActivityInstrumentationTestCase2<LunchDetailActivity> {

	public LunchDetailActivityTest() {
		super(LunchDetailActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testOnCreateWillTakeLunchFromIntentAndPassItToTheLunchDetailFragment()
			throws Exception {

		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				LunchDetailActivity.class);
		Lunch lunch = new Lunch(new Person("dfsdf", "sdasda", "12412",
				"235235@gam.com"), new Person("5234", "2312sd", "dgasgD",
				"21421"), new Date(523523));
		intent.putExtra(LunchDetailFragment.DETAILED_LUNCH_KEY, lunch);
		setActivityIntent(intent);

		LunchDetailFragment fragment = LunchBuddyTestCase.assertIsOfTypeAndGet(
				LunchDetailFragment.class, getActivity()
						.getSupportFragmentManager().getFragments().get(0));
		assertEquals(
				lunch,
				fragment.getArguments().get(
						LunchDetailFragment.DETAILED_LUNCH_KEY));
	}

}

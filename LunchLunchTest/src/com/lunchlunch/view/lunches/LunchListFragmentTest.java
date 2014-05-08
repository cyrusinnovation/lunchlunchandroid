package com.lunchlunch.view.lunches;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;

import com.lunchlunch.LunchTestCase;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.view.FragmentTestCase;
import com.lunchlunch.webcomm.lunch.LunchHelperProviderTestUtility;
import com.lunchlunch.webcomm.lunch.LunchReceiver;
import com.lunchlunch.webcomm.lunch.MockLunchHelper;

public class LunchListFragmentTest extends FragmentTestCase<LunchListActivity> {

	public LunchListFragmentTest() {
		super(LunchListActivity.class);
	}

	private static MockLunchHelper lunchHelper;
	private LunchListFragment lunchListFragment;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		lunchHelper = new MockLunchHelper();
		LunchHelperProviderTestUtility.setLunchHelperToProvide(lunchHelper);

		LunchListFragment fragment = new LunchListFragment();
		this.lunchListFragment = (LunchListFragment) startFragment(fragment);

	}

	@Override
	protected void tearDown() throws Exception {
		lunchHelper = null;
		LunchHelperProviderTestUtility.resetLunchHelperProvider();
		super.tearDown();
	}

	public void testImplementsLunchReceiver() throws Exception {
		assertEquals(LunchReceiver.class,
				LunchListFragment.class.getInterfaces()[0]);
	}

	public void testWillUsesPersonFromTheSessionToGetLunchesOnFragmentCreation()
			throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		lunchListFragment.onCreate(null);
		assertEquals(loggedInPerson, lunchHelper.getPersonPassedIn());
		assertEquals(lunchListFragment, lunchHelper.getLunchReceiverPassedIn());

	}

	public void testLunchesRecievedWillPopulateListAdapterWithLunches()
			throws Exception {
		MockLunch lunch1 = new MockLunch();
		MockLunch lunch2 = new MockLunch();
		MockLunch lunch3 = new MockLunch();
		List<LunchInterface> lunchesReceived = new ArrayList<>();
		lunchesReceived.add(lunch1);
		lunchesReceived.add(lunch2);
		lunchesReceived.add(lunch3);
		lunchListFragment.lunchesReceived(lunchesReceived);

		ArrayAdapter<?> adapter = LunchTestCase.assertIsOfTypeAndGet(
				ArrayAdapter.class, lunchListFragment.getListAdapter());
		assertEquals(lunchListFragment.getActivity(), adapter.getContext());

		assertEquals(3, adapter.getCount());
		assertEquals(lunch1, adapter.getItem(0));
		assertEquals(lunch2, adapter.getItem(1));
		assertEquals(lunch3, adapter.getItem(2));

	}
}
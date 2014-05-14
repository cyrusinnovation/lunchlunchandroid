package com.lunchlunch.view.lunches;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.lunch.MockLunch;
import com.lunchlunch.model.person.MockPerson;
import com.lunchlunch.view.FragmentTestCase;
import com.lunchlunch.webcomm.lunch.LunchRetrieverProviderTestUtility;
import com.lunchlunch.webcomm.lunch.LunchReceiver;
import com.lunchlunch.webcomm.lunch.MockLunchHelper;

public class LunchListFragmentTest extends FragmentTestCase<LunchListActivity> {

	public class MockLunchActivity extends Activity implements
			LunchListFragment.Callbacks {

		private LunchInterface lunchPassedIn;

		@Override
		public void onItemSelect(LunchInterface lunch) {
			this.lunchPassedIn = lunch;
		}

		public LunchInterface getLunchPassedIn() {
			return lunchPassedIn;
		}

	}

	public LunchListFragmentTest() {
		super(LunchListActivity.class);
	}

	private static MockLunchHelper lunchHelper;
	private LunchListFragment lunchListFragment;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		lunchHelper = new MockLunchHelper();
		LunchRetrieverProviderTestUtility.setLunchHelperToProvide(lunchHelper);

		LunchListFragment fragment = new LunchListFragment();
		this.lunchListFragment = (LunchListFragment) startFragment(fragment);

	}

	@Override
	protected void tearDown() throws Exception {
		lunchHelper = null;
		LunchRetrieverProviderTestUtility.resetLunchHelperProvider();
		super.tearDown();
	}

	public void testImplementsLunchReceiver() throws Exception {
		assertEquals(LunchReceiver.class,
				LunchListFragment.class.getInterfaces()[0]);
	}

	@UiThreadTest
	public void testWillUsesPersonFromTheSessionToGetLunchesOnFragmentCreation()
			throws Exception {
		MockPerson loggedInPerson = new MockPerson();
		LunchBuddySession.SINGLETON.setLoggedInUser(loggedInPerson);
		lunchListFragment.onCreate(null);
		assertEquals(loggedInPerson, lunchHelper.getPersonPassedIn());
		assertEquals(lunchListFragment, lunchHelper.getLunchReceiverPassedIn());

	}

	@UiThreadTest
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

		ArrayAdapter<?> adapter = LunchBuddyTestCase.assertIsOfTypeAndGet(
				ArrayAdapter.class, lunchListFragment.getListAdapter());
		assertEquals(lunchListFragment.getActivity(), adapter.getContext());

		assertEquals(3, adapter.getCount());
		assertEquals(lunch1, adapter.getItem(0));
		assertEquals(lunch2, adapter.getItem(1));
		assertEquals(lunch3, adapter.getItem(2));

	}

	@UiThreadTest
	public void testOnListItemClickWillSendLunchThroughCallBack()
			throws Exception {
		MockLunch lunch1 = new MockLunch();
		MockLunch lunch2 = new MockLunch();
		MockLunch lunch3 = new MockLunch();
		List<LunchInterface> lunchesReceived = new ArrayList<>();
		lunchesReceived.add(lunch1);
		lunchesReceived.add(lunch2);
		lunchesReceived.add(lunch3);
		lunchListFragment.lunchesReceived(lunchesReceived);
		MockLunchActivity activity = new MockLunchActivity();
		lunchListFragment.onAttach(activity);

		lunchListFragment.onListItemClick(new ListView(getActivity()),
				new View(getActivity()), 1, 12412);

		assertEquals(lunch2, activity.getLunchPassedIn());

	}
}

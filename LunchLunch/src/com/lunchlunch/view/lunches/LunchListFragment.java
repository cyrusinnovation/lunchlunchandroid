package com.lunchlunch.view.lunches;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.webcomm.lunch.LunchReceiver;
import com.lunchlunch.webcomm.lunch.LunchRetrieverInterface;
import com.lunchlunch.webcomm.lunch.LunchRetrieverProvider;

public class LunchListFragment extends ListFragment implements LunchReceiver {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = nullCallBacks;

	private int mActivatedPosition = ListView.INVALID_POSITION;

	public interface Callbacks {
		public void onItemSelect(LunchInterface lunch);
	}

	private static Callbacks nullCallBacks = new Callbacks() {

		@Override
		public void onItemSelect(LunchInterface lunch) {

		}
	};

	private ArrayAdapter<LunchInterface> listAdapter;

	public LunchListFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listAdapter = new ArrayAdapter<LunchInterface>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, new ArrayList<LunchInterface>());
		LunchRetrieverInterface lunchHelper = LunchRetrieverProvider.SINGLETON
				.provideLunchRetriever();
		lunchHelper.getLunches(LunchBuddySession.SINGLETON.getUserLoggedIn(),
				this);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void lunchesReceived(List<LunchInterface> lunches) {
		listAdapter = new ArrayAdapter<LunchInterface>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, lunches);
		setListAdapter(listAdapter);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = nullCallBacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		mCallbacks.onItemSelect(listAdapter.getItem(position));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

}

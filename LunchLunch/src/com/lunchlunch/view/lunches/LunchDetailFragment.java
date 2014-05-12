package com.lunchlunch.view.lunches;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunchlunch.R;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.person.PersonInterface;

public class LunchDetailFragment extends Fragment {

	public static final String DETAILED_LUNCH_KEY = "lunch_detailed";

	public LunchDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lunch_detail,
				container, false);
		Bundle arguments = getArguments();
		if (arguments != null && arguments.containsKey(DETAILED_LUNCH_KEY)) {
			setDetailLabelValues(rootView);

		}
		return rootView;
	}

	private void setDetailLabelValues(View rootView) {
		Lunch lunch = getArguments().getParcelable(DETAILED_LUNCH_KEY);
		TextView dateValueLabel = (TextView) rootView
				.findViewById(R.id.dateValueLabel);
		TextView timeValueLabel = (TextView) rootView
				.findViewById(R.id.timeValueLabel);
		TextView whomValueLabel = (TextView) rootView
				.findViewById(R.id.whomValueLabel);

		PersonInterface lunchBuddy = findLunchBuddy(lunch);
		whomValueLabel.setText(lunchBuddy.getFirstName() + " "
				+ lunchBuddy.getLastName());
		SimpleDateFormat dateFormatter = new SimpleDateFormat("M/d/yyyy",
				Locale.getDefault());
		dateValueLabel.setText(dateFormatter.format(lunch.getDateTime()));

		SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a",
				Locale.getDefault());
		timeValueLabel.setText(timeFormatter.format(lunch.getDateTime()));
	}

	private PersonInterface findLunchBuddy(Lunch lunch) {
		if (lunch.getPerson1().equals(
				LunchBuddySession.SINGLETON.getUserLoggedIn())) {
			return lunch.getPerson2();
		} else {
			return lunch.getPerson1();
		}
	}
}

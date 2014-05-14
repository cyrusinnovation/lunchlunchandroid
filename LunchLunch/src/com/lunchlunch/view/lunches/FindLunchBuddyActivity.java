package com.lunchlunch.view.lunches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lunchlunch.R;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.Lunch;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.view.DialogHandlerInterface;
import com.lunchlunch.view.DialogHandlerProvider;
import com.lunchlunch.webcomm.lunch.LunchCreationHandler;
import com.lunchlunch.webcomm.lunch.LunchCreatorInterface;
import com.lunchlunch.webcomm.lunch.LunchCreatorProvider;

public class FindLunchBuddyActivity extends Activity implements
		LunchCreationHandler {

	public static final String LUNCH_BUDDY_KEY = "lunchBuddyFound";
	private DialogHandlerInterface dialogHandler;
	private PersonInterface lunchBuddy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dialogHandler = DialogHandlerProvider.SINGLETON.provideDialogHandler();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_find_lunch_buddy);
		lunchBuddy = getIntent().getExtras().getParcelable(LUNCH_BUDDY_KEY);
		TextView firstNameLabel = (TextView) findViewById(R.id.firstNameTextValue);
		TextView lastNameLabel = (TextView) findViewById(R.id.lastNameTextValue);
		TextView emailLabel = (TextView) findViewById(R.id.emailTextValue);

		firstNameLabel.setText(lunchBuddy.getFirstName());
		lastNameLabel.setText(lunchBuddy.getLastName());
		emailLabel.setText(lunchBuddy.getEmail());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.find_lunch_buddy, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.createLunchButton) {
			try {
				TextView selectedDateText = (TextView) findViewById(R.id.selectedDateText);
				TextView selectedTimeText = (TextView) findViewById(R.id.selectedTimeText);
				LunchCreatorInterface lunchCreator = LunchCreatorProvider.SINGLETON
						.provideLunchCreator();
				SimpleDateFormat dateMaker = new SimpleDateFormat(
						"MM/dd/yyyy hh:mm a", Locale.getDefault());
				Date date = dateMaker.parse(selectedDateText.getText() + " "
						+ selectedTimeText.getText());
				lunchCreator.createLunch(
						new Lunch(
								LunchBuddySession.SINGLETON.getUserLoggedIn(),
								lunchBuddy, date), this);
			} catch (ParseException e) {
				dialogHandler.showErrorDialog(this,
						"Please enter a date to schedule your lunch");
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public void selectDateClicked(View view) {

		Calendar now = Calendar.getInstance();
		dialogHandler.showDatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				TextView selectedDateText = (TextView) findViewById(R.id.selectedDateText);
				int monthThatIsNotZeroBased = monthOfYear + 1;
				String selectedDateString = monthThatIsNotZeroBased + "/"
						+ dayOfMonth + "/" + year;
				selectedDateText.setText(selectedDateString);

			}
		}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now
				.get(Calendar.DAY_OF_MONTH));
	}

	public void selectTimeClicked(View view) {
		int defaultHour = 12;
		int defaultMinute = 0;
		dialogHandler.showTimePickerDialog(this, new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);

				SimpleDateFormat formatter = new SimpleDateFormat("h:mm a",
						Locale.getDefault());
				String timeString = formatter.format(calendar.getTime());

				TextView selectedTimeText = (TextView) findViewById(R.id.selectedTimeText);
				selectedTimeText.setText(timeString);
			}
		}, defaultHour, defaultMinute);
	}

	@Override
	public void lunchCreated() {
		finish();
	}

	@Override
	public void lunchCreationFailed() {
		dialogHandler
				.showErrorDialog(
						this,
						"Error communicating with web service, please ensure you are connected to the internet and try again");

	}
}

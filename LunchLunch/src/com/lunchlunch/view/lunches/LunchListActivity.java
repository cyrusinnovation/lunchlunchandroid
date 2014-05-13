package com.lunchlunch.view.lunches;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherInterface;
import com.lunchlunch.controller.CommandDispatcherProvider;
import com.lunchlunch.controller.ShowBuddyFoundCommand;
import com.lunchlunch.controller.ShowLunchDetailCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.lunch.LunchInterface;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.findBuddy.FindBuddyHelperInterface;
import com.lunchlunch.webcomm.findBuddy.FindBuddyHelperProvider;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class LunchListActivity extends FragmentActivity implements
		LunchListFragment.Callbacks, PersonReceiver {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lunch_list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lunch_list_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.suggest_buddy_button) {
			FindBuddyHelperInterface findBuddyHelper = FindBuddyHelperProvider.SINGLETON
					.provideFindBuddyHelper();
			findBuddyHelper.findLunchBuddy(
					LunchBuddySession.SINGLETON.getUserLoggedIn(), this);
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public void onItemSelect(LunchInterface lunch) {
		ShowLunchDetailCommand showLunchDetailCommand = new ShowLunchDetailCommand(
				lunch, this, ActivityStarter.SINGLETON);
		CommandDispatcherInterface commandDispatcher = CommandDispatcherProvider.SINGLETON
				.provideCommandDispatcher();
		commandDispatcher.execute(showLunchDetailCommand);
	}

	@Override
	public void personReceived(PersonInterface person) {
		ShowBuddyFoundCommand command = new ShowBuddyFoundCommand(person, this,
				ActivityStarter.SINGLETON);
		CommandDispatcherInterface commandDispatcher = CommandDispatcherProvider.SINGLETON
				.provideCommandDispatcher();
		commandDispatcher.execute(command);
	}
}

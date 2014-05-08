package com.lunchlunch.view.lunches;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherInterface;
import com.lunchlunch.controller.CommandDispatcherProvider;
import com.lunchlunch.controller.ShowLunchDetailCommand;
import com.lunchlunch.model.lunch.LunchInterface;

public class LunchListActivity extends FragmentActivity implements
		LunchListFragment.Callbacks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lunch_list);

	}

	@Override
	public void onItemSelect(LunchInterface lunch) {
		ShowLunchDetailCommand showLunchDetailCommand = new ShowLunchDetailCommand(
				lunch, this, ActivityStarter.SINGLETON);
		CommandDispatcherInterface commandDispatcher = CommandDispatcherProvider.SINGLETON
				.provideCommandDispatcher();
		commandDispatcher.execute(showLunchDetailCommand);
	}
}

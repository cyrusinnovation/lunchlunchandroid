package com.lunchlunch.view.lunches;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.lunchlunch.R;

public class LunchDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lunch_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			Bundle arguments = new Bundle();
			arguments.putParcelable(
					LunchDetailFragment.DETAILED_LUNCH_KEY,
					getIntent().getParcelableExtra(
							LunchDetailFragment.DETAILED_LUNCH_KEY));
			LunchDetailFragment fragment = new LunchDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.lunch_detail_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			NavUtils.navigateUpTo(this, new Intent(this,
					LunchListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

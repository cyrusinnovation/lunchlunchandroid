package com.lunchlunch.view.login;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.lunchlunch.LunchBuddyApp;
import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherInterface;
import com.lunchlunch.controller.CommandDispatcherProvider;
import com.lunchlunch.controller.LoginCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.PersonReceiver;
import com.lunchlunch.webcomm.login.LoginHelperInterface;
import com.lunchlunch.webcomm.login.LoginHelperProvider;

import dagger.ObjectGraph;

public class Login extends Activity implements PersonReceiver {

	private ObjectGraph activityGraph;

	@Inject
	Provider<LoginHelperInterface> loginHelper;

	@Inject
	Provider<CommandDispatcherInterface> commandDispatcherProvider;

	public Login() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LunchBuddyApp application = (LunchBuddyApp) getApplication();
		activityGraph = application.getApplicationGraph().plus(
				new LoginHelperProvider(), new CommandDispatcherProvider());

		activityGraph.inject(this);
		setContentView(R.layout.fragment_login);

	}

	@Override
	protected void onDestroy() {

		activityGraph = null;

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void loginClicked(View view) {
		EditText emailTextField = (EditText) findViewById(R.id.emailTextField);
		loginHelper.get().login(emailTextField.getText().toString(), this);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void personReceived(PersonInterface person) {
		if (!person.equals(NullPerson.NULL)) {
			commandDispatcherProvider.get().execute(
					new LoginCommand(person, LunchBuddySession.SINGLETON, this,
							ActivityStarter.SINGLETON));
		}
	}

}

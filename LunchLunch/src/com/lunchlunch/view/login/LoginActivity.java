package com.lunchlunch.view.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.lunchlunch.R;
import com.lunchlunch.controller.ActivityStarter;
import com.lunchlunch.controller.CommandDispatcherInterface;
import com.lunchlunch.controller.CommandDispatcherProvider;
import com.lunchlunch.controller.LoginCommand;
import com.lunchlunch.model.LunchBuddySession;
import com.lunchlunch.model.person.NullPerson;
import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.view.DialogHandlerInterface;
import com.lunchlunch.view.DialogHandlerProvider;
import com.lunchlunch.webcomm.login.LoginHelperInterface;
import com.lunchlunch.webcomm.login.LoginHelperProvider;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class LoginActivity extends Activity implements PersonReceiver {

	public LoginActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_login);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void loginClicked(View view) {
		EditText emailTextField = (EditText) findViewById(R.id.emailTextField);
		LoginHelperInterface loginHelper = LoginHelperProvider.SINGLETON
				.provideLoginHelper();
		loginHelper.login(emailTextField.getText().toString(), this);

	}

	@Override
	public void personReceived(PersonInterface person) {
		if (!person.equals(NullPerson.NULL)) {
			CommandDispatcherInterface commandDispatcher = CommandDispatcherProvider.SINGLETON
					.provideCommandDispatcher();
			commandDispatcher.execute(new LoginCommand(person,
					LunchBuddySession.SINGLETON, this,
					ActivityStarter.SINGLETON));
		} else {
			DialogHandlerInterface dialogHandler = DialogHandlerProvider.SINGLETON
					.provideDialogHandler();
			dialogHandler
					.showErrorDialog(this, getString(R.string.login_error));
		}
	}

}

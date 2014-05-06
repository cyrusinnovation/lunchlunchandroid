package com.lunchlunch.view;

import android.app.AlertDialog.Builder;
import android.content.Context;

public class DialogHandler implements DialogHandlerInterface {

	private AlertDialogProviderInterface alertDialogProvider;

	public DialogHandler(AlertDialogProviderInterface alertDialogProvider) {
		this.alertDialogProvider = alertDialogProvider;
	}

	@Override
	public void showErrorDialog(Context baseContext, String errorMessage) {
		Builder builder = alertDialogProvider
				.provideAlertDialogBuilder(baseContext);
		builder.setTitle("Error");
		builder.setMessage(errorMessage);
		builder.setPositiveButton("Ok", null);
		builder.show();
	}

	public AlertDialogProviderInterface getAlertDialogProvider() {
		return alertDialogProvider;
	}
}

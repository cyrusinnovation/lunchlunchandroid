package com.lunchlunch.view;

import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogProvider implements AlertDialogProviderInterface {

	public static final AlertDialogProviderInterface SINGLETON = new AlertDialogProvider();

	private AlertDialogProvider() {
	}

	@Override
	public AlertDialog.Builder provideAlertDialogBuilder(Context context) {
		return new AlertDialog.Builder(context);
	}
}

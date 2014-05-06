package com.lunchlunch.view;

import android.app.AlertDialog;
import android.content.Context;

public interface AlertDialogProviderInterface {

	public abstract AlertDialog.Builder provideAlertDialogBuilder(
			Context context);

}
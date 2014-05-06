package com.lunchlunch.view;

import android.content.Context;

public class MockDialogHandler implements DialogHandlerInterface {

	private Context baseContextForLastErrorDialog;
	private String errorMessageForLastErrorDialog;

	@Override
	public void showErrorDialog(Context baseContext, String errorMessage) {
		this.baseContextForLastErrorDialog = baseContext;
		this.errorMessageForLastErrorDialog = errorMessage;

	}

	public Context getBaseContextForLastErrorDialog() {
		return baseContextForLastErrorDialog;
	}

	public String getErrorMessageForLastErrorDialog() {
		return errorMessageForLastErrorDialog;
	}

}

package com.lunchlunch.view;

import android.app.AlertDialog.Builder;
import android.content.Context;

public class MockAlertDialogProvider implements AlertDialogProviderInterface {

	private Context contextPassedToAlertDialogBuilder;
	private Builder builderToProvide;

	@Override
	public Builder provideAlertDialogBuilder(Context context) {
		this.contextPassedToAlertDialogBuilder = context;
		return builderToProvide;
	}

	public void setBuilderToProvide(Builder builderToProvide) {
		this.builderToProvide = builderToProvide;
	}

	public Context getContextPassedToAlertDialogBuilder() {
		return contextPassedToAlertDialogBuilder;
	}

}

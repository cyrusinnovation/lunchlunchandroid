package com.lunchlunch.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class MockAlertDialogBuilder extends AlertDialog.Builder {

	private boolean showWasCalled;
	private CharSequence titleSet;
	private CharSequence messageSet;
	private boolean cancelable;
	private CharSequence positiveButtonText;

	public MockAlertDialogBuilder(Context context) {
		super(context);
	}

	@Override
	public Builder setTitle(CharSequence title) {
		this.titleSet = title;
		return super.setTitle(title);
	}

	public CharSequence getTitleSet() {
		return titleSet;
	}

	@Override
	public Builder setMessage(CharSequence message) {
		this.messageSet = message;
		return super.setMessage(message);
	}

	public CharSequence getMessageSet() {
		return messageSet;
	}

	@Override
	public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
		this.positiveButtonText = text;
		return super.setPositiveButton(text, listener);
	}

	public CharSequence getPositiveButtonText() {
		return positiveButtonText;
	}

	@Override
	public AlertDialog show() {
		showWasCalled = true;
		return null;
	}

	@Override
	public Builder setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
		return super.setCancelable(cancelable);
	}

	public boolean isCancelable() {
		return cancelable;
	}

	public boolean wasShowCalled() {
		return showWasCalled;
	}
}

package com.lunchlunch.view;

public class DialogHandlerProvider {

	public static final DialogHandlerProvider SINGLETON = new DialogHandlerProvider();
	private DialogHandlerInterface dialogHandler;

	private DialogHandlerProvider() {
		dialogHandler = new DialogHandler(DialogProvider.SINGLETON);
	}

	public DialogHandlerInterface provideDialogHandler() {
		return dialogHandler;
	}

}

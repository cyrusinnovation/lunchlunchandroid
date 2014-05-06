package com.lunchlunch.view;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class DialogHandlerProvider {

	@Provides
	@Singleton
	public DialogHandlerInterface providerDialogHandler() {
		return new DialogHandler(AlertDialogProvider.SINGLETON);
	}

}

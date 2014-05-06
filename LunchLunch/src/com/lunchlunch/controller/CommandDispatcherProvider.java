package com.lunchlunch.controller;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class CommandDispatcherProvider {

	@Provides
	@Singleton
	CommandDispatcherInterface provideCommandDispatcher() {
		return new CommandDispatcher();
	}

}

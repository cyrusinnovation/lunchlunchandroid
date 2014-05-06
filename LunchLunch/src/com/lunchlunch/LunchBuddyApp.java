package com.lunchlunch;

import android.app.Application;
import dagger.ObjectGraph;

public class LunchBuddyApp extends Application {

	private ObjectGraph applicationGraph;

	@Override
	public void onCreate() {
		super.onCreate();
		applicationGraph = ObjectGraph.create(new AndroidModule(this));
	}

	public ObjectGraph getApplicationGraph() {
		return applicationGraph;
	}
}

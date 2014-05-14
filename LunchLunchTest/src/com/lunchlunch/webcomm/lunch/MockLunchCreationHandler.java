package com.lunchlunch.webcomm.lunch;

import java.util.concurrent.CountDownLatch;

public class MockLunchCreationHandler implements LunchCreationHandler {

	private CountDownLatch countdown;
	private boolean lunchCreatedWasCalled;
	private boolean lunchCreationFailedCalled;

	public MockLunchCreationHandler(CountDownLatch countdown) {
		this.countdown = countdown;
	}

	@Override
	public void lunchCreated() {
		lunchCreatedWasCalled = true;
		countdown.countDown();
	}

	public boolean lunchCreatedWasCalled() {
		return lunchCreatedWasCalled;
	}

	public boolean lunchCreationFailedWasCalled() {
		return lunchCreationFailedCalled;
	}

	@Override
	public void lunchCreationFailed() {
		lunchCreationFailedCalled = true;
		countdown.countDown();
	}

}

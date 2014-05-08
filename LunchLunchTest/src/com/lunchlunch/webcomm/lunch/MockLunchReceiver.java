package com.lunchlunch.webcomm.lunch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.lunchlunch.model.lunch.LunchInterface;

public class MockLunchReceiver implements LunchReceiver {

	private List<? extends LunchInterface> lunchesReceived;
	private CountDownLatch countdown;

	public MockLunchReceiver(CountDownLatch countdown) {
		this.countdown = countdown;
	}

	@Override
	public void lunchesReceived(List<LunchInterface> lunches) {
		this.lunchesReceived = lunches;
		countdown.countDown();

	}

	public List<? extends LunchInterface> getLunchesReceived() {
		return lunchesReceived;
	}

}

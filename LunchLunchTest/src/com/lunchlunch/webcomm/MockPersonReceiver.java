package com.lunchlunch.webcomm;

import java.util.concurrent.CountDownLatch;

import com.lunchlunch.model.person.PersonInterface;
import com.lunchlunch.webcomm.person.PersonReceiver;

public class MockPersonReceiver implements PersonReceiver {

	private PersonInterface personReceived;
	private CountDownLatch latch;

	public MockPersonReceiver(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void personReceived(PersonInterface person) {
		this.personReceived = person;
		latch.countDown();

	}

	public PersonInterface getPersonReceived() {
		return personReceived;
	}
}

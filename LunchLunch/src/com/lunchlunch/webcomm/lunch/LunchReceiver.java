package com.lunchlunch.webcomm.lunch;

import java.util.List;

import com.lunchlunch.model.lunch.LunchInterface;

public interface LunchReceiver {

	public void lunchesReceived(List<LunchInterface> lunches);
}

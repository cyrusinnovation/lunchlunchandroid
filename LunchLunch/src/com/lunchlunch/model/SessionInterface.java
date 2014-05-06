package com.lunchlunch.model;

import com.lunchlunch.model.person.PersonInterface;

public interface SessionInterface {

	public abstract PersonInterface getUserLoggedIn();

	public abstract void setLoggedInUser(PersonInterface loggedInPerson);

}
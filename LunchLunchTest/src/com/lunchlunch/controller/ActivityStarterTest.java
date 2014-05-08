package com.lunchlunch.controller;

import java.util.HashMap;

import android.content.Intent;
import android.os.Parcelable;

import com.lunchlunch.LunchBuddyTestCase;
import com.lunchlunch.model.person.Person;

public class ActivityStarterTest extends LunchBuddyTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(ActivityStarterInterface.class,
				ActivityStarter.class.getInterfaces()[0]);
	}

	public void testIsSingleton() throws Exception {
		assertEquals(0, ActivityStarter.class.getConstructors().length);
		assertIsOfTypeAndGet(ActivityStarter.class, ActivityStarter.SINGLETON);
	}

	public void testStartActivity() throws Exception {
		MockActivity baseActivity = new MockActivity();
		Class<MockActivity> targetActivityClass = MockActivity.class;
		ActivityStarter.SINGLETON.startActivity(baseActivity,
				targetActivityClass);
		Intent intent = assertIsOfTypeAndGet(Intent.class,
				baseActivity.getIntentToStart());
		assertEquals(baseActivity.getPackageName(), intent.getComponent()
				.getPackageName());
		assertEquals(targetActivityClass.getName(), intent.getComponent()
				.getClassName());
	}

	public void testStartActivityWithExtrasWillPutInParcels() throws Exception {
		MockActivity baseActivity = new MockActivity();
		Class<MockActivity> targetActivityClass = MockActivity.class;
		HashMap<String, Parcelable> extraInformation = new HashMap<String, Parcelable>();
		Person person1 = new Person("213", "fdsfw", "garbage", "values");
		Person person2 = new Person("moar", "extra", "garbage", "values");

		extraInformation.put("Key1", person1);
		extraInformation.put("Key2", person2);
		ActivityStarter.SINGLETON.startActivityWithExtras(baseActivity,
				targetActivityClass, extraInformation);
		Intent intent = assertIsOfTypeAndGet(Intent.class,
				baseActivity.getIntentToStart());

		assertEquals(baseActivity.getPackageName(), intent.getComponent()
				.getPackageName());
		assertEquals(targetActivityClass.getName(), intent.getComponent()
				.getClassName());

		assertEquals(person1, intent.getParcelableExtra("Key1"));
		assertEquals(person2, intent.getParcelableExtra("Key2"));
	}
}

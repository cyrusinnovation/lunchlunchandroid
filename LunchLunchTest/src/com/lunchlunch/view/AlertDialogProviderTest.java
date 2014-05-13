package com.lunchlunch.view;

import android.app.AlertDialog;
import android.test.AndroidTestCase;

import com.lunchlunch.LunchBuddyTestCase;

public class AlertDialogProviderTest extends AndroidTestCase {
	public void testIsOfInterface() throws Exception {
		assertEquals(AlertDialogProviderInterface.class,
				AlertDialogProvider.class.getInterfaces()[0]);
	}

	public void testSingleton() throws Exception {
		assertEquals(0, AlertDialogProvider.class.getConstructors().length);
		LunchBuddyTestCase.assertIsOfTypeAndGet(AlertDialogProvider.class,
				AlertDialogProvider.SINGLETON);
	}

	public void testProvideAlertDialogBuilder() throws Exception {
		LunchBuddyTestCase.assertIsOfTypeAndGet(AlertDialog.Builder.class,
				AlertDialogProvider.SINGLETON
						.provideAlertDialogBuilder(mContext));

	}
}

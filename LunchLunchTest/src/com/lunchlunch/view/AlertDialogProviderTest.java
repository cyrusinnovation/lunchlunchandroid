package com.lunchlunch.view;

import android.app.AlertDialog;

import com.lunchlunch.LunchBuddyTestCase;

public class AlertDialogProviderTest extends LunchBuddyTestCase {
	public void testIsOfInterface() throws Exception {
		assertEquals(AlertDialogProviderInterface.class,
				AlertDialogProvider.class.getInterfaces()[0]);
	}

	public void testSingleton() throws Exception {
		assertEquals(0, AlertDialogProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(AlertDialogProvider.class,
				AlertDialogProvider.SINGLETON);
	}

	public void testProvideAlertDialogBuilder() throws Exception {
		assertIsOfTypeAndGet(AlertDialog.Builder.class,
				AlertDialogProvider.SINGLETON
						.provideAlertDialogBuilder(mContext));

	}
}

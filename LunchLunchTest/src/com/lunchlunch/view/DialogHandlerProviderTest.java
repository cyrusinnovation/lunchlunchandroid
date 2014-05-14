package com.lunchlunch.view;

import com.lunchlunch.LunchBuddyTestCase;

public class DialogHandlerProviderTest extends LunchBuddyTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, DialogHandlerProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(DialogHandlerProvider.class,
				DialogHandlerProvider.SINGLETON);
	}

	public void testBuildDialogHandler() throws Exception {
		DialogHandler provider = assertIsOfTypeAndGet(DialogHandler.class,
				DialogHandlerProvider.SINGLETON.provideDialogHandler());
		assertEquals(DialogProvider.SINGLETON,
				provider.getDialogProvider());
		assertSame(provider,
				DialogHandlerProvider.SINGLETON.provideDialogHandler());
	}
}

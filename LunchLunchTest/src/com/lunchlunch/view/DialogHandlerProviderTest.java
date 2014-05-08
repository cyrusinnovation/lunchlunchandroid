package com.lunchlunch.view;

import com.lunchlunch.LunchTestCase;

public class DialogHandlerProviderTest extends LunchTestCase {

	public void testIsASingleton() throws Exception {
		assertEquals(0, DialogHandlerProvider.class.getConstructors().length);
		assertIsOfTypeAndGet(DialogHandlerProvider.class,
				DialogHandlerProvider.SINGLETON);
	}

	public void testBuildDialogHandler() throws Exception {
		DialogHandler provider = assertIsOfTypeAndGet(DialogHandler.class,
				DialogHandlerProvider.SINGLETON.providerDialogHandler());
		assertEquals(AlertDialogProvider.SINGLETON,
				provider.getAlertDialogProvider());
		assertSame(provider,
				DialogHandlerProvider.SINGLETON.providerDialogHandler());
	}
}

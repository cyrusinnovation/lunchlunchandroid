package com.lunchlunch.view;

import com.lunchlunch.LunchTestCase;

public class DialogHandlerProviderTest extends LunchTestCase {

	public void testBuildDialogHandler() throws Exception {
		DialogHandler provider = assertIsOfTypeAndGet(DialogHandler.class,
				new DialogHandlerProvider().providerDialogHandler());
		assertEquals(AlertDialogProvider.SINGLETON,
				provider.getAlertDialogProvider());
	}
}

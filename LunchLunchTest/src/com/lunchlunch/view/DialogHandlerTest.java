package com.lunchlunch.view;

import com.lunchlunch.LunchTestCase;
import com.lunchlunch.controller.MockActivity;

public class DialogHandlerTest extends LunchTestCase {

	public void testImplementsInterface() throws Exception {
		assertEquals(DialogHandlerInterface.class,
				DialogHandler.class.getInterfaces()[0]);
	}

	public void testCanGetConstructorArgs() throws Exception {
		MockAlertDialogProvider alertDialogProvider = new MockAlertDialogProvider();
		DialogHandler dialogHandler = new DialogHandler(alertDialogProvider);
		assertEquals(alertDialogProvider,
				dialogHandler.getAlertDialogProvider());
	}

	public void testShowErrorDialog() throws Exception {
		MockAlertDialogProvider alertDialogProvider = new MockAlertDialogProvider();
		MockAlertDialogBuilder builderToProvide = new MockAlertDialogBuilder(
				mContext);
		alertDialogProvider.setBuilderToProvide(builderToProvide);

		DialogHandlerInterface dialogHandler = new DialogHandler(
				alertDialogProvider);

		MockActivity baseContext = new MockActivity();
		String expectedErrorMessage = "This is gonna be the error";
		dialogHandler.showErrorDialog(baseContext, expectedErrorMessage);

		assertEquals("Error", builderToProvide.getTitleSet());
		assertEquals("Ok", builderToProvide.getPositiveButtonText());
		assertEquals(expectedErrorMessage, builderToProvide.getMessageSet());
		assertEquals(baseContext,
				alertDialogProvider.getContextPassedToAlertDialogBuilder());
		assertTrue(builderToProvide.wasShowCalled());

	}
}

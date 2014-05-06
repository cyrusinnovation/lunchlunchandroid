package com.lunchlunch;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public abstract class LunchTestCase extends AndroidTestCase {

	public static <T> T assertIsOfTypeAndGet(Class<T> expectedClass,
			Object actualObject) {
		Assert.assertNotNull("The object was null", actualObject);
		Assert.assertEquals(expectedClass, actualObject.getClass());
		if (expectedClass.equals(actualObject.getClass())) {
			return (T) expectedClass.cast(actualObject);
		} else {
			Assert.fail();
			return null;
		}

	}
}

package com.lunchlunch;

import junit.framework.Assert;
import junit.framework.TestCase;

public abstract class LunchBuddyTestCase extends TestCase {

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

	public static void checkEqualsAndHashCode(Object original,
			Object equalObject, Object... notEqualObjects) {
		assertEquals(original, equalObject);
		assertEquals(
				"If you override equals, be sure to override hashcode as well",
				original.hashCode(), equalObject.hashCode());
		for (Object object : notEqualObjects) {
			assertFalse(original.equals(object));
		}

	}
}

package com.lunchlunch.webcomm;

import java.io.ByteArrayInputStream;

import com.lunchlunch.LunchBuddyTestCase;

public class ResponseHelperTest extends LunchBuddyTestCase {

	public void testGetResponseContentsAsString() throws Exception {
		MockHttpResponse response = new MockHttpResponse();
		MockHttpEntity responseEntity = new MockHttpEntity();
		response.setEntity(responseEntity);

		String responseContents = "thisiswhatI'mputtingintotheresponseboogityboogityboo";
		ByteArrayInputStream responseContentStream = new ByteArrayInputStream(
				responseContents.getBytes());
		responseEntity.setContentToReturn(responseContentStream);

		assertEquals(responseContents,
				ResponseHelper.getResponseContentsAsString(response));

	}
}

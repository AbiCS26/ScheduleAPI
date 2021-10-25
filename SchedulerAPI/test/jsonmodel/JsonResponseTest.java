package jsonmodel;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class JsonResponseTest {
	JsonResponse jr = new JsonResponse();

	@Test
	void testForWithoutDataGenerateResponse() {
		JsonResponse j = jr.generateResponse(400, "error");
		Status s = (Status) j.getStatus();
		assertTrue(s.getCode() == 400);
		assertTrue(s.getMessage().contains("error"));

	}

	@Test
	void testForWithoutDataNullValuesGenerateResponse() {
		Exception exception = assertThrows(RuntimeException.class, () -> jr.generateResponse(400, null));

		String expectedMessage = "Message can be empty but cannot be null!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForWithDataGenerateResponse() {
		Object o = "AAA";
		JsonResponse j = jr.generateResponse(400, "error", o);
		Status s = (Status) j.getStatus();
		assertTrue(s.getCode() == 400);
		assertTrue(s.getMessage().contains("error"));
		assertTrue(j.getData().toString().contains("AAA"));

	}

	@Test
	void testForWithDataNullMessageGenerateResponse() {
		Object o = "AAA";
		Exception exception = assertThrows(RuntimeException.class, () -> jr.generateResponse(400, null, o));

		String expectedMessage = "Message can be empty but cannot be null!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForWithDataNullDataGenerateResponse() {
		Exception exception = assertThrows(RuntimeException.class, () -> jr.generateResponse(400, "aa", null));

		String expectedMessage = "Data cannot be null!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

}

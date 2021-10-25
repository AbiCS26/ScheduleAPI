package jsonmodel;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class StatusTest {

	@Test
	void testForGenerateStatus() {
		Status s = Status.generateStatus(100, "Error");
		assertTrue(s.getCode() == 100);
		assertTrue(s.getMessage().contains("Error"));
	}

	@Test
	void testForNegativeCode() {
		Exception exception = assertThrows(RuntimeException.class, () -> Status.generateStatus(-100, "Error"));

		String expectedMessage = "Enter valid status code!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}

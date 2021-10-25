package eventmodel;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class ParticipantsTest {

	@Test
	void testForNullNames() {
		Exception exception = assertThrows(RuntimeException.class, () -> Participants.createParticipants(null, null));

		String expectedMessage = "Enter Participants Name!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForZeroNames() {
		String[] n = {};
		String[] e = { "abc" };
		Exception exception = assertThrows(RuntimeException.class, () -> Participants.createParticipants(n, e));

		String expectedMessage = "Enter Participants Name!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForNullEmails() {
		String[] n = { "a" };

		Exception exception = assertThrows(RuntimeException.class, () -> Participants.createParticipants(n, null));

		String expectedMessage = "Enter Participants E-mail!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForZeroEmail() {
		String[] n = { "abc" };
		String[] e = {};
		Exception exception = assertThrows(RuntimeException.class, () -> Participants.createParticipants(n, e));

		String expectedMessage = "Enter Participants E-mail!!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

}

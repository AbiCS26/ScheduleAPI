package inputs;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import dummy.DummyPopulator;

class InputValidationTest {

	@Test
	void testForValidID() {
		DummyPopulator.populateDummyEvents();
		assertTrue(Validation.checkID("AA"));

	}

	@Test
	void testForInvalidID() {
		DummyPopulator.populateDummyEvents();
		assertFalse(Validation.checkID("A"));

	}

	@Test
	void testForCheckIDNullValue() {
		DummyPopulator.populateDummyEvents();

		assertFalse(Validation.checkID(null));
	}

	@Test
	void testForCheckIDEmptyValue() {
		DummyPopulator.populateDummyEvents();

		assertFalse(Validation.checkID(""));
	}

	@Test
	void testForCheckLongInput() {
		DummyPopulator.populateDummyEvents();
		assertTrue(Validation.checkLongInput(140));
	}

	@Test
	void testForCheckZeroLongInput() {
		DummyPopulator.populateDummyEvents();
		assertFalse(Validation.checkLongInput(0));

	}

	@Test
	void testForCheckNegativeLongInput() {
		DummyPopulator.populateDummyEvents();
		assertFalse(Validation.checkLongInput(-40));
	}

	@Test
	void testForCheckStringInput() {
		assertTrue(Validation.checkStringInput("AAA"));
	}

	@Test
	void testForNullStringInput() {
		assertFalse(Validation.checkStringInput(null));
	}

	@Test
	void testForEmptyStringInput() {
		assertFalse(Validation.checkStringInput(""));
	}

	@Test
	void testForWhiteSpacesStringInput() {
		assertFalse(Validation.checkStringInput("   "));
	}

	@Test
	void testForCheckDuration() {
		assertEquals(true, Validation.checkStringToNumber("123"));
	}

	@Test
	void testForZeroDuration() {
		assertEquals(false, Validation.checkStringToNumber("0"));
	}

	@Test
	void testForCheckDurationWithNegativeValues() {
		assertEquals(false, Validation.checkStringToNumber(" -120  "));

	}

	@Test
	void testForCheckDurationWithWhiteSpaces() {
		assertEquals(true, Validation.checkStringToNumber("  123  "));
	}
}

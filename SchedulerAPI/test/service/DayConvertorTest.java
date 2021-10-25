package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

@Deprecated
class DayConvertorTest {

	@Test
	void testForNullValue() throws ParseException {
		Exception exception = assertThrows(RuntimeException.class, () -> DayConvertor.convertDateToMillis(null));

		String expectedMessage = "EventTime cannot be null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForEmptyString() throws ParseException {
		Exception exception = assertThrows(RuntimeException.class, () -> DayConvertor.convertDateToMillis(""));

		String expectedMessage = "EventTime cannot be Empty";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForDifferentFormat() throws ParseException {
		assertThrows(ParseException.class, () -> DayConvertor.convertDateToMillis("M on,   1 1 Oct 2021 02:00 PM IST"));

	}

	@Test
	void testForWhiteSpacesInStartAndEnd() throws ParseException {
		assertEquals(1609144200000L, DayConvertor.convertDateToMillis(" Mon, 11 Oct 2021 02:00 PM IST "));

	}

}

package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dummy.DummyPopulator;
import eventmodel.Event;

class EventsRetrieverTest {

	@BeforeEach
	void populateEvents() {
		DummyPopulator.populateDummyEvents();
	}

	@AfterEach
	void deleteEvents() {
		EventsManager.clearAllEvents();
	}

	@Test
	void testForGetEventsByEmail() {
		ArrayList<Event> ar = EventsRetriever.getEventsByEmail("A@gmail.com");
		Event ev = (Event) ar.get(0);
		assertTrue(ev.getEmails().contains("A@gmail.com"));
	}

	@Test
	void testForGetEventsByEmailEmptyValue() {
		Exception exception = assertThrows(RuntimeException.class, () -> EventsRetriever.getEventsByEmail(""));

		String expectedMessage = "Enter Valid Mail";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForGetEventsByEmailNullValue() {
		Exception exception = assertThrows(RuntimeException.class, () -> EventsRetriever.getEventsByEmail(null));

		String expectedMessage = "Email Cannot be Null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForGetEventsByEmailWhiteSpaces() {
		ArrayList<Event> ar = EventsRetriever.getEventsByEmail("   A@gmail.com   ");

		Event ev = (Event) ar.get(0);
		assertTrue(ev.getEmails().contains("A@gmail.com"));
	}

	@Test
	void testForGetEventsByID() {
		Exception exception = assertThrows(RuntimeException.class, () -> EventsRetriever.getEventByID("AA3"));

		String expectedMessage = "Enter valid ID";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testForGetEventsByTimeRange() {
		ArrayList<Event> ar = EventsRetriever.getEventsByTimeRange(1000L, 1200L);
		assertEquals(2, ar.size());
	}

	@Test
	void testForGetEventsByTimeRangeNegativeValues() {
		Exception exception = assertThrows(RuntimeException.class,
				() -> EventsRetriever.getEventsByTimeRange(-16000L, 66000L));

		String expectedMessage = "Time Range should not be negative";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForGetEventsByTimeRange_EndTimeGreaterThanStartTime() {
		Exception exception = assertThrows(RuntimeException.class,
				() -> EventsRetriever.getEventsByTimeRange(16000L, 10000L));

		String expectedMessage = "End Time should be greater than Start time";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}

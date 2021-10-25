package service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dummy.DummyPopulator;
import eventmodel.Event;

class EventsManagerTest {

	@BeforeEach
	void populateEvents() {
		DummyPopulator.populateDummyEvents();
	}

	@AfterEach
	void deleteEvents() {
		EventsManager.clearAllEvents();
	}

	@Test
	void testForStoringEvent() {
		String[] names = { "Abi" };
		String[] emails = { "A@gmail.com" };
		Event e = DummyPopulator.createDummyEvent(names, emails, 16000, 30, "abc");
		String s = EventsManager.storeEvent(e);
		assertTrue(s.contains("abc"));
	}

	@Test
	void testForStoringNullEvent() {
		Exception exception = assertThrows(RuntimeException.class, () -> EventsManager.storeEvent(null));

		String expectedMessage = "Event cannot be null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForDeletingEvent() {
		EventsManager.deleteStoredEventById("AA");

		Exception exception = assertThrows(RuntimeException.class, () -> EventsRetriever.getEventByID("AA"));

		String expectedMessage = "Enter valid ID";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testForModifyingEvent() {
		String[] names = { "James" };
		String[] emails = { "A@gmail.com" };
		Event e = Event.createEvent(names, emails, 16000, 30, "abc");
		Event s = EventsManager.modifyEvent(e);
		assertTrue(s.getNames().contains("James"));
	}

	@Test
	void testForModifyingNullEvent() {
		Exception exception = assertThrows(RuntimeException.class, () -> EventsManager.modifyEvent(null));

		String expectedMessage = "Null Event cannot be modified";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}

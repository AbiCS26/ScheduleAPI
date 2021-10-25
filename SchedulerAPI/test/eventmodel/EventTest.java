package eventmodel;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import dummy.DummyPopulator;
import service.EventsManager;
import service.EventsRetriever;

class EventTest {
	EventsRetriever er = new EventsRetriever();
	EventsManager em = new EventsManager();

	@Test
	void testForCreateEvent() {
		String[] names = { "Abi" };
		String[] emails = { "A@gmail.com" };
		Event e1 = (Event) DummyPopulator.createDummyEvent(names, emails, 1600L, 60, "AA");
		assertTrue(e1.getID().contains("AA"));
	}

	@Test
	void testForModifyEvent() {
		DummyPopulator.populateDummyEvents();
		String[] names = { "Abi" };
		String[] emails = { "A@gmail.com" };
		Event e1 = (Event) Event.modifyEvent(names, emails, 1600L, 60, "BB", "IST");
		assertTrue(e1.getID().contains("BB"));
		assertTrue(e1.getNames().contains("Abi"));
	}
}

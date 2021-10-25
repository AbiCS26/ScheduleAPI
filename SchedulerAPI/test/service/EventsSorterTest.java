package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dummy.DummyPopulator;
import eventmodel.Event;

class EventsSorterTest {

	@BeforeEach
	void populateEvents() {
		DummyPopulator.populateDummyEvents();
	}

	@AfterEach
	void deleteEvents() {
		EventsManager.clearAllEvents();
	}

	@Test
	void testForSortByID() {
		ArrayList<Event> t = EventsSorter.sortByID();
		String s = t.get(0).getID();
		assertEquals("AA", s);
	}

	@Test
	void testForSortByNoOfPart() {
		List<Event> s = EventsSorter.sortByNoOfPart();
		int a = s.get(0).getNames().size();
		int b = s.get(1).getNames().size();
		int c = s.get(2).getNames().size();

		assertEquals(true, a < b);
		assertEquals(true, b < c);

	}

	@Test
	void testForSortByEventTime() {
		List<Event> s = EventsSorter.sortByEventTime();
		long a = s.get(0).getEventTime();
		long b = s.get(1).getEventTime();
		long c = s.get(2).getEventTime();

		assertEquals(true, a < b);
		assertEquals(true, b < c);
	}

	@Test
	void testForSortByDuration() {
		List<Event> s = EventsSorter.sortByDuration();
		long a = s.get(0).getDuration();
		long b = s.get(1).getDuration();
		long c = s.get(2).getDuration();

		assertEquals(true, a < b);
		assertEquals(true, b < c);
	}

	@Test
	void testForSortByCreatedTime() {
		List<Event> s = EventsSorter.sortByCreatedTime();
		long a = s.get(0).getCreatedTime();
		long b = s.get(1).getCreatedTime();
		long c = s.get(2).getCreatedTime();

		assertEquals(true, a <= b);
		assertEquals(true, b <= c);
	}

}

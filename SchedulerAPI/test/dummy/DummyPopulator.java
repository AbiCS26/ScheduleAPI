package dummy;

import eventmodel.Event;
import eventmodel.Participants;
import service.EventsManager;

public class DummyPopulator extends EventsManager {

	public static void populateDummyEvents() {
		String[] names = { "a", "c", "v" };
		String[] emails = { "A@gmail.com" };

		String[] names1 = { "a", "b" };
		String[] emails1 = { "A@gmail.com", "B@gmail.com" };

		String[] names2 = { "a" };
		String[] emails2 = { "A@gmail.com", "B@gmail.com", "C@gmail.com" };

		Event e1 = createDummyEvent(names1, emails1, 1600L, 60, "AA");
		EventsManager.storeEvent(e1);
		Event e2 = createDummyEvent(names2, emails2, 1200L, 90, "BB");
		EventsManager.storeEvent(e2);
		Event e = createDummyEvent(names, emails, 1000L, 30, "CC");
		EventsManager.storeEvent(e);
	}

	public static Event createDummyEvent(String[] names, String[] emails, long eventTime, int duration, String ID) {
		Event event = new Event();
		event.setParticipants(Participants.createParticipants(names, emails));
		event.setEventTime(eventTime);
		event.setDuration(duration);
		event.setID(ID);
		event.setCreatedTime();

		return event;
	}

}

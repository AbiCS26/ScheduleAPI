package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import eventmodel.Event;
import inputs.Validation;
import storage.DataStorage;

public class EventsRetriever {
	static DataStorage data = DataStorage.getInstance();
	static HashMap<String, Event> hm = data.getMap();

	static Function<String, String> makeUpperCase = String::toUpperCase;

	public static Collection<Event> getAllEvents() {
		return data.getMap().values();
	}

	public static ArrayList<Event> getEventsByEmail(String email) {
		if (email == null)
			throw new RuntimeException("Email Cannot be Null");

		if (!Validation.checkStringInput(email))
			throw new RuntimeException("Enter Valid Mail");

		ArrayList<Event> ar = new ArrayList<>();
		String str = email.trim();
		for (Map.Entry<String, Event> t : hm.entrySet()) {
			if (t.getValue().getEmails().contains(str))
				ar.add(t.getValue());
		}
		return ar;

	}

	public static Event getEventByID(String ID) {
		if (!Validation.checkID(ID))
			throw new RuntimeException("Enter valid ID");

		Event event = null;
		for (Map.Entry<String, Event> t : hm.entrySet()) {
			if (t.getKey().equals(ID))
				event = t.getValue();
		}
		return event;
	}

	public static ArrayList<Event> getEventsByTimeRange(long start, long end) {
		if (Validation.validateZeroOrNegative.test(start, end))
			throw new RuntimeException("Time Range should not be negative");
		if (start > end)
			throw new RuntimeException("End Time should be greater than Start time");

		ArrayList<Event> ar = new ArrayList<>();
		for (Map.Entry<String, Event> t : hm.entrySet()) {
			if (start <= t.getValue().getEventTime() && t.getValue().getEventTime() <= end)
				ar.add(t.getValue());
		}
		return ar;
	}

}

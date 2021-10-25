package service;

import java.util.HashMap;

import eventmodel.Event;
import inputs.Validation;
import storage.DataStorage;

public class EventsManager {
	static DataStorage data = DataStorage.getInstance();
	static HashMap<String, Event> hm = data.getMap();

	public static String storeEvent(Event event) {
		if (event == null)
			throw new RuntimeException("Event cannot be null");

		String id = event.getID();
		hm.put(event.getID(), event);

		return id;
	}

	public static Event deleteStoredEventById(String target) {
		if (!Validation.checkID(target))
			throw new RuntimeException("Enter valid ID");

		return hm.remove(target);
	}

	public static Event modifyEvent(Event event) {
		if (event == null)
			throw new RuntimeException("Null Event cannot be modified");

		hm.put(event.getID(), event);
		return event;
	}

	public static void clearAllEvents() {
		hm.clear();
	}

}

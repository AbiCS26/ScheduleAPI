package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import eventmodel.Event;
import storage.DataStorage;

public class EventsSorter {
	static DataStorage data = DataStorage.getInstance();
	static HashMap<String, Event> map = data.getMap();

	public static ArrayList<Event> sortByID() {
		TreeMap<String, Event> sortedMap = new TreeMap<>(map);
		ArrayList<Event> listItem = new ArrayList<>(sortedMap.values());
		return listItem;
	}

	public static List<Event> sortByNoOfPart() {
		List<Event> list = new ArrayList<>(map.values());
		Collections.sort(list, getParticipantsSorter());
		return list;
	}

	public static List<Event> sortByEventTime() {
		List<Event> list = new ArrayList<>(map.values());
		Collections.sort(list, getEventTimeSorter());
		return list;
	}

	public static List<Event> sortByDuration() {
		List<Event> list = new ArrayList<>(map.values());
		Collections.sort(list, getDurationSorter());
		return list;
	}

	public static List<Event> sortByCreatedTime() {
		List<Event> list = new ArrayList<>(map.values());
		Collections.sort(list, getCreatedTimeSorter());
		return list;
	}

	private static Comparator<Event> getParticipantsSorter() {
		return new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return Integer.valueOf(o1.getNames().size()).compareTo(Integer.valueOf(o2.getNames().size()));
			}
		};
	}

	private static Comparator<Event> getEventTimeSorter() {
		return new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return (Long.valueOf(o1.getEventTime())).compareTo(Long.valueOf(o2.getEventTime()));
			}
		};
	}

	private static Comparator<Event> getDurationSorter() {
		return new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return (Long.valueOf(o1.getDuration())).compareTo(Long.valueOf(o2.getDuration()));
			}
		};

	}

	private static Comparator<Event> getCreatedTimeSorter() {
		return new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return (Long.valueOf(o1.getCreatedTime())).compareTo(Long.valueOf(o2.getCreatedTime()));
			}
		};

	}

}

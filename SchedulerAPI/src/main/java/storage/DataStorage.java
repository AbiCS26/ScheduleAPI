package storage;

import java.util.HashMap;

import com.google.gson.Gson;

import eventmodel.Event;

public class DataStorage {
	Gson gson = new Gson();

	private HashMap<String, Event> hm = new HashMap<>();
	private static DataStorage instance = null;

	private DataStorage() {
	};

	public static DataStorage getInstance() {
		if (instance == null)
			instance = new DataStorage();
		return instance;
	}

	public HashMap<String, Event> getMap() {
		return hm;
	}
}
package inputs;

import java.util.HashMap;
import java.util.function.BiPredicate;

import com.google.gson.Gson;

import eventmodel.Event;
import service.EventsManager;
import storage.DataStorage;

public class Validation {
	static Gson gson = new Gson();
	static EventsManager sec = new EventsManager();

	static DataStorage data = DataStorage.getInstance();
	static HashMap<String, Event> hm = data.getMap();

	public static BiPredicate<Long, Long> validateZeroOrNegative = (input1, input2) -> input1 <= 0 || input2 <= 0;

	public static boolean checkID(String iD) {
		if (iD != null && !iD.isEmpty()) {
			String str = iD.trim();
			if (hm.keySet().contains(str))
				return true;
		}
		return false;

	}

	public static boolean checkLongInput(long l) {
		if (l != 0 && l > 0)
			return true;
		else
			return false;
	}

	public static boolean checkIntInput(int l) {
		if (l != 0 && l > 0)
			return true;
		else
			return false;
	}

	public static boolean checkStringInput(String s) {
		if (s != null && !s.isEmpty()) {
			String str = s.trim();
			if (!str.isEmpty())
				return true;
		}
		return false;
	}

	public static boolean checkStringToNumber(String s) {
		if (s != null && !s.isEmpty()) {
			String str = s.trim();
			if (!str.isEmpty())
				if (Long.parseLong(str) > 0)
					return true;
		}
		return false;

	}

}

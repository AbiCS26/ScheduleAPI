package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Deprecated
public class DayConvertor {
	public static long convertDateToMillis(String eventtime) throws ParseException {
		if (eventtime != null) {
			if (!eventtime.isEmpty()) {
				String eventTime = eventtime.trim();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM YYYY hh:mm aa z");

				Date date = sdf.parse(eventTime);

				long millis = date.getTime();
				return millis;
			} else {
				throw new RuntimeException("EventTime cannot be Empty");
			}

		} else {
			throw new RuntimeException("EventTime cannot be null");
		}
	}

	public static void main(String[] args) {
		long l = 1634640715071L;
		String timeZone = "UTC";
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone(timeZone));
		c.setTimeInMillis(l);

		System.out.println(c.getTimeZone().getID());
		System.out.println(c.getTimeInMillis());

	}
}

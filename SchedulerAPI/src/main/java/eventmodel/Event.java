package eventmodel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

import inputs.IDGeneration;
import service.EventsRetriever;

public class Event {

	private Participants participants;

	private String ID;
	private String timeZone;

	private long eventTime;
	private long duration;
	private long createdTime;
	private long modifiedTime;

	public String getTimeZone() {
		return timeZone;
	}

	public ArrayList<String> getNames() {
		return participants.getParticipantNames();
	}

	public Set<String> getEmails() {
		return participants.getParticipantEmails();
	}

	public long getEventTime() {
		return eventTime;
	}

	public void setEventTime(long eventTime, String timeZone2) {
		this.timeZone = timeZone2;

		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone(timeZone2));
		c.setTimeInMillis(eventTime);
		this.eventTime = c.getTimeInMillis();
	}

	public void setEventTime(long eventTime) {
		this.eventTime = eventTime;

	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		Duration dur = Duration.ofMinutes(duration);
		this.duration = dur.toMillis();

	}

	public String getID() {
		return ID;
	}

	public void setID() {

		this.ID = IDGeneration.generateID();
	}

	public void setID(String s) {

		this.ID = s;
	}

	public void setIDMod(String ID) {

		this.ID = ID;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime() {
		this.createdTime = System.currentTimeMillis();
	}

	private void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;

	}

	private void setmodifiedTime() {
		this.modifiedTime = System.currentTimeMillis();
	}

	public long getmodifiedTime() {
		return modifiedTime;
	}

	public void setParticipants(Participants participants) {
		this.participants = participants;
	}

	public static Event createEvent(String[] names, String[] email, long eventTime, int duration, String timeZone) {
		Event event = new Event();
		event.setParticipants(Participants.createParticipants(names, email));
		event.setEventTime(eventTime, timeZone);
		event.setDuration(duration);
		event.setID();
		event.setCreatedTime();

		return event;
	}

	public static Event modifyEvent(String[] names, String[] email, long eventTime, int duration, String ID,
			String timeZone) {
		long createdTime = EventsRetriever.getEventByID(ID).getCreatedTime();

		Event event = new Event();
		event.setParticipants(Participants.createParticipants(names, email));
		event.setEventTime(eventTime, timeZone);
		event.setDuration(duration);
		event.setIDMod(ID);
		event.setCreatedTime(createdTime);
		event.setmodifiedTime();

		return event;
	}

}

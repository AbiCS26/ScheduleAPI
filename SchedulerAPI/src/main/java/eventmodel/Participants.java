package eventmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Participants {

	private ArrayList<String> names = new ArrayList<>();
	private Set<String> emails = new HashSet<>();

	public static Participants createParticipants(String[] names, String[] email) {
		Participants p = new Participants();
		p.setParticipantNames(names);
		p.setParticipantEmails(email);
		return p;
	}

	public void setParticipantNames(String[] names) {
		if (names != null && names.length > 0) {
			for (String n : names) {
				this.names.add(n);
			}
		} else
			throw new RuntimeException("Enter Participants Name!!");
	}

	public void setParticipantEmails(String[] emails) {
		if (emails != null && emails.length > 0) {

			for (String n : emails) {
				this.emails.add(n);

			}
		} else
			throw new RuntimeException("Enter Participants E-mail!!");
	}

	public ArrayList<String> getParticipantNames() {
		return names;
	}

	public Set<String> getParticipantEmails() {
		return emails;
	}

}

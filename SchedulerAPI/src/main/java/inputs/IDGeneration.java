package inputs;

import java.util.UUID;

public class IDGeneration {

	public static String generateID() {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		return uuidAsString;
	}
}

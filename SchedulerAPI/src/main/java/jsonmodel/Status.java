package jsonmodel;

public class Status {
	private int code;
	private String message;

	public static Status generateStatus(int code, String message) {
		if (code >= 0) {
			Status status = new Status();
			status.setCode(code);
			status.setMessage(message);
			return status;
		} else
			throw new RuntimeException("Enter valid status code!!");
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String string) {
		message = string;
	}

	public void setCode(int i) {
		code = i;

	}
}

package jsonmodel;

public class JsonResponse {
	private Status status;
	private Object data;

	public JsonResponse generateResponse(int code, String message) {
		if (message == null)
			throw new RuntimeException("Message can be empty but cannot be null!!");

		JsonResponse jRes = new JsonResponse();
		jRes.setStatus(Status.generateStatus(code, message));
		return jRes;

	}

	public JsonResponse generateResponse(int code, String message, Object data) {
		if (data == null)
			throw new RuntimeException("Data cannot be null!!");
		if (message == null)
			throw new RuntimeException("Message can be empty but cannot be null!!");
		JsonResponse jRes = new JsonResponse();
		jRes.setStatus(Status.generateStatus(code, message));
		jRes.setData(data);
		return jRes;

	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

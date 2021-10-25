package controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eventmodel.Event;
import inputs.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jsonmodel.JsonResponse;
import service.EventsManager;
import service.JsonService;

public class EventsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		String StoredMessage = "Event Created And Saved Successfully!!  Event ID is ";

		try {
			JsonObject jsonObj = JsonService.getBodyAsJson(req);
			JsonArray name = jsonObj.getAsJsonArray("names");
			JsonArray email = jsonObj.getAsJsonArray("emails");

			String[] names = JsonService.getAsStringArray(name);
			String[] emails = JsonService.getAsStringArray(email);

			String eventtime = jsonObj.get("eventTime").getAsString();
			String dura = jsonObj.get("duration").getAsString();
			String timeZone = jsonObj.get("timeZone").getAsString();

			long eventTime;
			int duration;
			if (Validation.checkStringToNumber(eventtime)) {
				eventTime = Long.parseLong(eventtime);
			} else
				throw new RuntimeException("Enter valid Event Time!");
			if (Validation.checkStringToNumber(dura)) {

				duration = Integer.parseInt(dura);
			} else
				throw new RuntimeException("Enter valid Duration!");

			Event event1 = Event.createEvent(names, emails, eventTime, duration, timeZone);
			String id = EventsManager.storeEvent(event1);

			res.setStatus(HttpServletResponse.SC_CREATED);
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), StoredMessage + id, id);
			out.print(JsonService.getAsJson(jsonResponse));

		} catch (IllegalStateException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print(JsonService.getAsJson(jsonResponse.generateResponse(res.getStatus(), e.getMessage())));

		} catch (NullPointerException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print(JsonService
					.getAsJson(jsonResponse.generateResponse(res.getStatus(), "Some expected field is missing")));

		} catch (RuntimeException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print(JsonService.getAsJson(jsonResponse.generateResponse(res.getStatus(), e.getMessage())));

		} catch (IOException e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print(JsonService.getAsJson(
					jsonResponse.generateResponse(res.getStatus(), "Internal Error while reading request body")));
		}

	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		String target = req.getParameter("ID");

		try {
			Event e = EventsManager.deleteStoredEventById(target);
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), "Event Deleted Successfully", e);
			out.print(JsonService.getAsJson(jsonResponse));
		} catch (RuntimeException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print(JsonService.getAsJson(jsonResponse.generateResponse(res.getStatus(), e.getMessage())));
		}

	}

}

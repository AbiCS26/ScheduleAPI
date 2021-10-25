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

public class ModifyEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		String ModifiedMessage = "Modified The Event Successfully ";

		try {
			JsonObject jsonObject = JsonService.getBodyAsJson(req);
			String ID = jsonObject.get("ID").getAsString();
			if (Validation.checkID(ID)) {
				JsonArray name = jsonObject.getAsJsonArray("names");
				JsonArray email = jsonObject.getAsJsonArray("emails");

				String[] names = JsonService.getAsStringArray(name);
				String[] emails = JsonService.getAsStringArray(email);

				String eventtime = jsonObject.get("eventTime").getAsString();
				String dura = jsonObject.get("duration").getAsString();
				String timeZone = jsonObject.get("timeZone").getAsString();

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

				Event event1 = Event.modifyEvent(names, emails, eventTime, duration, ID, timeZone);
				Event e = EventsManager.modifyEvent(event1);
				jsonResponse = jsonResponse.generateResponse(res.getStatus(), ModifiedMessage, e);
				out.print(JsonService.getAsJson(jsonResponse));
			} else {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print(JsonService.getAsJson(jsonResponse.generateResponse(res.getStatus(), "Enter Valid ID")));
			}
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

}

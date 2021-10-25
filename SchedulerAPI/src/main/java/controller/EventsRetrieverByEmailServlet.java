package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jsonmodel.JsonResponse;
import service.EventsRetriever;
import service.JsonService;

public class EventsRetrieverByEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");

		PrintWriter out = res.getWriter();
		String email = req.getParameter("email");

		try {
			new EventsRetriever();
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), "Retrieved All Events By Given Emails",
					EventsRetriever.getEventsByEmail(email));
			out.print(JsonService.getAsJson(jsonResponse));
		} catch (RuntimeException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), e.getMessage());
			out.print(JsonService.getAsJson(jsonResponse));
		}

	}
}

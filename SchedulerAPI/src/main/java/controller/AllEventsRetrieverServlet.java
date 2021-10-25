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

public class AllEventsRetrieverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");

		PrintWriter out = res.getWriter();
		new EventsRetriever();
		jsonResponse = jsonResponse.generateResponse(res.getStatus(), "Retrieved All Events",
				EventsRetriever.getAllEvents());
		out.print(JsonService.getAsJson(jsonResponse));
	}

}

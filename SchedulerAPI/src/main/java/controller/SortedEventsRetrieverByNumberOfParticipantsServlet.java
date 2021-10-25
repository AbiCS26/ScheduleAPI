package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jsonmodel.JsonResponse;
import service.EventsSorter;
import service.JsonService;

public class SortedEventsRetrieverByNumberOfParticipantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");

		PrintWriter out = res.getWriter();
		jsonResponse = jsonResponse.generateResponse(res.getStatus(),
				"Retrieved Events Sorted By Number Of Participants", EventsSorter.sortByNoOfPart());

		out.print(JsonService.getAsJson(jsonResponse));
	}

}

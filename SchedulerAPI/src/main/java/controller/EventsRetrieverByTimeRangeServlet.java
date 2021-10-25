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

public class EventsRetrieverByTimeRangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");

		PrintWriter out = res.getWriter();

		String s = req.getParameter("startTime");
		String e = req.getParameter("endTime");

		long start = Long.parseLong(s);
		long end = Long.parseLong(e);

		jsonResponse = jsonResponse.generateResponse(res.getStatus(), "Retrieved All Events By Given Time Range",
				EventsRetriever.getEventsByTimeRange(start, end));
		out.print(JsonService.getAsJson(jsonResponse));

	}

}

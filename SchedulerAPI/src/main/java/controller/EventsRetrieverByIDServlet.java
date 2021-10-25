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

public class EventsRetrieverByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonResponse jsonResponse = new JsonResponse();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");

		PrintWriter out = res.getWriter();

		String ID = req.getParameter("ID");

		try {
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), "Retrieved The Event By Given ID",
					EventsRetriever.getEventByID(ID));
			out.print(JsonService.getAsJson(jsonResponse));
		} catch (RuntimeException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse = jsonResponse.generateResponse(res.getStatus(), e.getMessage());
			out.print(JsonService.getAsJson(jsonResponse));
		}
	}

}

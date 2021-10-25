package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dummy.DummyPopulator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class EventsRetrieverByTimeRangeServletTest {
	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testForEventsRetrieveByTimeRange() throws ServletException, IOException {

		DummyPopulator.populateDummyEvents();
		EventsRetrieverByTimeRangeServlet servlet = new EventsRetrieverByTimeRangeServlet();

		when(req.getParameter("startTime")).thenReturn("900");
		when(req.getParameter("endTime")).thenReturn("1200");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		servlet.doGet(req, res);
		String result = sw.getBuffer().toString();
		System.out.println(result);
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		assertEquals(2, jsonArray.size());

	}

}

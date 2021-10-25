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

class EventsRetrieverByEmailServletTest {
	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testForEventsRetrieveByEmail() throws ServletException, IOException {

		DummyPopulator.populateDummyEvents();
		EventsRetrieverByEmailServlet servlet = new EventsRetrieverByEmailServlet();

		when(req.getParameter("email")).thenReturn("A@gmail.com");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		servlet.doGet(req, res);
		String result = sw.getBuffer().toString();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		assertEquals(3, jsonArray.size());

	}

}

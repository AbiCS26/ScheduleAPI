package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dummy.DummyPopulator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EventsManager;
import service.EventsRetriever;

class EventsServletTest {
	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testForCreateEvent() throws ServletException, IOException {

		EventsServlet servlet = new EventsServlet();
		String jsonString = "{'names' : [ 'abi'],'emails' : ['abi@gmail.com'],'duration' : 30,'eventTime' : '1614500000','timeZone' : 'IST'}";

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);
		when(req.getReader()).thenReturn(new BufferedReader(new StringReader(jsonString)));

		servlet.doPost(req, res);

		int size = EventsRetriever.getAllEvents().size();
		assertEquals(1, size);

	}

	@Test
	void testForDeleteEvent() throws ServletException, IOException {
		DummyPopulator.populateDummyEvents();
		EventsServlet servlet = new EventsServlet();

		when(req.getParameter("ID")).thenReturn("AA");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		servlet.doDelete(req, res);
		int size = EventsRetriever.getAllEvents().size();
		assertEquals(2, size);
		EventsManager.clearAllEvents();
	}
}

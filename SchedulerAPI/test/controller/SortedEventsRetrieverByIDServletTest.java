package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dummy.DummyPopulator;
import eventmodel.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class SortedEventsRetrieverByIDServletTest extends Mockito {

	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testForSortByID() throws ServletException, IOException {
		Gson gson = new Gson();

		DummyPopulator.populateDummyEvents();
		SortedEventsRetrieverByIDServlet servlet = new SortedEventsRetrieverByIDServlet();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		servlet.doGet(req, res);
		String result = sw.getBuffer().toString();
		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		ArrayList<Event> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			Event e = gson.fromJson(jsonArray.get(i), Event.class);
			list.add(e);
		}

		String a = list.get(0).getID();
		String b = list.get(1).getID();
		String c = list.get(2).getID();

		assertEquals(-1, a.compareTo(b));
		assertEquals(-1, b.compareTo(c));

	}
}

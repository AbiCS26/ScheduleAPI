package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dummy.DummyPopulator;
import eventmodel.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class SortedEventsRetrieverByCreatedTimeServletTest extends Mockito {

	@Test
	void testForSortByCreatedTime() throws ServletException, IOException {
		Gson gson = new Gson();
		DummyPopulator.populateDummyEvents();

		SortedEventsRetrieverByCreatedTimeServlet servlet = new SortedEventsRetrieverByCreatedTimeServlet();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);

		servlet.doGet(request, response);
		String result = sw.getBuffer().toString();

		JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");

		List<Event> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			Event e = gson.fromJson(jsonArray.get(i), Event.class);
			list.add(e);
		}

		long a = list.get(0).getCreatedTime();
		long b = list.get(1).getCreatedTime();
		long c = list.get(2).getCreatedTime();

		assertEquals(true, a <= b);
		assertEquals(true, b <= c);

	}

}

package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;
import jsonmodel.JsonResponse;

public class JsonService {
	public static JsonObject getBodyAsJson(HttpServletRequest req) throws IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();

		JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
		return jsonObject;
	}

	public static String[] getAsStringArray(JsonArray name) {
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < name.size(); i++) {
			stringList.add(name.get(i).getAsString());
		}

		int size = stringList.size();
		String[] stringArray = stringList.toArray(new String[size]);
		return stringArray;
	}

	public static String getAsJson(JsonResponse jsonResponse) {
		Gson gson = new Gson();
		return gson.toJson(jsonResponse);
	}
}

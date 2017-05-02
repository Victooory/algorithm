package algorithm.some_implementation.GsonTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GsonStream {

	public List<String> readJsonStream(InputStream in) throws IOException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		List<String> messages = new ArrayList<String>();
		reader.beginArray();
		while (reader.hasNext()) {
			String message = gson.fromJson(reader, String.class);
			messages.add(message);
		}
		reader.endArray();
		reader.close();
		return messages;
	}

	public void writeJsonStream(OutputStream out, List<String> messages) throws IOException {
		Gson gson = new Gson();
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("	");
		writer.beginArray();
		for (String message : messages) {
			gson.toJson(message, String.class, writer);
		}
		writer.endArray();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader("/");
		char[] ch = new char[1024];
		fileReader.read(ch);
		
	}
}

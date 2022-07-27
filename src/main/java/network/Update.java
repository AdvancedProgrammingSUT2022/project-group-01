package network;

import com.thoughtworks.xstream.XStream;
import lombok.Getter;

import java.util.HashMap;

public class Update {
	@Getter
	private String action;
	private final HashMap<String, Object> data = new HashMap<>();

	public Update(String action) {
		this.action = action;
	}

	public static Update fromXML(String request) {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return (Update) xStream.fromXML(request);
	}

	public Update addData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Object get(String key) {
		return data.get(key);
	}
}

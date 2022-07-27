package network;

import com.thoughtworks.xstream.XStream;

import java.util.HashMap;

public class Request {
	private final HashMap<String, Object> data = new HashMap<>();
	private String action;
	private String token;

	public Request(String action) {
		this.action = action;
	}

	public static Request fromXML(String xml) {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return (Request) xStream.fromXML(xml);
	}

	public String getAction() {
		return action;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void addData(String key, Object value) {
		this.data.put(key, value);
	}

	public Object get(String key) {
		return data.get(key);
	}

	public String toXML() {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return xStream.toXML(this);
	}
}

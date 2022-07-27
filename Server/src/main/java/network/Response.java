package network;

import com.thoughtworks.xstream.XStream;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class Response {
	private final int status;
	private final String message;
	private final HashMap<String, Object> data = new HashMap<>();

	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Response addData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Object get(String key) {
		return this.data.get(key);
	}

	public static Response fromXML(String xml) {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return (Response) xStream.fromXML(xml);
	}

	public String toXML() {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return xStream.toXML(this);
	}
}

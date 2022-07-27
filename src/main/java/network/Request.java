package network;

import com.thoughtworks.xstream.XStream;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashMap;

@Setter
public class Request {
	private final HashMap<String, Object> data = new HashMap<>();
	private String action;
	private String token = "";

	public Request(String action){
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

	public Request addData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public String get(String key) {
		return (String) data.get(key);
	}

	public String toXML() {
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return xStream.toXML(this);
	}

	public static Request methodInvoke(Method method, Object... args) {
		Request request = new Request("Method Invoke");
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		request.addData("method", xStream.toXML(method));
		for (int i = 0; i < method.getParameterCount(); i++) {
			request.addData(method.getParameters()[i].getName(), args[i]);
		}
		return request;
	}
}

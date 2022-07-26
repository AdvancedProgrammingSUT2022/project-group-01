package network;

import java.util.HashMap;

public class Update {
	private String action;
	private final HashMap<String, Object> data = new HashMap<>();

	public Update(String action) {
		this.action = action;
	}

	public Update addData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public String get(String key) {
		return (String) data.get(key);
	}
}

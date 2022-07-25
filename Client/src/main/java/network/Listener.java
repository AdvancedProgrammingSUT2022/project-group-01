package network;

import java.io.DataInputStream;

public class Listener extends Thread {

	private final DataInputStream inputStream;

	public Listener(DataInputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String request = inputStream.readUTF();
				System.out.println("Some update with length " + request.length());
			} catch (Exception e) {
				System.out.println("Something went wrong");
				break;
			}
		}
	}
}

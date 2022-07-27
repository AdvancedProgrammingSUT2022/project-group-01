package controller;

import network.Listener;
import network.Request;
import network.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkController {
	public static Socket socket;
	public static DataInputStream inputStream;
	public static DataOutputStream outputStream;

	public static Response send(Request request) {
		try{
			outputStream.writeUTF(request.toXML());
			System.out.println("sent");
			/*
			int len = inputStream.readInt();
			byte[] res = new byte[len];
			for (int i = 0; i < len; i++)
				res[i] = inputStream.readByte();
//			while(inputStream.available())
			System.out.println("read " + len + " bytes");
			System.out.println("received");
			Response response = Response.fromXML(new String(res, StandardCharsets.UTF_8));
			System.out.println(response.getStatus());

			 */
			return Response.fromXML(inputStream.readUTF());
		} catch (Exception ignored){
			ignored.printStackTrace();
		}
		return null;
	}

	public static boolean connect(String domain, int PORT){
		try {
			socket = new Socket(domain, PORT);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			Socket listenerSocket = new Socket(domain, PORT);
			Listener listener = new Listener(new DataInputStream(listenerSocket.getInputStream()));
			listener.start();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void connectListener(){

	}

	public static boolean disconnect(){
		try {
			socket.close();
			return true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

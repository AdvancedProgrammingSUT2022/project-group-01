package network;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import controller.LobbyController;
import controller.UserController;
import lombok.Getter;
import lombok.SneakyThrows;
import model.Game;
import model.User;
import utils.Converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ClientHandler extends Thread {
	private static final Vector<Method> methods = new Vector<>();

	static {
		for (Class<?> clazz : List.of(UserController.class, LobbyController.class)) {
			for (Method method : clazz.getMethods()) {
				if (method.isAnnotationPresent(ClientAPI.class)) {
					methods.add(method);
				}
			}
		}
	}

	private final Socket socket;
//	private final Socket listenerSocket;
	private final DataInputStream inputStream;
	private final DataOutputStream outputStream;
//	private final DataOutputStream listenerOutputStream;
//	private final ScheduledExecutorService scheduler;
//	private final Scanner scanner;

	@XStreamOmitField
	private final Socket _listenerSocket;
	@XStreamOmitField
	private final DataOutputStream _listenerOutputStream;

	public static final ThreadLocal<Socket> listenerSocket = new ThreadLocal<>();
	public static final ThreadLocal<DataOutputStream> listenerOutputStream = new ThreadLocal<>();
	public static final ThreadLocal<User> currentUser = new ThreadLocal<>();
	public static final ThreadLocal<Game> game = new ThreadLocal<>();
	public static final HashMap<User, DataOutputStream> listeners = new HashMap<>();

	public DataOutputStream getListener(User user) {
		return outputStream;
	}

	public ClientHandler(Socket socket, Socket listenerSocket) throws IOException {
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		_listenerSocket = listenerSocket;
		_listenerOutputStream = new DataOutputStream(_listenerSocket.getOutputStream());
		ClientHandler.listenerSocket.set(listenerSocket);
		ClientHandler.listenerOutputStream.set(new DataOutputStream(listenerSocket.getOutputStream()));
		/*
		AtomicInteger x = new AtomicInteger(1);
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> {
			try {
				listenerOutputStream.writeUTF(Converter.toXML(new Request("Ping " + x + "th")));
				x.getAndIncrement();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, 0, 10, TimeUnit.SECONDS);
		*/
//		scheduler.shutdown();
	}

	public void terminate() {
//		scheduler.shutdown();
		try {
			socket.close();
			listenerSocket.get().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Method findMethod(String path) {
		for (Method method : methods) {
			if (method.getAnnotation(ClientAPI.class).path().equals(path)) {
				return method;
			}
		}
		return null;
	}

	@SneakyThrows
	private Object methodInvoker(Method method, Object[] args) {
		switch (args.length) {
			case 0:
				return method.invoke(null);
			case 1:
				return method.invoke(null, args[0]);
			case 2:
				return method.invoke(null, args[0], args[1]);
			case 3:
				return method.invoke(null, args[0], args[1], args[2]);
			case 4:
				return method.invoke(null, args[0], args[1], args[2], args[3]);
			case 5:
				return method.invoke(null, args[0], args[1], args[2], args[3], args[4]);
			default:
				return null;
		}

	}

	@Override
	public void run() {
		ClientHandler.listenerSocket.set(_listenerSocket);
		ClientHandler.listenerOutputStream.set(_listenerOutputStream);

		while (true) {
			try {
				Request request = (Request) Converter.fromXML(inputStream.readUTF());
				Response response;
				switch (request.getAction()) {
					case "Method Invoke":
						Method method = findMethod((String) request.get("method"));
						System.out.println(method);
						if (method == null)
							throw new Exception("Method not found");
						response = (Response) methodInvoker(method, (Object[]) request.get("args"));
						break;
					case "Create Game":
//						Game game = LobbyController.newGame(Integer.parseInt((String) request.get("map size")));
//						System.out.println("Game Created");
//						String xml = Converter.toXML(game);
//						response = new Response(200, "Game Created");
//						xml = Converter.toXML(response);
//						System.out.println("Request Handled");
//						byte[] b = xml.getBytes(StandardCharsets.UTF_8);
//						outputStream.writeInt(b.length);
//						outputStream.write(b);
//						System.out.println("Sent " + b.length);
					default:
						response = new Response(400, "Bad Request");
				}
				System.out.println(Converter.toXML(response.getData().get("user")));
				outputStream.writeUTF(Converter.toXML(response));
			} catch (Exception ignored) {
				ignored.printStackTrace();
				terminate();
				break;
			}
		}
	}
}

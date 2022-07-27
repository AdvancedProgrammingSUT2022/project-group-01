import controller.UserController;
import lombok.SneakyThrows;
import network.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {
	private static final int PORT = 9090;

	@SneakyThrows
	public static void main(String[] args) {
		UserController.init();

		ServerSocket serverSocket;
		serverSocket = new ServerSocket(PORT);
//        try {
//        } catch (IOException e){
//            System.out.printf("couldn't initialize Socket ...\nplease check system or change $PORT\ncurrent $PORT is %d\n", PORT);
//            return ;
//        }
		System.out.println("Server up");

		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("new Client");
			Socket listenerSocket = serverSocket.accept();

			ClientHandler clientHandler = new ClientHandler(socket, listenerSocket);
			clientHandler.start();
		}

	}

}

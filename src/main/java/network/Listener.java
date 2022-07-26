package network;

import com.thoughtworks.xstream.XStream;
import controller.Menus;
import controller.ProgramController;
import lombok.SneakyThrows;
import model.Game;
import model.ObjectID;

import java.io.DataInputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class Listener extends Thread {

	private final DataInputStream inputStream;

	public Listener(DataInputStream inputStream) {
		this.inputStream = inputStream;
	}

	@SneakyThrows
	public Game getGame() {
		int len = inputStream.readInt();
		byte[] res = new byte[len];
		for (int i = 0; i < len; i++)
			res[i] = inputStream.readByte();

		System.out.println("read " + len + " bytes");
		System.out.println("received");
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		return (Game) xStream.fromXML(new String(res, StandardCharsets.UTF_8));
	}

	@Override
	public void run() {
		while (true) {
			try {
				String request = inputStream.readUTF();
				Update update = Update.fromXML(request);
				if(update.getAction().equals("Game Start")){
					Game game = getGame();
					ProgramController.setGame(game);
					ProgramController.setCurrentMenu(Menus.GAME_MENU);
				} else handleUpdate(update);
			} catch (Exception e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
				break;
			}
		}
	}

	@SneakyThrows
	private Object methodInvoker(Method method, Object self, Object[] args) {
		System.out.println("invoked " + method.getName());
		for(int i = 0; i < args.length; i++) {
			if(args[i] instanceof ObjectID) {
				args[i] = ProgramController.getGame().getObject((ObjectID) args[i]);
			}
		}
		if(self instanceof ObjectID) {
			self = ProgramController.getGame().getObject((ObjectID) self);
		}
		System.out.println("32432532 ");
		System.out.println(method);
		System.out.println(self);
		switch (args.length) {
			case 0:
				return method.invoke(self);
			case 1:
				return method.invoke(self, args[0]);
			case 2:
				return method.invoke(self, args[0], args[1]);
			case 3:
				return method.invoke(self, args[0], args[1], args[2]);
			case 4:
				return method.invoke(self, args[0], args[1], args[2], args[3]);
			case 5:
				return method.invoke(self, args[0], args[1], args[2], args[3], args[4]);
			default:
				return null;
		}
	}

	private void handleUpdate(Update update) {
		if(!update.getAction().equals("Method Invoke"))
			return;
		Method method = (Method) update.get("method");
		methodInvoker(method, update.get("this"), (Object[]) update.get("args"));
	}
}

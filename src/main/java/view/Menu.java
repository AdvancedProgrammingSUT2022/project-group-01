package view;

import model.Command;
import java.util.*;

public abstract class Menu {

	private Vector<Command> commands;
	private static Scanner scanner;
	public static String getInput() {
		// TODO - implement view.Menu.getInput
		throw new UnsupportedOperationException();
	}

	public abstract void run();

}
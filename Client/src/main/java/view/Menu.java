package view;

import utils.CommandProcessor;
import utils.Commands;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

interface CommandAction{
	String action(HashMap<String, String> args) throws InvocationTargetException, IllegalAccessException;
}

public abstract class Menu {

	private final static Scanner scanner = new Scanner(System.in);

	public static String getInput() {
		return scanner.nextLine();
	}

	public static void handleCommand(HashMap<CommandAction, Commands> commands, String input){
		HashMap<String, String> result;
		for(Map.Entry<CommandAction, Commands> set: commands.entrySet()){
			if((result = CommandProcessor.extractCommand(input, set.getValue()))!=null){
				try {
					System.out.println(set.getKey().action(result));
				} catch (Exception ignored) {
					ignored.printStackTrace();
				}
				return;
			}
		}
		System.out.println("invalid command!");
	}
	public abstract void run();
	public abstract void initCommands();

}
package view;

import model.Command;
import utils.CommandProcessor;
import utils.Commands;

import java.util.*;

interface CommandAction{
	void action(HashMap<String, String> args);
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
				set.getKey().action(result);
				return;
			}
		}
		System.out.println("invalid command!");
	}
	public abstract void run();
	public abstract void initCommands();

}
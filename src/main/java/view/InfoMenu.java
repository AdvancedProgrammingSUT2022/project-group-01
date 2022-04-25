package view;

import controller.InfoMenuController;
import utils.Commands;

import java.util.HashMap;

public class InfoMenu extends Menu {
	private InfoMenuController infoMenuController;
	protected HashMap<CommandAction, Commands> commands;

	public InfoMenu(InfoMenuController infoMenuController){
		this.infoMenuController = infoMenuController;
	}

	@Override
	public void run() {

	}

	public void initCommands(){

	}
}

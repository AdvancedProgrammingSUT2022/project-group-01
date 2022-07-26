package controller;

import model.Game;
import model.tile.Tile;
import model.unit.Unit;
import network.AccessLevel;
import network.ClientAPI;
import network.ClientHandler;
import network.Response;

public class UnitController {

	@ClientAPI(ACCESS_LEVEL = AccessLevel.USER, path = "/game/unit/move")
	public static Response move(Unit unit, int position) {
		Game game = ClientHandler.game.get();
		Tile tile = game.getMap().getTileByNumber(position);
//		if (!unit.canGoTo(tile))
//			return new Response(400, "Can't reach there");
//		if(unit.outOfMP())
//			return new Response(400, "selected unit is out of movement point");
//		if(game.getCurrentPlayer().getSavedMap().getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR))
//			return new Response(400, "you don't see there");
		unit.moveTo(tile);
		return new Response(200, "Unit Moved");
	}
}

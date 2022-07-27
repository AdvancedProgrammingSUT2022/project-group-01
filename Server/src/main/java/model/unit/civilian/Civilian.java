package model.unit.civilian;

import lombok.SneakyThrows;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import network.Update;

public class Civilian extends Unit {

	public Civilian(UnitType type, Tile tile, Civilization civilization) {
		super(type, tile, civilization);
	}

	public static Civilian spawnCivilian(UnitType type, Tile tile, Civilization civilization) {
		if (type == UnitType.WORKER)
			return Worker.spawnWorker(type, tile, civilization);
		if (type == UnitType.SETTLER)
			return Settler.spawnSettler(type, tile, civilization);
		return new Civilian(type, tile, civilization);
	}

	public void captureUnit(Unit enemy) {
		ownerCivilization = enemy.getOwnerCivilization();
	}

	@SneakyThrows
	public void moveTo(Tile tile) {
		System.out.println("?!?!?!?!?!?");
		getGame().notifyPlayers(new Update("Method Invoke").addData("method", Unit.class.getMethod("moveTo", Tile.class))
				.addData("this", this.getId()).addData("args", new Object[]{tile.getId()}));
		updateMapAfterMove(getGame());
		currentTile.removeUnit(this);
		tile.setCivilianUnit((Civilian) this);
		this.currentTile = tile;
		tile.setCivilianUnit(this);
	}
}

package model;
// TODO initialize savedmap & savedmap & getSAvedMap
// TODO : ADDED map Center Tile

import lombok.Getter;
import model.civilization.Civilization;
import model.map.SavedMap;
import model.tile.Tile;

@Getter
public class Player {

	private User user;
	private Civilization civilization;
	private SavedMap savedMap;
	private Tile mapCenterTile;

	public Player(User user) {
		this.user = user;
	}

	public void initializeSavedMap(Game game) {
		savedMap = new SavedMap(game.getMap(), game.getMap().getMapSize());
	}

	public Civilization getCivilization() {
		return civilization;
	}

	public SavedMap getSavedMap() {
		return savedMap;
	}

	public Tile getMapCenterTile() {
		return mapCenterTile;
	}

	public void setMapCenterTile(Tile mapCenterTile) {
		this.mapCenterTile = mapCenterTile;
	}

	public void setCivilization(Civilization civilization) {
		this.civilization = civilization;
	}

}
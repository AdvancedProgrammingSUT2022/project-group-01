package model.improvement;

import model.Notification;
import model.ProgressState;
import model.TurnBasedLogic;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.tile.Tile;
import org.mockito.internal.matchers.Not;

import javax.swing.plaf.ColorUIResource;

public class ImprovementInventory {
	private ImprovementType improvement;
	private int turnsLeft;
	private ProgressState state;
	private Tile tile;

	public ImprovementInventory(Tile tile) {
		this.tile = tile;
		this.state = ProgressState.COMPLETE;
		this.turnsLeft = 0;
	}
	public void reset(ImprovementType improvement){
		this.improvement = improvement;
		this.turnsLeft = improvement.getProductionTime(this.tile);
		state = ProgressState.IN_PROGRESS;
	}
	public void nextTurn(){
		if(this.state.equals(ProgressState.IN_PROGRESS) | this.state.equals(ProgressState.DAMAGED)) {
			this.turnsLeft -= 1;
			if (this.turnsLeft == 0) {
				if(this.state.equals(ProgressState.IN_PROGRESS)) {
					if ((this.tile != null) && (this.tile.getCivilization() != null) && (this.tile.getAvailableResource() != null)){
						this.tile.getCivilization().addResource(this.tile.getAvailableResource(), tile.getAvailableResource().outputNumberToCivilization);
					}
				}
				this.state = ProgressState.COMPLETE;
				Notification notification = new Notification(tile, Notification.NotificationTexts.IMPROVEMENT_BUILT);
				tile.getCivilization().getNotificationInbox().addNotification(notification);
			}
		}
	}

	public void remove(){
		this.state = ProgressState.COMPLETE;
		this.improvement = null;
		this.turnsLeft = 0;
	}


	public ImprovementType getImprovement() {
		return improvement;
	}

	public void setImprovement(ImprovementType improvement) {
		this.improvement = improvement;
	}

	public int getTurnsLeft() {
		return turnsLeft;
	}

	public void setTurnsLeft(int turnsLeft) {
		this.turnsLeft = turnsLeft;
	}

	public ProgressState getState() {
		return state;
	}

	public void setState(ProgressState state) {
		this.state = state;
	}

	public void progress(){
		if(this.state != ProgressState.IN_PROGRESS)
			nextTurn();
	}
	public void repair(){
		if(this.state.equals(ProgressState.DAMAGED)) {
			nextTurn();
			}
	}
	public void damage(){this.state = ProgressState.DAMAGED;
	this.turnsLeft = 3;}

	public Currency getCurrency(){
		Currency currency = new Currency(0,0,0);
		if(this.improvement == null) return currency;
		if(!this.state.equals(ProgressState.COMPLETE)) return currency;
		currency.increase(improvement.goldYield, improvement.productionYield,improvement.foodYield);
		return currency;}}
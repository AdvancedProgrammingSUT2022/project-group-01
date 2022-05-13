package controller.unit;

import lombok.AllArgsConstructor;
import model.Game;
import model.unit.civilian.Settler;


@AllArgsConstructor
public class UnitController {
	private Game game;




	public String foundCity(Settler settler){
		if(!settler.canSettle())
			return "Cannot Settle new city here";

		settler.settle();
		return "City Founded";
	}
}

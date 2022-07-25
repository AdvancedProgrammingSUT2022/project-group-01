package model.civilization;

public class Currency {

	private double gold;
	private double product;
	private double food;

	public Currency(double gold, double product, double food) {
		this.gold = gold;
		this.product = product;
		this.food = food;
	}

	public void add(Currency currency) {//todo args?
		this.gold += currency.getGold();
		this.product += currency.getProduct();
		this.food += currency.getFood();
	}

	public void setValue(double gold, double product, double food) {
		this.gold = gold;
		this.product = product;
		this.food = food;
	}

	public void increase(double gold, double product, double food) {
		this.gold += gold;
		this.product += product;
		this.food += food;
	}

	public double getGold() {
		return gold;
	}

	public double getProduct() {
		return product;
	}

	public double getFood() {
		return food;
	}
}
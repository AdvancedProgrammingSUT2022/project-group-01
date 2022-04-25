package model.civilization;

public class Currency {

    private double gold;
    private double product;
    private double food;

    public Currency() {
        this.gold = 0;
        this.product = 0;
        this.food = 0;
    }

    public void add(Currency currency) {//todo args?
        this.gold += currency.getGold();
        this.product += currency.getProduct();
        this.food += currency.getFood();
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getProduct() {
        return product;
    }

    public void setProduct(double product) {
        this.product = product;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }
}
package view.components.city.productionpanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.building.BuildingType;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.unit.UnitType;
import utils.GraphicUtils;
import utils.StringUtils;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.awt.*;

public class ProductionRow extends BorderPane {
    
    private final Producible producible;
    private final Rectangle rectangle = new Rectangle(45,45);
    private final String cost;
    private final boolean isProducing;
    private final VBox vbox = new VBox();
    private final HBox hBox = new HBox();
    private final HBox buttonBox = new HBox();
    City city;
    public ProductionRow(Producible producible,boolean isProducing, String cost, City city){
        this.producible = producible;
        this.isProducing = isProducing;
        this.cost = cost;
        this.setLeft(hBox);
        this.setRight(buttonBox);
        this.city = city;
        init();
    }

    private void init(){
        initHBox();
        initRectangle();
        initName();
        if(isProducing)
            initTurnsCount();
        else
            initCost();
        this.setStyle("-fx-background-color: black");
        this.setMaxWidth(290);
        this.setPadding(new Insets(0,10,0,0));
        initButton();
    }

    private void initHBox(){
        hBox.setSpacing(5);
        hBox.setStyle("-fx-background-color: black");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(0,0,0,10));
    }

    private void initRectangle(){
        if(producible instanceof UnitType){
            String path = ((UnitType)producible).getCombatType().name()+"/"+((UnitType)producible).name()+".png";
            rectangle.setFill(GraphicUtils.getImage("/units/"+path));
        }else{
            String path = "/buildings/"+((BuildingType)producible).name()+".png";
            rectangle.setFill(GraphicUtils.getImage(path));
        }
        hBox.getChildren().add(rectangle);
    }

    private void initName(){
        String name;
        if(producible instanceof UnitType){
            name = StringUtils.convertToPascalCase(((UnitType)producible).name()).replaceAll("_"," ");
        }else{
            name = StringUtils.convertToPascalCase(((BuildingType)producible).name()).replaceAll("_", " ");
        }
        Label label = new Label(name);
        label.setStyle("-fx-text-fill: #ffeea0");
        Font font = new Font("Times New Roman", 13);
        label.setFont(font);
        vbox.getChildren().add(label);
        vbox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(vbox);
    }

    public void initTurnsCount(){
        Label label;
        String cost;
        if(this.cost.equals("Infinity"))
            cost = this.cost;
        else
            cost = String.valueOf(((int)Double.parseDouble(this.cost)));
        if(!cost.equals("Infinity") && Integer.parseInt(cost) <= 1)
            label = new Label(cost+ " Turn");
        else
            label = new Label(cost+ " Turns");
        label.setStyle("-fx-text-fill: #ffeea0");
        Font font = new Font("Times New Roman", 10);
        label.setFont(font);
        vbox.getChildren().add(label);
    }

    public void initCost(){
        HBox hbox = new HBox();
        Label label = new Label(cost);
        label.setStyle("-fx-text-fill: #ffeea0");
        Font font = new Font("Times New Roman", 10);
        label.setFont(font);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(5);
        hbox.getChildren().add(label);
        Rectangle rect = new Rectangle(10,10);
        rect.setFill(GraphicUtils.getImage("/city/coin.png"));
        hbox.getChildren().add(rect);
        vbox.getChildren().add(hbox);
    }

    private void initButton(){
        PurchaseButton pButton;
        if(isProducing) {
            pButton = new PurchaseButton("set/change production");
            pButton.setOnMouseClicked(mouseEvent -> {
                if(producible instanceof BuildingType && city.getBuildingInventory().hasBuilding((BuildingType)producible)){
                    new PopUp().run(PopUpStates.ERROR, "Already you have this item.");
                    return;
                }
                city.setNewProduction(producible);
                new PopUp().run(PopUpStates.OK, "Ok.");
            });
        }
        else {
            pButton = new PurchaseButton("Purchase");
            pButton.setOnMouseClicked(mouseEvent -> {
                int cost = producible.getCost(city);
                if(producible instanceof BuildingType && city.getBuildingInventory().hasBuilding((BuildingType)producible)){
                    new PopUp().run(PopUpStates.ERROR, "Already you have this item.");
                    return;
                }
                if(city.getCurrency().getGold() < cost){
                    new PopUp().run(PopUpStates.ERROR, "You don't have enough gold.");
                    return;
                }
                city.getCurrency().increase(-cost,0,0);
                city.getChangesOfCurrency().increase(-cost,0,0);
                producible.produce(city);
                new PopUp().run(PopUpStates.OK, "Successful.");
            });
        }

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(pButton);
    }





}

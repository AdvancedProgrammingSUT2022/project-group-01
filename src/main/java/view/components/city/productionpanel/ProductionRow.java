package view.components.city.productionpanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.building.BuildingType;
import model.civilization.production.Producible;
import model.unit.UnitType;
import utils.GraphicUtils;
import utils.StringUtils;

public class ProductionRow extends HBox {

    private final Producible producible;
    private final Rectangle rectangle = new Rectangle(45,45);
    private final String cost;
    private final boolean isProducing;
    private final VBox vbox = new VBox();
    public ProductionRow(Producible producible,boolean isProducing, String cost){
        this.producible = producible;
        this.isProducing = isProducing;
        this.cost = cost;
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
    }

    private void initHBox(){
        this.setSpacing(5);
        this.setStyle("-fx-background-color: black");
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(0,0,0,10));
    }

    private void initRectangle(){
        if(producible instanceof UnitType){
            String path = ((UnitType)producible).getCombatType().name()+"/"+((UnitType)producible).name()+".png";
            rectangle.setFill(GraphicUtils.getImage("/units/"+path));
        }else{
            String path = "/buildings/"+((BuildingType)producible).name()+".png";
            rectangle.setFill(GraphicUtils.getImage(path));
        }
        this.getChildren().add(rectangle);
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
        this.getChildren().add(vbox);
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





}

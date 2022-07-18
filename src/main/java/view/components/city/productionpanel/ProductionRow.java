package view.components.city.productionpanel;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import model.building.BuildingType;
import model.civilization.production.Producible;
import model.unit.UnitType;
import utils.GraphicUtils;

public class ProductionRow extends HBox {

    private Producible producible;
    private Rectangle rectangle = new Rectangle(30,30);
    private boolean isProducing;

    public ProductionRow(Producible producible,boolean isProducing){
        this.producible = producible;
        this.isProducing = isProducing;
    }

    private void init(){
        initRectangle();
    }

    private void initHBox(){

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

    private void initNameAndCost(){

    }





}

package view.components.city;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import lombok.Getter;
import utils.GraphicUtils;

@Getter
public enum Currency {
    FOOD("/city/currency/food.png","#007f0e"),
    PRODUCTION("/city/currency/production.png","#ff6a00"),
    GOLD("/city/currency/gold.png","#ddb800"),
    SCIENCE("/city/currency/science.png","#1843ff");

    private ImagePattern icon;
    private String style;

    Currency(String path, String color){
        this.icon = GraphicUtils.getImage(path);
        this.style = "-fx-text-fill: "+color;
    }
}

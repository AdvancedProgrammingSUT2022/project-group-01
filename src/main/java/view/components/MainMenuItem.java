package view.components;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class MainMenuItem extends Pane {
    private Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1,1,1);

    public MainMenuItem(String name){
        Polygon background = new Polygon(0,0,200,0,215,15,200,30,0,30);
        background.setStroke(Color.color(1,1,1,0.75));
        background.setEffect(new GaussianBlur());

        background.fillProperty().bind(Bindings.when(pressedProperty()).then(Color.color(0,0,0,0.75)).otherwise(Color.color(0,0,0,0.25)));

        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        //font
        text.setFill(Color.WHITE);

        text.effectProperty().bind(Bindings.when(hoverProperty()).then(shadow).otherwise(blur));

        getChildren().addAll(background,text);
    }

    public void setOnAction(Runnable action){
        setOnMouseClicked(e -> action.run());
    }

}

package view.components;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CivilizationTitle extends Pane {
    private Text text;

    public CivilizationTitle(String name){
        String a = "";
        for(char b : name.toCharArray())
            a += b + " ";
        text = new Text(name);
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30,Color.WHITE));
        getChildren().addAll(text);
    }

    public double getTitleWidth(){return text.getLayoutBounds().getWidth();}

    public double getTitleHeight(){return text.getLayoutBounds().getHeight();}
}

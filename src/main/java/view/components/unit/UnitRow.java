package view.components.unit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.unit.Unit;
import utils.GraphicUtils;
import utils.StringUtils;

public class UnitRow extends HBox {

    private Unit unit;
    private Rectangle rectangle = new Rectangle(30,30);
    private Label label;
    public UnitRow(Unit unit){
        this.unit = unit;
        init();
    }

    private void init(){
        initHBox();
        initRectangle();
        initLabel();
    }

    private void initHBox(){
        this.getStylesheets().add(getClass().getResource("/CSS/UnitPanel.css").toExternalForm());
        this.getStyleClass().add("h-box");
        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private void initRectangle(){
        String path = unit.getType().getCombatType().name()+"/"+unit.getType().name()+".png";
        rectangle.setFill(GraphicUtils.getImage("/units/"+path));
        this.getChildren().add(rectangle);
    }

    private void initLabel(){
        this.label = new Label(StringUtils.makeFirstCapital(unit.getType().name()));
        Font font = new Font("Times New Roman",16);
        label.setFont(font);
        label.getStyleClass().add("label");
        this.getChildren().add(label);
    }


}

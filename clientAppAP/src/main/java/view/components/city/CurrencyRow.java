package view.components.city;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import utils.StringUtils;


public class CurrencyRow extends BorderPane {
    private HBox first = new HBox();
    private HBox second = new HBox();
    private Rectangle icon = new Rectangle();
    private Currency currency;
    private double value;
    public CurrencyRow(Currency currency, double value){
        this.currency = currency;
        this.value = value;
        init();
    }

    public void init(){
        this.getStyleClass().add("currency-row");
        initFirstVBox();
        initSecondVBox();
        this.setLeft(first);
        this.setRight(second);
    }

    public void initFirstVBox(){
        first.setStyle("-fx-alignment: left");
        first.setStyle("-fx-spacing: 5;");
        first.setAlignment(Pos.CENTER);
        Rectangle rect = new Rectangle(30,30);
        rect.setFill(currency.getIcon());
        Label label = new Label(StringUtils.makeFirstCapital(currency.name()));
        label.setStyle(currency.getStyle());
        first.getChildren().addAll(rect, label);
    }

    public void initSecondVBox(){
        second.setStyle("-fx-alignment: right");
        second.setStyle("-fx-spacing: 3;");
        second.setAlignment(Pos.CENTER);
        Label label = new Label(StringUtils.makeNumberSigned(value));
        label.setStyle(currency.getStyle());
        second.getChildren().add(label);
    }
}

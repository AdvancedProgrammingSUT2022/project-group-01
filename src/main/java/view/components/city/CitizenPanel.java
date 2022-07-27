package view.components.city;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.civilization.Person;
import model.civilization.city.City;
import model.tile.Tile;
import utils.GraphicUtils;

public class CitizenPanel extends Pane {

    private final City city;

    public CitizenPanel(City city){
        this.city = city;
        init();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/Citizen.css").toExternalForm());
        this.getStyleClass().add("pane");
        initTop();
        initItems();
    }

    private void initTop(){
        HBox hbox = new HBox();
        Label label = new Label("Citizens");
        Font font = new Font("Times New Roman", 20);
        label.getStyleClass().add("label");
        label.setFont(font);
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.setMinWidth(230);
        hbox.setMaxWidth(230);
        hbox.setMinHeight(30);
        hbox.setMaxHeight(30);
        hbox.setTranslateX(10);
        hbox.setTranslateY(10);
        this.getChildren().add(hbox);
    }

    private void initItems(){
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox();
        vbox.getStyleClass().add("v-box");
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        scrollPane.setContent(vbox);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for(Person person : city.getPopulation()){
            vbox.getChildren().add(row(person));
        }
        scrollPane.setTranslateX(10);
        scrollPane.setTranslateY(50);
        this.getChildren().add(scrollPane);
    }

    private BorderPane row(Person person){
        BorderPane out = new BorderPane();
        out.setPadding(new Insets(5,5,5,5));
        out.getStyleClass().add("box");
        Rectangle rect = new Rectangle(20,20);
        rect.setFill(GraphicUtils.getImage("/city/population.png"));
        HBox left = new HBox();
        left.setAlignment(Pos.CENTER_LEFT);
        left.getChildren().add(rect);
        out.setLeft(left);
        out.setRight(hbox(person.getTile()));
        return out;
    }

    private HBox hbox(Tile tile){
        HBox out = new HBox();
        out.setAlignment(Pos.CENTER_RIGHT);

        if(tile == null){
            Label label = new Label("Unemployed");
            Font font = new Font("Times New Roman", 12);
            label.setFont(font);
            out.getChildren().add(label);
            return out;
        }

        Label goldLabel = new Label(String.valueOf((int)tile.getCurrency().getGold()));
        goldLabel.setStyle(Currency.GOLD.getStyle());
        Rectangle goldRect = new Rectangle(15,15);
        goldRect.setFill(Currency.GOLD.getIcon());

        Label productLabel = new Label(String.valueOf((int)tile.getCurrency().getProduct()));
        goldLabel.setStyle(Currency.PRODUCTION.getStyle());
        Rectangle productRect = new Rectangle(15,15);
        productRect.setFill(Currency.PRODUCTION.getIcon());

        Label foodLabel = new Label(String.valueOf((int)tile.getCurrency().getFood()));
        goldLabel.setStyle(Currency.FOOD.getStyle());
        Rectangle foodRect = new Rectangle(15,15);
        foodRect.setFill(Currency.FOOD.getIcon());

        out.setSpacing(5);
        out.getChildren().addAll(goldLabel,goldRect,productLabel,productRect,foodLabel,foodRect);
        return out;
    }



}
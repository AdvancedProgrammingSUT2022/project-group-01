package view.components.city;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.civilization.city.City;
import utils.GraphicUtils;

public class CityOverview extends VBox {

    City city;

    public CityOverview(City city) {
        this.city = city;
        init();
    }

    public void init() {
        this.getStylesheets().add(GraphicUtils.getResourcePath("/CSS/CityOverview.css"));
        this.getStyleClass().add("box");
        initItems();
    }

    private void initItems() {
        initPopulation();
        this.getChildren().add(new CurrencyRow(Currency.PRODUCTION, city.getChangesOfCurrency().getProduct()));
        this.getChildren().add(new CurrencyRow(Currency.FOOD, city.getChangesOfCurrency().getFood()));
        this.getChildren().add(new CurrencyRow(Currency.GOLD, city.getChangesOfCurrency().getGold()));
        this.getChildren().add(new CurrencyRow(Currency.SCIENCE, city.getBeaker()));
        initTileGrowth();
    }

    private void initPopulation() {
        BorderPane borderPane = new BorderPane();
        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(GraphicUtils.getImage("/city/population.png"));
        borderPane.setLeft(rect);
        VBox vbox = new VBox();
        //count of citizens
        Label citizenCount;
        int countOfCitizens = city.getPopulation().size();
        if (countOfCitizens <= 1)
            citizenCount = new Label(countOfCitizens + " Citizen");
        else
            citizenCount = new Label(countOfCitizens + " Citizens");
        Font font = new Font("Times New Roman", 18);
        citizenCount.setFont(font);
        citizenCount.setStyle("-fx-text-fill: #FFE7BE");
        vbox.getChildren().add(citizenCount);
        //remained turns
        Label turnsCount;
        int turns = city.getRemainedTurnToGrowth();
        if (turns <= 1)
            turnsCount = new Label(turns + " Turn until a new citizen is born.");
        else
            turnsCount = new Label(turns + " Turns until a new citizen is born.");
        turnsCount.setWrapText(true);
        turnsCount.setMaxWidth(100);
        turnsCount.setStyle("-fx-text-fill: #3E8F00");
        Font font2 = new Font("Times New Roman", 10);
        turnsCount.setFont(font2);
        vbox.getChildren().add(turnsCount);
        borderPane.setRight(vbox);
        borderPane.setStyle("-fx-spacing: 5;");
        this.getChildren().add(borderPane);
    }

    private void initTileGrowth() {
        BorderPane borderPane = new BorderPane();
        Rectangle rect = new Rectangle(45, 45);
        rect.setFill(GraphicUtils.getImage("/city/tile.png"));
        Label label;
        int remains = city.getRemainedTurnToGrowth();
        if (remains <= 1)
            label = new Label(remains + " Turn until border growth");
        else
            label = new Label(remains + " Turns until border growth");
        Font font = new Font("Times New Roman", 10);
        label.setWrapText(true);
        label.setMaxWidth(100);
        label.setFont(font);
        label.setStyle("-fx-text-fill: #c700ff");
        HBox hbox = new HBox();
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        borderPane.setRight(hbox);
        borderPane.setLeft(rect);
        this.getChildren().add(borderPane);
    }
}

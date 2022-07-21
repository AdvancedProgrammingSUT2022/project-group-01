package view.components.infoPanels;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.civilization.Civilization;
import view.components.ImagesAddress;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;


public class CityPanel {
    private Pane root = new Pane();
    private Civilization civilization;
    private Pane background;
    private ImageView cityPanelImage;
    private int index;
    public CityPanel(Pane background, Civilization civilization) {
        this.background = background;
        this.civilization = civilization;
        index = 0;
        initialize();
    }

    private void initialize(){
        root = new Pane();
        root.setPrefHeight(231);
        root.setPrefWidth(482);
        root.setStyle("-fx-background-color: transparent");
        halfInitializer();
        openPanel();
    }

    private void halfInitializer(){
        cityPanelImage = new ImageView(ImagesAddress.CITY_PANEL.getImage());
        cityPanelImage.setFitHeight(231);
        cityPanelImage.setFitWidth(484);
        cityPanelImage.setLayoutX(-12);
        cityPanelImage.setLayoutY(4);
        cityPanelImage.setPickOnBounds(true);
        cityPanelImage.setPreserveRatio(true);
        root.getChildren().add(cityPanelImage);
        onMouseClick();
        if(civilization.getCities().isEmpty()) {
            new PopUp().run(PopUpStates.WARNING,"No cities in this civilization");
            return;
        }
        addCityName();
        addCityData();
        closeButton();
    }

    private void addCityName(){
        Label name = new Label(civilization.getCities().get(index).getName());
        name.setLayoutX(96);
        name.setLayoutY(26);
        name.setPrefHeight(26);
        name.setPrefWidth(245);
        name.setTextFill(Color.WHITESMOKE);
        name.setFont(Font.font("AppleGothic Regular",17));
        root.getChildren().add(name);
    }

    private void addCityData(){
        Label population = new Label("Population: ");
        population.setLayoutX(97);
        population.setLayoutY(71);
        population.setTextFill(Color.WHITESMOKE);
        Label populationValue = new Label(String.valueOf(civilization.getCities().get(index).getPopulation().size()));
        populationValue.setLayoutX(200);
        populationValue.setLayoutY(71);
        populationValue.setTextFill(Color.WHITESMOKE);
        Label defence = new Label("Defence: ");
        defence.setLayoutX(297);
        defence.setLayoutY(71);
        defence.setTextFill(Color.WHITESMOKE);
        Label defenceValue = new Label(String.valueOf(civilization.getCities().get(index).getDefencePower()));
        defenceValue.setLayoutX(380);
        defenceValue.setLayoutY(71);
        defenceValue.setTextFill(Color.WHITESMOKE);
        Label food = new Label("Food: ");
        food.setLayoutX(97);
        food.setLayoutY(105);
        food.setTextFill(Color.WHITESMOKE);
        Label foodValue = new Label(String.valueOf(civilization.getCities().get(index).getCurrency().getFood()));
        foodValue.setLayoutX(200);
        foodValue.setLayoutY(105);
        foodValue.setTextFill(Color.WHITESMOKE);
        Label beaker = new Label("Beaker: ");
        beaker.setLayoutX(297);
        beaker.setLayoutY(105);
        beaker.setTextFill(Color.WHITESMOKE);
        Label beakerValue = new Label(String.valueOf(civilization.getCities().get(index).getBeaker()));
        beakerValue.setLayoutX(380);
        beakerValue.setLayoutY(105);
        beakerValue.setTextFill(Color.WHITESMOKE);
        Label gold = new Label("Gold: ");
        gold.setLayoutX(97);
        gold.setLayoutY(139);
        gold.setTextFill(Color.WHITESMOKE);
        Label goldValue = new Label(String.valueOf(civilization.getCities().get(index).getCurrency().getGold()));
        goldValue.setLayoutX(200);
        goldValue.setLayoutY(139);
        goldValue.setTextFill(Color.WHITESMOKE);
        Label production = new Label("Production: ");
        production.setLayoutX(297);
        production.setLayoutY(139);
        production.setTextFill(Color.WHITESMOKE);
        Label productionValue = new Label(String.valueOf(civilization.getCities().get(index).getCurrency().getProduct()));
        productionValue.setLayoutX(380);
        productionValue.setLayoutY(139);
        productionValue.setTextFill(Color.WHITESMOKE);
        Label product = new Label("Product: ");
        product.setLayoutX(97);
        product.setLayoutY(173);
        product.setTextFill(Color.WHITESMOKE);
        root.getChildren().addAll(population,populationValue,defence,defenceValue,food,foodValue,beaker,beakerValue,gold,goldValue,production,productionValue,product);
        if(civilization.getCities().get(index).getProductionInventory().getCurrentProduction() != null){
            Label productValue = new Label(String.valueOf(civilization.getCities().get(index).getProductionInventory().getCurrentProduction()));
            productValue.setLayoutX(200);
            productValue.setLayoutY(173);
            productValue.setTextFill(Color.WHITESMOKE);
            Label timeLeft = new Label("Time left: ");
            timeLeft.setLayoutX(297);
            timeLeft.setLayoutY(173);
            timeLeft.setTextFill(Color.WHITESMOKE);
            Label timeLeftValue = new Label(String.valueOf(civilization.getCities().get(index).getProductionInventory().getRemainedTurns()));
            timeLeftValue.setLayoutX(380);
            timeLeftValue.setLayoutY(173);
            timeLeftValue.setTextFill(Color.WHITESMOKE);
            root.getChildren().addAll(productValue,timeLeft,timeLeftValue);
        }else{
            Label productValue = new Label("None");
            productValue.setLayoutX(200);
            productValue.setLayoutY(173);
            productValue.setTextFill(Color.WHITESMOKE);
            root.getChildren().add(productValue);
        }

    }

    private void goToNext(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(400),root);
        transition.setToX(-500);
        transition.play();
        transition.setOnFinished(e -> {
            root.getChildren().clear();
            int next = index + 1;
            if(civilization.getCities().size() > next)
                index = next;
            else index = 0;
            halfInitializer();
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),root);
            translateTransition.setToX(0);
            translateTransition.play();
        });
    }

    private void openPanel(){
        background.getChildren().add(root);
        root.setTranslateY(40);
        root.setTranslateX(-500);
        TranslateTransition transition = new TranslateTransition(Duration.millis(400),root);
        transition.setToX(0);
        transition.play();
    }

    private void onMouseClick(){
        cityPanelImage.setOnMouseClicked(e -> {
            goToNext();
        });
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(428);
        close.setLayoutY(13);
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        root.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            background.getChildren().remove(root);
        });
    }
}

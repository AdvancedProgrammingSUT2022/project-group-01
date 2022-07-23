package view.components.infoPanels;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.Player;
import model.civilization.Civilization;
import model.civilization.city.City;
import view.components.ImagesAddress;
import view.components.mapComponents.GameMapController;
import view.components.popup.PopUp;

import java.util.ArrayList;
import java.util.Comparator;

public class DemographicPanel {
    private Pane root = new Pane();
    private GameMapController gameMapController;
    private Pane background;
    private ImageView demographicPanelImage;
    private Civilization civilization;
    private int index;
    private Label title;
    private Label best;
    private Label score;
    private Label worst;
    private Label average;
    private Label rank;
    public DemographicPanel(Pane background, GameMapController gameMapController) {
        this.background = background;
        this.gameMapController = gameMapController;
        this.civilization = gameMapController.getGameMenuController().getGame().getCurrentPlayer().getCivilization();
        index = 0;
        initialize();
    }

    private void initialize() {
        root = new Pane();
        root.setPrefHeight(231);
        root.setPrefWidth(484);
        root.setStyle("-fx-background-color: transparent");
        halfInitializer();
        openPanel();
    }

    public Pane getRoot(){
        return root;
    }

    private void halfInitializer() {
        demographicPanelImage = new ImageView(ImagesAddress.CITY_PANEL.getImage());
        demographicPanelImage.setFitHeight(231);
        demographicPanelImage.setFitWidth(484);
        demographicPanelImage.setLayoutX(-1);
        demographicPanelImage.setLayoutY(4);
        demographicPanelImage.setPickOnBounds(true);
        demographicPanelImage.setPreserveRatio(true);
        root.getChildren().add(demographicPanelImage);
        onMouseClick();
        addImages();
        addLabels();
        setLabels();
        closeButton();
    }

   private void addImages(){
        ImageView title = new ImageView(ImagesAddress.BLUE_BUTTON.getImage());
        title.setFitHeight(150);
        title.setFitWidth(200);
        title.setLayoutX(173);
        title.setLayoutY(14);
        title.setPickOnBounds(true);
        title.setPreserveRatio(true);
        root.getChildren().add(title);
        ImageView gray1 = new ImageView(ImagesAddress.GRAY_BUTTON.getImage());
        gray1.setFitHeight(71);
        gray1.setFitWidth(165);
        gray1.setLayoutX(72);
        gray1.setLayoutY(81);
        gray1.setPickOnBounds(true);
        gray1.setPreserveRatio(true);
        root.getChildren().add(gray1);
        ImageView gray2 = new ImageView(ImagesAddress.GRAY_BUTTON.getImage());
        gray2.setFitHeight(71);
        gray2.setFitWidth(165);
        gray2.setLayoutX(303);
        gray2.setLayoutY(81);
        gray2.setPickOnBounds(true);
        gray2.setPreserveRatio(true);
        root.getChildren().add(gray2);
        ImageView gray3 = new ImageView(ImagesAddress.GRAY_BUTTON.getImage());
        gray3.setFitHeight(71);
        gray3.setFitWidth(165);
        gray3.setLayoutX(72);
        gray3.setLayoutY(143);
        gray3.setPickOnBounds(true);
        gray3.setPreserveRatio(true);
        root.getChildren().add(gray3);
        ImageView gray4 = new ImageView(ImagesAddress.GRAY_BUTTON.getImage());
        gray4.setFitHeight(71);
        gray4.setFitWidth(165);
        gray4.setLayoutX(303);
        gray4.setLayoutY(143);
        gray4.setPickOnBounds(true);
        gray4.setPreserveRatio(true);
        root.getChildren().add(gray4);
        ImageView BlueHex = new ImageView(ImagesAddress.BLUE_HEX.getImage());
        BlueHex.setFitHeight(93);
        BlueHex.setFitWidth(84);
        BlueHex.setLayoutX(231);
        BlueHex.setLayoutY(105);
        BlueHex.setPickOnBounds(true);
        BlueHex.setPreserveRatio(true);
        root.getChildren().add(BlueHex);
   }

   private void addLabels(){
        title = new Label();
        title.setLayoutX(203);
        title.setLayoutY(36);
        title.prefHeight(26);
        title.prefWidth(140);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.rgb(199,214,255));
        title.setFont(Font.font("AppleGothic Regular",14));
        root.getChildren().add(title);
        best = new Label();
        best.setLayoutX(98);
        best.setLayoutY(103);
        best.prefHeight(17);
        best.prefWidth(97);
        best.setTextAlignment(TextAlignment.CENTER);
        best.setTextFill(Color.rgb(199,214,255));
        best.setFont(Font.font("AppleGothic Regular",14));
        root.getChildren().add(best);
        score = new Label();
        score.setLayoutX(258);
        score.setLayoutY(130);
        score.prefHeight(26);
        score.prefWidth(30);
        score.setTextAlignment(TextAlignment.CENTER);
        score.setTextFill(Color.rgb(0,0,0));
        score.setFont(Font.font("AppleGothic Regular",17));
        root.getChildren().add(score);
        worst = new Label();
        worst.setLayoutX(330);
        worst.setLayoutY(103);
        worst.prefHeight(17);
        worst.prefWidth(97);
        worst.setTextAlignment(TextAlignment.CENTER);
        worst.setTextFill(Color.rgb(199,214,255));
        worst.setFont(Font.font("AppleGothic Regular",14));
        root.getChildren().add(worst);
        average = new Label();
        average.setLayoutX(103);
        average.setLayoutY(165);
        average.prefHeight(17);
        average.prefWidth(97);
        average.setTextAlignment(TextAlignment.CENTER);
        average.setTextFill(Color.rgb(199,214,255));
        average.setFont(Font.font("AppleGothic Regular",14));
        root.getChildren().add(average);
        rank = new Label();
        rank.setLayoutX(330);
        rank.setLayoutY(165);
        rank.prefHeight(17);
        rank.prefWidth(97);
        rank.setTextAlignment(TextAlignment.CENTER);
        rank.setTextFill(Color.rgb(199,214,255));
        rank.setFont(Font.font("AppleGothic Regular",14));
        root.getChildren().add(rank);
   }

   private void citySizeLabels(){
       ArrayList<Civilization> civilizations = new ArrayList<>();
       double sum = 0;
       for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
           civilizations.add(player.getCivilization());
           sum += player.getCivilization().getCities().size();
       }
       civilizations.sort((o1, o2) -> {
           double difference = o1.getCities().size() - o2.getCities().size();
           if (difference > 0) return 1;
           if (difference < 0) return -1;
           return 0;
       });
       title.setText("City Size");
       best.setText("Best: " + civilizations.get(civilizations.size() - 1).getCities().size());
       worst.setText("Worst: " + civilizations.get(0).getCities().size());
       average.setText("Average: " + (sum / civilizations.size()));
       score.setText(String.valueOf(civilization.getCities().size()));
       rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));

   }

   private void tileSizeLabels(){
       ArrayList<Civilization> civilizations = new ArrayList<>();
       double sum = 0;
       for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
           civilizations.add(player.getCivilization());
           sum += getNumberOfCivilizationTiles(player.getCivilization());
       }
       civilizations.sort((o1, o2) -> {
           double difference = getNumberOfCivilizationTiles(o1) - getNumberOfCivilizationTiles(o2);
           if (difference > 0) return 1;
           return -1;
       }
       );
       title.setText("Tile Size");
       best.setText("Best: " + getNumberOfCivilizationTiles(civilizations.get(civilizations.size() - 1)));
       worst.setText("Worst: " + getNumberOfCivilizationTiles(civilizations.get(0)));
       average.setText("Average: " + (sum / civilizations.size()));
       score.setText(String.valueOf(getNumberOfCivilizationTiles(civilization)));
       rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
   }

    private int getNumberOfCivilizationTiles(Civilization civ) {
        int numberOfTiles = 0;
        for (City city : civ.getCities()) {
            numberOfTiles += city.getTiles().size();
        }
        return numberOfTiles;
    }

    private void unitLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getUnits().size();
        }
        civilizations.sort(Comparator.comparingInt(o -> o.getUnits().size()));
        title.setText("Unit Count");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getUnits().size());
        worst.setText("Worst: " + civilizations.get(0).getUnits().size());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getUnits().size()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void populationLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        int sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getPopulationSize();
        }
        civilizations.sort(Comparator.comparingInt(Civilization::getPopulationSize));
        title.setText("Population");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getPopulationSize());
        worst.setText("Worst: " + civilizations.get(0).getPopulationSize());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getPopulationSize()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void goldLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getCurrency().getGold();
        }
        civilizations.sort(new Comparator<>() {
            @Override
            public int compare(Civilization o1, Civilization o2) {
                double difference = o1.getCurrency().getGold() - o2.getCurrency().getGold();
                if (difference > 0) return 1;
                if (difference < 0) return -1;
                return 0;
            }
        });
        title.setText("Gold");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getCurrency().getGold());
        worst.setText("Worst: " + civilizations.get(0).getCurrency().getGold());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getCurrency().getGold()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void foodLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getCurrency().getFood();
        }
        civilizations.sort(new Comparator<>() {
            @Override
            public int compare(Civilization o1, Civilization o2) {
                double difference = o1.getCurrency().getFood() - o2.getCurrency().getFood();
                if (difference > 0) return 1;
                if (difference < 0) return -1;
                return 0;
            }
        });
        title.setText("Food");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getCurrency().getFood());
        worst.setText("Worst: " + civilizations.get(0).getCurrency().getFood());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getCurrency().getFood()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void productionLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getCurrency().getProduct();
        }
        civilizations.sort(new Comparator<>() {
            @Override
            public int compare(Civilization o1, Civilization o2) {
                double difference = o1.getCurrency().getProduct() - o2.getCurrency().getProduct();
                if (difference > 0) return 1;
                if (difference < 0) return -1;
                return 0;
            }
        });
        title.setText("Production");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getCurrency().getProduct());
        worst.setText("Worst: " + civilizations.get(0).getCurrency().getProduct());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getCurrency().getProduct()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void happinessLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += player.getCivilization().getHappiness();
        }
        civilizations.sort(new Comparator<>() {
            @Override
            public int compare(Civilization o1, Civilization o2) {
                double difference = o1.getHappiness() - o2.getHappiness();
                if (difference > 0) return 1;
                if (difference < 0) return -1;
                return 0;
            }
        });
        title.setText("Happiness");
        best.setText("Best: " + civilizations.get(civilizations.size() - 1).getHappiness());
        worst.setText("Worst: " + civilizations.get(0).getHappiness());
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(civilization.getHappiness()));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private void resourcesLabels(){
        ArrayList<Civilization> civilizations = new ArrayList<>();
        double sum = 0;
        for (Player player : gameMapController.getGameMenuController().getGame().getPlayers()) {
            civilizations.add(player.getCivilization());
            sum += getNumberOfResourceReposit(player.getCivilization());
        }
        civilizations.sort(new Comparator<>() {
            @Override
            public int compare(Civilization o1, Civilization o2) {
                double difference = getNumberOfResourceReposit(o1) - getNumberOfResourceReposit(o2);
                if (difference > 0) return 1;
                return -1;

            }
        });
        title.setText("Resources");
        best.setText("Best: " + getNumberOfResourceReposit(civilizations.get(civilizations.size() - 1)));
        worst.setText("Worst: " + getNumberOfResourceReposit(civilizations.get(0)));
        average.setText("Average: " + (sum / civilizations.size()));
        score.setText(String.valueOf(getNumberOfResourceReposit(civilization)));
        rank.setText("Rank: " + (civilizations.indexOf(civilization) + 1));
    }

    private int getNumberOfResourceReposit(Civilization civ) {
        int number = 0;
        for (Integer i : civ.getResourceRepository().values())
            number += i;
        return number;
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
        demographicPanelImage.setOnMouseClicked(e -> {
            goToNext();
        });
    }

    private void goToNext(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(400),root);
        transition.setToX(-500);
        transition.play();
        transition.setOnFinished(e -> {
            root.getChildren().clear();
            index += 1;
            if(index > 8) index = 0;
            halfInitializer();
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),root);
            translateTransition.setToX(0);
            translateTransition.play();
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

    private void setLabels(){
        switch (index){
            case 0:
                citySizeLabels();
                break;
            case 1:
                tileSizeLabels();
                break;
            case 2:
                foodLabels();
                break;
            case 3:
                productionLabels();
                break;
            case 4:
                goldLabels();
                break;
            case 5:
                happinessLabels();
                break;
            case 6:
                resourcesLabels();
                break;
            case 7:
                populationLabels();
                break;
            case 8:
                unitLabels();
                break;
        }
    }
}

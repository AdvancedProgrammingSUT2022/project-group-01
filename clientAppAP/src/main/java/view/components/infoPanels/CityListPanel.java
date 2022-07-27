package view.components.infoPanels;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.civilization.Civilization;
import model.civilization.city.City;
import view.Main;
import view.components.ImagesAddress;


public class CityListPanel {
    private Pane root = new Pane();
    private Civilization civilization;
    private VBox list;
    private Pane background;
    public CityListPanel(Civilization civilization, Pane background) {
        this.civilization = civilization;
        this.background = background;
        initDesign();
        background.getChildren().add(root);
    }

    private void initDesign(){
        root.setPrefHeight(552);
        root.setPrefWidth(368);
        ImageView panel = new ImageView(ImagesAddress.INFO_BACK.getImage());
        panel.setFitHeight(552);
        panel.setFitWidth(368);
        panel.setTranslateX(0);
        panel.setPickOnBounds(true);
        panel.setPreserveRatio(true);
        root.getChildren().add(panel);
        ImageView buttonImage = new ImageView(ImagesAddress.BLUE_BUTTON.getImage());
        buttonImage.setFitHeight(62);
        buttonImage.setFitWidth(208);
        buttonImage.setLayoutX(79);
        buttonImage.setLayoutY(415);
        buttonImage.setPickOnBounds(true);
        buttonImage.setPreserveRatio(true);
        root.getChildren().add(buttonImage);
        JFXButton economicOverviewButton = new JFXButton();
        economicOverviewButton.setButtonType(JFXButton.ButtonType.RAISED);
        economicOverviewButton.setLayoutX(96);
        economicOverviewButton.setLayoutY(430);
        economicOverviewButton.setTextAlignment(TextAlignment.CENTER);
        economicOverviewButton.setText("economic overview");
        economicOverviewButton.setTextFill(Color.rgb(214,214,227));
        economicOverviewButton.setFont(Font.font("AppleGothic Regular",15));
        economicOverviewButton.setOnMouseClicked(ev -> {
            closePanel();
            new CityPanel(background,civilization);
        });
        root.getChildren().add(economicOverviewButton);
        ScrollPane listPane = new ScrollPane();
        listPane.setLayoutX(27);
        listPane.setLayoutY(59);
        listPane.setPrefHeight(346);
        listPane.setPrefWidth(283);
        listPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        listPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.getChildren().add(listPane);
        list = new VBox();
        list.setPrefHeight(337);
        list.setPrefWidth(272);
        list.setStyle("-fx-background-color: transparent; -fx-background: transparent " );
        listPane.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        listPane.setContent(list);
        addCitiesToList();
        closeButton();
    }

    private void addCitiesToList(){
        for(City c : civilization.getCities()){
            list.getChildren().add(getCityRow(c));
        }
    }


    private Pane getCityRow(City c){
        Pane back = new Pane();
        back.setPrefHeight(63);
        back.setPrefWidth(272);
        back.setStyle("-fx-background-color: transparent;");
        ImageView cityDesign = new ImageView(ImagesAddress.CITY_IN_CITY_PANEL.getImage());
        cityDesign.setFitHeight(44);
        cityDesign.setFitWidth(288);
        cityDesign.setLayoutX(0);
        cityDesign.setLayoutY(11);
        cityDesign.setPickOnBounds(true);
        cityDesign.setPreserveRatio(true);
        back.getChildren().add(cityDesign);

        Label cityName = new Label();
        cityName.setLayoutX(47);
        cityName.setLayoutY(22);
        cityName.setText(c.getName());
        cityName.setFont(Font.font("AppleGothic Regular",16));
        cityName.setPrefHeight(17);
        cityName.setPrefWidth(89);
        cityName.setTextFill(Color.rgb(213,236,255));
        back.getChildren().add(cityName);

        Label cityPopulation = new Label();
        cityPopulation.setLayoutX(200);
        cityPopulation.setLayoutY(25);
        cityPopulation.setText(String.valueOf(c.getPopulation().size()));
        cityPopulation.setTextFill(Color.rgb(213,236,255));
        back.getChildren().add(cityPopulation);

        Label cityDefense = new Label();
        cityDefense.setLayoutX(242);
        cityDefense.setLayoutY(25);
        cityDefense.setText(String.valueOf(c.getDefencePower()));
        cityDefense.setTextFill(Color.rgb(213,236,255));
        back.getChildren().add(cityDefense);

        ImageView populationImage = new ImageView(ImagesAddress.CITIZEN.getImage());
        populationImage.setFitHeight(23);
        populationImage.setFitWidth(23);
        populationImage.setLayoutX(169);
        populationImage.setLayoutY(24);
        populationImage.setPickOnBounds(true);
        populationImage.setPreserveRatio(true);
        back.getChildren().add(populationImage);

        ImageView defenseImage = new ImageView(ImagesAddress.SHIELD_CITY_INFO.getImage());
        defenseImage.setFitHeight(23);
        defenseImage.setFitWidth(23);
        defenseImage.setLayoutX(216);
        defenseImage.setLayoutY(24);
        defenseImage.setPickOnBounds(true);
        defenseImage.setPreserveRatio(true);
        back.getChildren().add(defenseImage);

        Pane clickPane = new Pane();
        clickPane.setPrefHeight(31);
        clickPane.setPrefWidth(34);
        clickPane.setLayoutX(9);
        clickPane.setLayoutY(14);
        clickPane.setStyle("-fx-background-color: transparent;");
        clickPane.setOnMouseClicked(event -> {
            System.out.println("city clicked bitch");
        });
        back.getChildren().add(clickPane);
        return back;
    }

    public Pane getRoot(){
        return root;
    }

    private void closePanel(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),e->{
            root.setOpacity(0);
        }));
        timeline.setCycleCount(1);
        timeline.setOnFinished(e2 -> {
            background.getChildren().remove(root);
        });
        timeline.play();
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setLayoutX(20);
        close.setLayoutY(20);
        root.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            background.getChildren().remove(root);
        });
    }

}

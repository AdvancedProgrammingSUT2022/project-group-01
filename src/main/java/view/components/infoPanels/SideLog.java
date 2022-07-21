package view.components.infoPanels;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.components.ImagesAddress;
import view.components.mapComponents.GameMapController;

public class SideLog {
    private Pane root;
    private ImageView log;
    private ScrollPane scrollPane;
    private VBox list;
    private boolean isClosed;
    private Pane background;
    private GameMapController gameMapController;
    public SideLog(Pane background, GameMapController gameMapController) {
        this.gameMapController = gameMapController;
        this.background = background;
        initialize();
    }


    private void initialize(){
        root = new Pane();
        root.setPrefHeight(231);
        root.setPrefWidth(59);
        log = new ImageView(ImagesAddress.SIDE_LOG.getImage());
        log.setFitHeight(239);
        log.setFitWidth(80);
        log.setLayoutY(-4);
        log.setLayoutX(-4);
        log.setPickOnBounds(true);
        log.setPreserveRatio(true);
        root.getChildren().add(log);

        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(16);
        scrollPane.setPrefHeight(200);
        scrollPane.setPrefWidth(30);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.getChildren().add(scrollPane);

        list = new VBox();
        list.setPrefWidth(29);
        list.setPrefHeight(200);
        list.setSpacing(10);
        scrollPane.setContent(list);
        addListIcons();
        onClick();
        background.getChildren().add(root);
        isClosed = true;
        root.setTranslateX(-35);
        root.setTranslateY(40);
    }

    private void addListIcons(){
        ImageView cityPanelIcon = new ImageView(ImagesAddress.CITY_LIST_INFO_ICON.getImage());
        cityPanelIcon.setFitHeight(29);
        cityPanelIcon.setFitWidth(29);
        list.getChildren().add(cityPanelIcon);
        cityPanelIcon.setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.PRIMARY) {
                    close();
                    Pane p = new CityListPanel(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getCivilization(),background).getRoot();
                    p.setTranslateY(20);
                    TranslateTransition transition = new TranslateTransition(Duration.millis(400),p);
                    transition.setToX(-4);

                }
        });
        ImageView demographicPanelIcon = new ImageView(ImagesAddress.DEMOGRAPHIC_INFO_ICON.getImage());
        demographicPanelIcon.setFitHeight(29);
        demographicPanelIcon.setFitWidth(29);
        list.getChildren().add(demographicPanelIcon);
        demographicPanelIcon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                new DemographicPanel(background,gameMapController);
            }
        });
    }

    private void onClick(){
        log.setOnMouseClicked(event -> {
            if(isClosed){
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),root);
                translateTransition.setToX(0);
                translateTransition.play();
                isClosed = false;
            }else{
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),root);
                translateTransition.setToX(-35);
                translateTransition.play();
                isClosed = true;
            }
        });
    }

    private void close(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),root);
        translateTransition.setToX(-35);
        translateTransition.play();
        isClosed = true;
    }
}

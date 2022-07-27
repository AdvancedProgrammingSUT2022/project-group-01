package view.components.infoPanels;

import javafx.animation.TranslateTransition;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.components.ImagesAddress;
import view.components.gamePanelComponents.technologyPanel.TechnologyPopUp;
import view.components.infoPanels.democracy.DemocracyMainPanel;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.GameStopMenu;
import view.components.popup.NotificationHistory;
import view.components.unit.MilitaryOverview;
import view.components.unit.UnitPanel;

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
        // city panel
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
        // demographic
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
        // technology panel
        ImageView technologyPanelIcon = new ImageView(ImagesAddress.TECHNOLOGY_INFO_ICON.getImage());
        technologyPanelIcon.setFitHeight(29);
        technologyPanelIcon.setFitWidth(29);
        list.getChildren().add(technologyPanelIcon);
        technologyPanelIcon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                new TechnologyPopUp(background,gameMapController);
            }
        });
        // notification panel
        ImageView notificationPanelIcon = new ImageView(ImagesAddress.NOTIFICATION_INFO_ICON.getImage());
        notificationPanelIcon.setFitHeight(29);
        notificationPanelIcon.setFitWidth(29);
        list.getChildren().add(notificationPanelIcon);
        notificationPanelIcon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                NotificationHistory n = new NotificationHistory(background);
                background.getChildren().add(n);
                n.setTranslateY(20);
            }
        });
        // military overview
        ImageView militaryOverviewIcon = new ImageView(ImagesAddress.MILITARY_OVERVIEW_ICON.getImage());
        militaryOverviewIcon.setFitHeight(29);
        militaryOverviewIcon.setFitWidth(29);
        list.getChildren().add(militaryOverviewIcon);
        militaryOverviewIcon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                MilitaryOverview m = new MilitaryOverview(background,gameMapController.getGameMenuController().getGame().getCurrentPlayer().getCivilization());
                background.getChildren().add(m);
                m.setTranslateY(20);
            }
        });
        // unit panel
        ImageView unitPanelIcon = new ImageView(ImagesAddress.UNIT_PANEL_ICON.getImage());
        unitPanelIcon.setFitHeight(29);
        unitPanelIcon.setFitWidth(29);
        list.getChildren().add(unitPanelIcon);
        unitPanelIcon.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                UnitPanel up = new UnitPanel(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getCivilization(),gameMapController);
                background.getChildren().add(up);
                up.setTranslateY(20);
            }
        });
        // democracy panel
        ImageView democracyPanel = new ImageView(ImagesAddress.DEMOCRACY_PANEL_ICON.getImage());
        democracyPanel.setFitHeight(29);
        democracyPanel.setFitWidth(29);
        list.getChildren().add(democracyPanel);
        democracyPanel.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY){
                close();
                DemocracyMainPanel dmp = new DemocracyMainPanel(gameMapController);
                background.getChildren().add(dmp.getPane());
            }
        });
        // stop game (last panel)
        ImageView stopGame = new ImageView(ImagesAddress.STOP_GAME_ICON.getImage());
        stopGame.setFitHeight(29);
        stopGame.setFitWidth(29);
        list.getChildren().add(stopGame);
        stopGame.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                close();
                new GameStopMenu(gameMapController,background);
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

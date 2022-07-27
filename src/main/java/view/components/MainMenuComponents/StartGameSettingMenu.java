package view.components.MainMenuComponents;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import lombok.Getter;
import model.Player;
import model.User;
import view.Main;
import model.GameInstantiateData;
import view.components.ImagesAddress;
import view.components.mapComponents.UserMapBuilder.PreBuiltMap;

import java.util.Vector;


public class StartGameSettingMenu {
    @Getter
    private Pane root = new Pane();
    private Label playersLabel = new Label();
    private Label mapSizeLabel = new Label();
    private Label mapSizeRecommendation = new Label();
    private ScrollPane playersList = new ScrollPane();
    private GameInstantiateData gameInstantiateData;
    private Label mapSizeNumber = new Label();
    private ImageView minusImage;
    private ImageView plusImage;
    private JFXButton startGameButton = new JFXButton();
    private Timeline playersListener = new Timeline();
    private ScrollPane mapList = new ScrollPane();
    private TextField autoSaveText;
    private TextField epField;

    public StartGameSettingMenu(GameInstantiateData gameInstantiateData){
        this.gameInstantiateData = gameInstantiateData;
        root.setPrefHeight(450);
        root.setPrefWidth(830);
        root.setStyle("-fx-background-color: transparent");
        playersList.setStyle("-fx-opacity: 0");
        setPlayersLabel();
        setMapSizeLabel();
        setMapSizeRecommendation();
        setMapSizeNumber();
        setNumberChangeImages();
        setStartGameButton();
        gameInstantiateData.setMapSizeToPref();
        PreBuiltMap.fromJson();
        playersListSetup();
        initMapsList();
        buildMapIcon();
        autoSaveSetter();
        expectedPlayersSetter();
    }


    private void setPlayersLabel(){
        playersLabel.setLayoutX(41);
        playersLabel.setLayoutY(31);
        playersLabel.setText(gameInstantiateData.numberOfPlayersReady() + " Players");
        playersLabel.setFont(Font.font("Verdana",20));
        playersLabel.setTextFill(Color.WHITESMOKE);
        root.getChildren().add(playersLabel);
    }

    private void setMapSizeLabel(){
        mapSizeLabel.setLayoutX(46);
        mapSizeLabel.setLayoutY(168);
        mapSizeLabel.setText("Map Size");
        mapSizeLabel.setFont(Font.font("Verdana",20));
        mapSizeLabel.setTextFill(Color.WHITESMOKE);
    }

    private void setMapSizeRecommendation(){
        mapSizeRecommendation.setLayoutX(46);
        mapSizeRecommendation.setLayoutY(208);
        mapSizeRecommendation.setText("Preferred map diameter is more than " + (gameInstantiateData.numberOfPlayersReady() * 2 + 1));
        mapSizeRecommendation.setFont(Font.font("Verdana",15));
        mapSizeRecommendation.setTextFill(Color.WHITESMOKE);
        root.getChildren().add(mapSizeRecommendation);
    }

    private void setMapSizeNumber(){
        int preSize = (gameInstantiateData.numberOfPlayersReady() * 2) + 1;
        mapSizeNumber.setAlignment(Pos.CENTER);
        mapSizeNumber.setLayoutX(494);
        mapSizeNumber.setLayoutY(201);
        mapSizeNumber.setPrefHeight(29);
        mapSizeNumber.setPrefWidth(50);
        mapSizeNumber.setTextAlignment(TextAlignment.CENTER);
        mapSizeNumber.setText(String.valueOf(preSize));
        mapSizeNumber.setFont(Font.font("Verdana",24));
        root.getChildren().add(mapSizeNumber);
    }

    private void setNumberChangeImages() {
        minusImage = new ImageView(ImagesAddress.MAP_NUMBER_MINUS.getImage());
        minusImage.setFitHeight(31);
        minusImage.setFitWidth(31);
        minusImage.setLayoutX(459);
        minusImage.setLayoutY(200);
        minusImage.setPickOnBounds(true);
        minusImage.setPreserveRatio(true);
        minusImage.setOnMouseClicked(e -> {
            changeMapNumber(-2);
            imageClickAnimation(minusImage);
        });
        plusImage = new ImageView(ImagesAddress.MAP_NUMBER_PLUS.getImage());
        plusImage.setFitHeight(31);
        plusImage.setFitWidth(31);
        plusImage.setLayoutX(549);
        plusImage.setLayoutY(200);
        plusImage.setPickOnBounds(true);
        plusImage.setPreserveRatio(true);
        plusImage.setOnMouseClicked(e -> {
            changeMapNumber(+2);
            imageClickAnimation(plusImage);
        });
        root.getChildren().addAll(minusImage, plusImage);
    }

    private void changeMapNumber(int i){
        gameInstantiateData.changeMapSize(i);
        updateMapSize();
        updateMapList();
        if(gameInstantiateData.getMapSize() <= gameInstantiateData.getMapPrefSize()){
            minusImage.setDisable(true);
        }
        else{
            if(minusImage.isDisabled())
                minusImage.setDisable(false);
        }
    }

    private void updateMapList() {
        System.out.println("maps updated");
        mapList.setContent(showMapsList());
    }

    private void updateMapSize(){
        mapSizeNumber.setText(String.valueOf(gameInstantiateData.getMapSize()));
    }

    private void imageClickAnimation(ImageView image){
        Timeline fade = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            image.setOpacity(0);
        }));
        fade.setCycleCount(1);
        Timeline unfade = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            image.setOpacity(1);
        }));
        unfade.setCycleCount(1);
        fade.setOnFinished(e -> {
            unfade.play();
        });
        fade.play();
    }

    private void setStartGameButton(){
        startGameButton.setLayoutX(321);
        startGameButton.setLayoutY(315);
        startGameButton.setPrefHeight(60);
        startGameButton.setPrefWidth(188);
        startGameButton.setText("Start Game");
        startGameButton.setFont(Font.font("Verdana",20));
        startGameButton.setTextFill(Color.WHITESMOKE);
        startGameButton.setOnMouseClicked(e -> {
            startGame();
        });
        root.getChildren().add(startGameButton);
    }

    private void startGame(){
        gameInstantiateData.startGame();
    }

    private void playersListSetup(){
        playersList.setLayoutX(41);
        playersList.setLayoutY(80);
        HBox list = new HBox();
        list.setStyle("-fx-background-color: transparent");
        list.setStyle("-fx-background : transparent");
        list.setStyle("-fx-background-opacity: 0");
        list.setSpacing(10);
        for(User u : gameInstantiateData.getPlayers()){
            Circle avatarCircle = new Circle();
            avatarCircle.setRadius(40);
            ImagePattern pattern = new ImagePattern(new Image(u.getAvatarUrl()));
            avatarCircle.setFill(pattern);
            avatarCircle.setStyle("-fx-background-repeat: stretch; -fx-background-size: cover; -fx-background-color: transparent");
            list.getChildren().add(avatarCircle);
        }
        playersList.setContent(list);
        playersList.setStyle("-fx-background: transparent");
        playersList.setStyle("-fx-background-color: transparent");
        playersList.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        playersList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        playersList.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.getChildren().add(playersList);
    }

    private HBox showMapsList(){
        HBox list = new HBox();
        list.setStyle("-fx-background-color: transparent");
        list.setStyle("-fx-background : transparent");
        list.setStyle("-fx-background-opacity: 0");
        list.setSpacing(10);
        for(int i = 0; i < PreBuiltMap.getMaps().size(); i++){
            if(PreBuiltMap.getMaps().get(i).getMapSize() == gameInstantiateData.getMapSize()){
                JFXButton button = new JFXButton("Map " + i);
                button.setStyle("-fx-background-color: rgb(37,98,185)");
                button.setOnMouseClicked(e -> {
                    gameInstantiateData.loadBuiltMap(PreBuiltMap.getMaps().get(Integer.parseInt(button.getText().substring(4))));
                });
                list.getChildren().add(button);
            }
        }
        return list;
    }

    private void initMapsList(){
        mapList = new ScrollPane();
        mapList.setLayoutX(40);
        mapList.setLayoutY(250);
        mapList.setStyle("-fx-background: transparent");
        mapList.setStyle("-fx-background-color: transparent");
        mapList.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/InvitationMenu.css")));
        mapList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapList.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapList.setContent(showMapsList());
        root.getChildren().add(mapList);
    }

    private void buildMapIcon(){
        ImageView mapIcon = new ImageView(ImagesAddress.MAP_ICON.getImage());
        mapIcon.setTranslateX(500);
        mapIcon.setTranslateY(315);
        root.getChildren().add(mapIcon);
        mapIcon.setOnMouseClicked(e -> {
            setExtraDataGameInstantiateData();
            gameInstantiateData.startMapBuilder();
        });
    }

    private void setExtraDataGameInstantiateData() {
        int as;
        try{
            as = Integer.parseInt(autoSaveText.getText());
        }catch (NumberFormatException e){
            as = -1;
        }
        gameInstantiateData.setAutoSaveNumber(as);
        int playersExpect;
        try{
            playersExpect = Integer.parseInt(epField.getText());
            if(gameInstantiateData.getPlayers().size() > playersExpect){
                Vector<User> p = new Vector<>();
                for(int i = 0 ; i < playersExpect; i ++){
                    p.add(gameInstantiateData.getPlayers().get(i));
                }
                gameInstantiateData.setPlayers(p);
            }
        }catch (NumberFormatException e){
        }

    }

    private void autoSaveSetter(){
        Label autoSaveLabel = new Label("auto save");
        autoSaveLabel.setLayoutX(41);
        autoSaveLabel.setLayoutY(391);
        autoSaveLabel.setTextFill(Color.WHITESMOKE);
        root.getChildren().add(autoSaveLabel);
        autoSaveText = new TextField();
        autoSaveText.setLayoutX(70);
        autoSaveText.setLayoutY(391);
        autoSaveText.setPrefHeight(26);
        autoSaveText.setPrefWidth(40);
        autoSaveText.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/FirstPage.css")));
        autoSaveText.getStyleClass().add("tf_box");
        root.getChildren().add(autoSaveText);
    }

    private void expectedPlayersSetter(){
        Label eP = new Label("expected number of players");
        eP.setLayoutX(460);
        eP.setLayoutY(31);
        eP.setTextFill(Color.WHITESMOKE);
        root.getChildren().add(eP);
        epField = new TextField();
        epField.setLayoutX(635);
        epField.setLayoutY(27);
        epField.setPrefHeight(26);
        epField.setPrefWidth(40);
        epField.getStylesheets().add(String.valueOf(Main.class.getResource("/CSS/FirstPage.css")));
        epField.getStyleClass().add("tf_box");
        root.getChildren().add(epField);
    }

}

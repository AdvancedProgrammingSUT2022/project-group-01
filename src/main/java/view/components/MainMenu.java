package view.components;

import controller.GUIController;
import controller.ProgramController;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.Pair;
import view.components.MainMenuComponents.StartGameSettingMenu;
import view.components.mapComponents.MapTileComponent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    @FXML
    private Pane root;
    private Pane toolTipPane = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;
    private Pane inData = new Pane();
    private GameInstantiateData gameInstantiateData = new GameInstantiateData(ProgramController.getLoggedInUser());
    private List<Pair<String,Runnable>> items = Arrays.asList(
            new Pair<String,Runnable>("Start Game",()->{startGameMenu();}),
            new Pair<String,Runnable>("Invite Others",()->{openInviteMenu();})

    );

    public void initialize(){
        root.setPrefWidth(1280);
        root.setPrefHeight(720);
        ImageView background = new ImageView(ImagesAddress.GAME_BACKGROUND.getImage());
        background.setFitHeight(720);
        background.setFitWidth(1280);
        root.getChildren().add(background);

        double lineX = 40;
        double lineY = 290;
        line = new Line(lineX,lineY,lineX,lineY + 300);
        line.setStrokeWidth(3);
        line.setOpacity(0.5);
        line.setStroke(Color.color(1,1,1,0.75));
        line.setEffect(new DropShadow(5,Color.BLACK));
        line.setScaleY(0);
        root.getChildren().add(line);
        addItem(lineX + 5,lineY + 5);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000),line);
        scaleTransition.setToY(1);
        scaleTransition.setOnFinished(e ->{
            for(int i = 0; i < menuBox.getChildren().size(); i++){
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15),n);
                tt.setToX(0);
                tt.setOnFinished(event -> n.setClip(null));
                tt.play();
            }
        });
        scaleTransition.play();
        initInPane();
        setBackButton();

    }

    private void addItem(double x,double y){
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        items.forEach(data -> {
            MainMenuItem item = new MainMenuItem(data.getKey());
            item.setTranslateX(-300);
            item.setOnAction(data.getValue()
            );
            item.setOnMouseEntered(e -> {
                toolTipShower(data.getKey(),29,210);
            });
            item.setOnMouseExited(e -> {
                root.getChildren().remove(toolTipPane);
            });
            Rectangle clip = new Rectangle(300,30);
            clip.translateXProperty().bind(item.translateXProperty().negate());
            item.setClip(clip);
            menuBox.getChildren().add(item);
        });
        root.getChildren().add(menuBox);
    }


    private void initInPane(){
        inData.setLayoutX(390);
        inData.setLayoutY(230);
        inData.setPrefWidth(830);
        inData.setPrefHeight(450);
        inData.setStyle("-fx-background-color: rgba(0,0,0,0.66); -fx-background-radius: 10");
        root.getChildren().add(inData);
    }


    private void openInviteMenu(){
        inData.getChildren().clear();
        Pane p = new InvitationMenu(gameInstantiateData).getRoot();
        inData.getChildren().addAll(p.getChildren());
    }

    private void startGameMenu(){
        inData.getChildren().clear();
        Pane p = new StartGameSettingMenu(gameInstantiateData).getRoot();
        inData.getChildren().addAll(p.getChildren());
    }

    private void toolTipShower(String item, double x, double y){
        toolTipPane = new Pane();
        toolTipPane.setPrefWidth(250);
        toolTipPane.setPrefHeight(30);
        toolTipPane.setStyle("-fx-border-width: 10; -fx-border-color: black; -fx-border-radius: 10");
        toolTipPane.setStyle("-fx-background-color: rgba(38,35,37,0.8)");
        Label description = new Label();
        if(item.equals("Invite Others"))
            description.setText("Invite others to play with you");
        else if(item.equals("Start Game"))
            description.setText("Start a game with your friends");
        else
            description.setText("Fuck Off");
        description.setFont(Font.font("Verdana",10));
        description.setTextFill(Color.rgb(224,58,127));
        toolTipPane.getChildren().add(description);
        toolTipPane.setTranslateX(x + 100);
        toolTipPane.setTranslateY(y);
        root.getChildren().add(toolTipPane);
    }

    private void setBackButton(){
        ImageView backButton = new ImageView(ImagesAddress.BACK_BUTTON.getImage());
        backButton.setTranslateX(5);
        backButton.setTranslateY(5);
        backButton.setOnMouseClicked(e -> {
            GUIController.changeMenu("preMainMenu");
        });
        root.getChildren().add(backButton);
    }

}

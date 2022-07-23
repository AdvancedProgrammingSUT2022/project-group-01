package view.components.preMainMenu;

import controller.GUIController;
import controller.ProgramController;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;
import view.components.GameInstantiateData;
import view.components.ImagesAddress;
import view.components.MainMenuItem;
import view.components.preMainInMenus.FriendShipController;

import java.util.Arrays;
import java.util.List;

public class PreMainMenu {
    @FXML
    private Pane root;
    private VBox menuBox = new VBox(-5);
    private Line line;
    private List<Pair<String,Runnable>> items = Arrays.asList(
            new Pair<String,Runnable>("Game Menu",()->{runGameMenu();}),
            new Pair<String,Runnable>("Score Board",()->{runScoreBoard();}),
            new Pair<String,Runnable>("Profile Menu",()->{runProfileMenu();}),
            new Pair<String,Runnable>("Friends Menu",()->{runFriendsMenu();}),
            new Pair<String,Runnable>("Logout",()->{logout();})

    );

    private void runFriendsMenu() {
        GUIController.changeMenuManually(new FriendShipController(ProgramController.getLoggedInUser()).getRoot());
    }

    private void runGameMenu() {
        GUIController.changeMenu("MainMenu");
    }

    private void runScoreBoard(){
        GUIController.changeMenu("ScoreBoardMenu");
    }

    private void runProfileMenu(){
        GUIController.changeMenu("ProfileMenu");
    }

    private void logout(){
        ProgramController.setLoggedInUser(null);
        GUIController.changeMenu("FirstPage");
    }

    public void initialize(){
        GUIController.setCursor();
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
    }

    private void addItem(double x,double y){
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        items.forEach(data -> {
            MainMenuItem item = new MainMenuItem(data.getKey());
            item.setTranslateX(-300);
            item.setOnAction(data.getValue()
            );
            Rectangle clip = new Rectangle(300,30);
            clip.translateXProperty().bind(item.translateXProperty().negate());
            item.setClip(clip);
            menuBox.getChildren().add(item);
        });
        root.getChildren().add(menuBox);
    }



}

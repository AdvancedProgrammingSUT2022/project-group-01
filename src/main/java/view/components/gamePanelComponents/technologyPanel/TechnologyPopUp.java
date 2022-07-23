package view.components.gamePanelComponents.technologyPanel;

import controller.ProgramController;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import view.components.ImagesAddress;
import view.components.gamePanelComponents.TechnologyItem;
import view.components.mapComponents.GameMapController;

import java.util.Locale;


public class TechnologyPopUp {
    private Pane root = new Pane();
    private Rectangle innerRectangle = new Rectangle();
    private Circle techPicture = new Circle();
    private Label name = new Label();
    private Label remainingScienceLabel = new Label();
    private Pane backPane;
    private GameMapController gameMapController;
    public TechnologyPopUp(Pane backPane, GameMapController gameMapController){
        this.gameMapController = gameMapController;
        this.backPane = backPane;
        setRectangles();
        setTechPicture();
        setName();
        setRemainingScienceLabel();
        closeButton();
        backPane.getChildren().add(root);

    }

    public Pane getPane(){
        return root;
    }

    private void setRectangles(){
        root.setPrefHeight(128);
        root.setPrefWidth(330);
        root.setStyle("-fx-background-color: rgb(234,87,87,1); -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-style: solid;");
        innerRectangle.setArcHeight(5);
        innerRectangle.setArcWidth(5);
        innerRectangle.setFill(Color.rgb(55, 56, 57, 1));
        innerRectangle.setHeight(118);
        innerRectangle.setLayoutX(5);
        innerRectangle.setLayoutY(5);
        innerRectangle.setStroke(Color.BLACK);
        innerRectangle.setStrokeType(StrokeType.INSIDE);
        innerRectangle.setWidth(320);
        root.getChildren().addAll(innerRectangle);
    }

    private void setTechPicture(){
        techPicture.setRadius(48);
        techPicture.setLayoutX(62);
        techPicture.setLayoutY(60);
        techPicture.setStroke(Color.rgb(235,194,46,1));
        if(ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getCurrentResearch() != null)
            techPicture.setFill(new ImagePattern(ImagesAddress.getTechnologyImage(ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getCurrentResearch())));
        root.getChildren().add(techPicture);
        openTechPanel();
    }

    private void setName(){
        name.setAlignment(Pos.CENTER);
        name.setContentDisplay(ContentDisplay.CENTER);
        name.setLayoutX(150);
        name.setLayoutY(14);
        name.setPrefHeight(40);
        name.setPrefWidth(101);
        if(ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getCurrentResearch() != null)
            name.setText(ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getCurrentResearch().name().toLowerCase(Locale.ROOT));
        name.setTextFill(Color.WHITE);
        name.setFont(new javafx.scene.text.Font(18));
        root.getChildren().add(name);
    }

    private void setRemainingScienceLabel(){
        remainingScienceLabel.setLayoutX(131);
        remainingScienceLabel.setLayoutY(73);
        if(ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getCurrentResearch() != null)
            remainingScienceLabel.setText("science left : " + ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().getRemainingScience());
        remainingScienceLabel.setTextFill(Color.WHITE);
        root.getChildren().add(remainingScienceLabel);
    }

    private void openTechPanel(){
        techPicture.setOnMouseClicked(event -> {
            TechnologyPanel panel = new TechnologyPanel(gameMapController);
            backPane.getChildren().add(panel.getRoot());
        });
    }

    private void closeButton(){
        ImageView close = new ImageView(ImagesAddress.INFO_CLOSE.getImage());
        close.setLayoutX(10);
        close.setLayoutY(10);
        close.setFitHeight(34);
        close.setFitWidth(34);
        close.setPickOnBounds(true);
        close.setPreserveRatio(true);
        root.getChildren().add(close);
        close.setOnMouseClicked(e -> {
            backPane.getChildren().remove(root);
        });
    }
}

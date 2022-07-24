package view.components.gamePanelComponents;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import model.Player;
import model.technology.TechnologyList;
import model.technology.TechnologyType;
import view.Main;
import view.components.ImagesAddress;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

public class TechnologyItem {
    private Pane root = new Pane();
    private Label nameLabel = new Label();
    private HBox avatars = new HBox();
    private Rectangle box = new Rectangle();
    private Circle avatarCircle = new Circle();
    private boolean panelVersion = false;
    private TechnologyType type;
    public TechnologyItem(TechnologyType type, Player player, boolean panelVersion){
        this.type = type;
        this.panelVersion = panelVersion;
        root.setPrefHeight(76);
        root.setPrefWidth(327);
        root.setStyle("-fx-background-color: transparent");
        initRectangle(type, player);
        initLabel(type, player);
        initAvatarCircle(type);
        kidsRectangle();
        initKidsBox(type);
    }

    public Pane getPane(){
        return root;
    }

    private void initRectangle(TechnologyType type, Player player){
        box.setArcWidth(30);
        box.setArcHeight(20);
        if(panelVersion){
            box.setFill(Color.rgb(0, 0, 0, 0.5));
            box.setStyle("-fx-background-color: #262424");
        }
        else if(player.getCivilization().getResearchTree().isResearched(type)){
            box.setFill(Color.GREEN);
            box.setStyle("-fx-background-color: GREEN");
        }else {
            box.setFill(Color.rgb(229,86,86,1));
            box.setStyle("-fx-background-color: #ea5757");
        }
        box.setHeight(70);
        box.setLayoutX(3);
        box.setLayoutY(2);
        box.setStroke(Color.BLACK);
        box.setStrokeType(StrokeType.INSIDE);
        box.setWidth(321);
        root.getChildren().add(box);
    }

    private void initAvatarCircle(TechnologyType type){
        avatarCircle.setFill(new ImagePattern(Objects.requireNonNull(getTechnologyImage(type))));
        avatarCircle.setLayoutX(41);
        avatarCircle.setLayoutY(37);
        avatarCircle.setRadius(27);
        avatarCircle.setStroke(Color.BLACK);
        avatarCircle.setStrokeType(StrokeType.INSIDE);
        root.getChildren().add(avatarCircle);
    }

    private void initLabel(TechnologyType type,Player player){
        nameLabel.setLayoutX(80);
        nameLabel.setLayoutY(3);
        nameLabel.setText(type.name().toLowerCase(Locale.ROOT));
        if(panelVersion)
            nameLabel.setTextFill(Color.WHITESMOKE);
        else if(player.getCivilization().getResearchTree().isResearched(type))
            nameLabel.setTextFill(Color.BLACK);
        else
            nameLabel.setTextFill(Color.WHITESMOKE);
        nameLabel.setFont(Font.font("Chalkboard SE Light",14));
        root.getChildren().add(nameLabel);
    }

    private void kidsRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setFill(Color.BLACK);
        rectangle.setHeight(38);
        rectangle.setLayoutX(80);
        rectangle.setLayoutY(28);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setWidth(212);
        root.getChildren().add(rectangle);
    }

    private void initKidsBox(TechnologyType type){
        avatars.setLayoutX(80);
        avatars.setLayoutY(28);
        Vector<TechnologyType> technologyTypes;
        if(panelVersion){
            technologyTypes= new Vector<>();
            for(TechnologyType t : TechnologyType.values()){
                if(t.getPrerequisiteTechs().getTechs().contains(type))
                    technologyTypes.add(t);
            }
        }
        else
            technologyTypes = type.getPrerequisiteTechs().getTechs();
        for(TechnologyType tech : technologyTypes){
            Circle circle = new Circle();
            circle.setFill(new ImagePattern(getTechnologyImage(tech)));
            circle.setRadius(17);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(StrokeType.INSIDE);
            avatars.getChildren().add(circle);
        }
        root.getChildren().add(avatars);
    }


    private Image getTechnologyImage(TechnologyType type) {
        return ImagesAddress.getTechnologyImage(type);
    }

    public TechnologyType getTechnologyType() {
        return type;
    }
}

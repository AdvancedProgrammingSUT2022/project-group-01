package view.components.gamePanelComponents;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import controller.GameMenuController;
import controller.ProgramController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import model.technology.TechnologyType;
import view.Main;
import view.components.ImagesAddress;
import view.components.mapComponents.GameMapController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class TechnologyTreeController {
    public Pane backPane = new Pane();
    public Pane wholePane = new Pane();
    public ScrollPane scrollPane = new ScrollPane();
    public TextField techFinderText = new TextField();
    private JFXButton searchButton = new JFXButton();
    private HashMap<TechnologyType,TechnologyItem> items = new HashMap<>();
    private HashMap<TechnologyType,Double> xCoordinates = new HashMap<>();
    private HashMap<TechnologyType,Double> yCoordinates = new HashMap<>();
    private GameMapController gameMapController;
    public TechnologyTreeController(GameMapController gameMapController) {
        this.gameMapController = gameMapController;
        initialize();
    }

    public Pane getRoot(){
        return wholePane;
    }
    public void initialize(){
        GUIController.setCursor();
        initializeBasedOnFXML();
        initPaneFeatures();
        loadLines();
        positionMap();
        addAllTechnologies();
        backButton();
    }


    public void initializeBasedOnFXML(){
        wholePane.setPrefHeight(720);
        wholePane.setPrefWidth(1280);
        wholePane.setStyle("-fx-background-image: url(asset/technology/background.JPG)");
        scrollPane.setLayoutY(-1);
        scrollPane.setPrefHeight(670);
        scrollPane.setPrefWidth(1280);
        backPane.setPrefHeight(663);
        backPane.setPrefWidth(10280);
        scrollPane.setContent(backPane);
        wholePane.getChildren().add(scrollPane);
        techFinderText.setLayoutX(640);
        techFinderText.setLayoutY(674);
        techFinderText.setPrefHeight(32);
        techFinderText.setPrefWidth(522);
        techFinderText.getStylesheets().add(Main.class.getResource("/css/FirstPage.css").toExternalForm());
        techFinderText.getStyleClass().add("tf_box");
        wholePane.getChildren().add(techFinderText);
        searchButton = new JFXButton("Search");
        searchButton.setButtonType(JFXButton.ButtonType.RAISED);
        searchButton.setLayoutX(1180);
        searchButton.setLayoutY(677);
        searchButton.setOnMouseClicked(this::findTech);
        searchButton.setFont(Font.font("Apple SD Gothic Neo Regular",13));
        wholePane.getChildren().add(searchButton);
        Rectangle rect1 = new Rectangle();
        rect1.setArcHeight(5);
        rect1.setArcWidth(5);
        rect1.setFill(Color.rgb(25,218,80));
        rect1.setHeight(20);
        rect1.setLayoutX(14);
        rect1.setLayoutY(680);
        rect1.setStroke(Color.BLACK);
        rect1.setStrokeType(StrokeType.INSIDE);
        rect1.setWidth(19);
        wholePane.getChildren().add(rect1);
        Rectangle rect2 = new Rectangle();
        rect2.setArcHeight(5);
        rect2.setArcWidth(5);
        rect2.setFill(Color.rgb(128,128,128,0.8));
        rect2.setHeight(20);
        rect2.setLayoutX(14);
        rect2.setLayoutY(680);
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeType(StrokeType.INSIDE);
        rect2.setWidth(19);
        wholePane.getChildren().add(rect2);
        Rectangle rect3 = new Rectangle();
        rect3.setArcHeight(5);
        rect3.setArcWidth(5);
        rect3.setFill(Color.rgb(231,99,99));
        rect3.setHeight(20);
        rect3.setLayoutX(219);
        rect3.setLayoutY(680);
        rect3.setStroke(Color.BLACK);
        rect3.setStrokeType(StrokeType.INSIDE);
        rect3.setWidth(19);
        wholePane.getChildren().add(rect3);
        Label searched = new Label();
        searched.setLayoutX(40);
        searched.setLayoutY(682);
        searched.setText("Searched");
        searched.setFont(Font.font("AppleGothic Regular",13));
        wholePane.getChildren().add(searched);
        Label elseLabel = new Label();
        elseLabel.setLayoutX(249);
        elseLabel.setLayoutY(682);
        elseLabel.setText("else");
        elseLabel.setFont(Font.font("AppleGothic Regular",13));
        wholePane.getChildren().add(elseLabel);
        Label searchable = new Label();
        searchable.setLayoutX(40);
        searchable.setLayoutY(682);
        searchable.setText("Searchable");
        searchable.setFont(Font.font("AppleGothic Regular",13));
        wholePane.getChildren().add(searchable);

    }

    private void addAllTechnologies(){
        for(TechnologyType type : TechnologyType.values()){
            addTechnology(type);
        }
    }

    private void initPaneFeatures(){
        backPane.setStyle("-fx-background-color: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.setStyle("-fx-background : transparent");
        backPane.setStyle("-fx-backgorund : transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/css/InvitationMenu.css")));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }


    private void addTechnology(TechnologyType type){
        TechnologyItem technologyItem = new TechnologyItem(type, ProgramController.getGame().getCurrentPlayer(),false);
        items.put(type,technologyItem);
        backPane.getChildren().add(technologyItem.getPane());
        technologyItem.getPane().setLayoutX(xCoordinates.get(type));
        technologyItem.getPane().setLayoutY(yCoordinates.get(type));
    }

    private void loadLines(){
        try {
            URL address = new URL(Objects.requireNonNull(
                    Main.class.getResource("/FXML/TechTreeLines.fxml")).toString());
            Pane linesPane = FXMLLoader.load(address);
            for(Node l : linesPane.getChildren()){
                if(l instanceof Line) {
                    l.setStyle("-fx-stroke: #e5c319; -fx-stroke-width: 2");
                    ((Line)l).setStrokeWidth(3);
                    ((Line) l).setStroke(Color.color(1,1,1,0.75));
                    l.setEffect(new DropShadow(5,Color.BLACK));
                }
            }
            backPane.getChildren().addAll(linesPane.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void positionMap(){
        xCoordinates.put(TechnologyType.AGRICULTURE,30.0);
        yCoordinates.put(TechnologyType.AGRICULTURE,261.0);
        xCoordinates.put(TechnologyType.ANIMAL_HUSBANDRY,453.0);
        yCoordinates.put(TechnologyType.ANIMAL_HUSBANDRY,238.0);
        xCoordinates.put(TechnologyType.ARCHERY,453.0);
        yCoordinates.put(TechnologyType.ARCHERY,352.0);
        xCoordinates.put(TechnologyType.MINING,460.0);
        yCoordinates.put(TechnologyType.MINING,459.0);
        xCoordinates.put(TechnologyType.POTTERY,453.0);
        yCoordinates.put(TechnologyType.POTTERY,112.0);
        xCoordinates.put(TechnologyType.MASONRY,921.0);
        yCoordinates.put(TechnologyType.MASONRY,459.0);
        xCoordinates.put(TechnologyType.CALENDAR,909.0);
        yCoordinates.put(TechnologyType.CALENDAR,162.0);
        xCoordinates.put(TechnologyType.WRITING,909.0);
        yCoordinates.put(TechnologyType.WRITING,162.0);
        xCoordinates.put(TechnologyType.TRAPPING,915.0);
        yCoordinates.put(TechnologyType.TRAPPING,255.0);
        xCoordinates.put(TechnologyType.THE_WHEEL,921.0);
        yCoordinates.put(TechnologyType.THE_WHEEL,358.0);
        xCoordinates.put(TechnologyType.BRONZE_WORKING,921.0);
        yCoordinates.put(TechnologyType.BRONZE_WORKING,565.0);
        xCoordinates.put(TechnologyType.HORSEBACK_RIDING,1344.0);
        yCoordinates.put(TechnologyType.HORSEBACK_RIDING,259.0);
        xCoordinates.put(TechnologyType.MATHEMATICS,1344.0);
        yCoordinates.put(TechnologyType.MATHEMATICS,375.0);
        xCoordinates.put(TechnologyType.CONSTRUCTION,1344.0);
        yCoordinates.put(TechnologyType.CONSTRUCTION,497.0);
        xCoordinates.put(TechnologyType.PHILOSOPHY,1726.0);
        yCoordinates.put(TechnologyType.PHILOSOPHY,112.0);
        xCoordinates.put(TechnologyType.CURRENCY,1718.0);
        yCoordinates.put(TechnologyType.CURRENCY,320.0);
        xCoordinates.put(TechnologyType.ENGINEERING,1718.0);
        yCoordinates.put(TechnologyType.ENGINEERING,421.0);
        xCoordinates.put(TechnologyType.IRON_WORKING,1718.0);
        yCoordinates.put(TechnologyType.IRON_WORKING,573.0);
        xCoordinates.put(TechnologyType.THEOLOGY,2197.0);
        yCoordinates.put(TechnologyType.THEOLOGY,112.0);
        xCoordinates.put(TechnologyType.CIVIL_SERVICE,2197.0);
        yCoordinates.put(TechnologyType.CIVIL_SERVICE,253.0);
        xCoordinates.put(TechnologyType.METAL_CASTING,2187.0);
        yCoordinates.put(TechnologyType.METAL_CASTING,478.0);
        xCoordinates.put(TechnologyType.EDUCATION,2784.0);
        yCoordinates.put(TechnologyType.EDUCATION,35.0);
        xCoordinates.put(TechnologyType.CHIVALRY,2784.0);
        yCoordinates.put(TechnologyType.CHIVALRY,157.0);
        xCoordinates.put(TechnologyType.MACHINERY,2772.0);
        yCoordinates.put(TechnologyType.MACHINERY,266.0);
        xCoordinates.put(TechnologyType.PHYSICS,2784.0);
        yCoordinates.put(TechnologyType.PHYSICS,409.0);
        xCoordinates.put(TechnologyType.STEEL,2799.0);
        yCoordinates.put(TechnologyType.STEEL,540.0);
        xCoordinates.put(TechnologyType.ACOUSTICS,3198.0);
        yCoordinates.put(TechnologyType.ACOUSTICS,90.0);
        xCoordinates.put(TechnologyType.BANKING,3198.0);
        yCoordinates.put(TechnologyType.BANKING,183.0);
        xCoordinates.put(TechnologyType.PRINTING_PRESS,3198.0);
        yCoordinates.put(TechnologyType.PRINTING_PRESS,353.0);
        xCoordinates.put(TechnologyType.GUNPOWDER,3198.0);
        yCoordinates.put(TechnologyType.GUNPOWDER,540.0);
        xCoordinates.put(TechnologyType.ECONOMICS,3584.0);
        yCoordinates.put(TechnologyType.ECONOMICS,266.0);
        xCoordinates.put(TechnologyType.CHEMISTRY,3584.0);
        yCoordinates.put(TechnologyType.CHEMISTRY,420.0);
        xCoordinates.put(TechnologyType.METALLURGY,3584.0);
        yCoordinates.put(TechnologyType.METALLURGY,554.0);
        xCoordinates.put(TechnologyType.ARCHAEOLOGY,3963.0);
        yCoordinates.put(TechnologyType.ARCHAEOLOGY,29.0);
        xCoordinates.put(TechnologyType.SCIENTIFIC_THEORY,3963.0);
        yCoordinates.put(TechnologyType.SCIENTIFIC_THEORY,183.0);
        xCoordinates.put(TechnologyType.MILITARY_SCIENCE,3963.0);
        yCoordinates.put(TechnologyType.MILITARY_SCIENCE,325.0);
        xCoordinates.put(TechnologyType.RIFLING,3963.0);
        yCoordinates.put(TechnologyType.RIFLING,454.0);
        xCoordinates.put(TechnologyType.FERTILIZER,3963.0);
        yCoordinates.put(TechnologyType.FERTILIZER,564.0);
        xCoordinates.put(TechnologyType.BIOLOGY,4380.0);
        yCoordinates.put(TechnologyType.BIOLOGY,74.0);
        xCoordinates.put(TechnologyType.STEAM_POWER,4380.0);
        yCoordinates.put(TechnologyType.STEAM_POWER,350.0);
        xCoordinates.put(TechnologyType.ELECTRICITY,4788.0);
        yCoordinates.put(TechnologyType.ELECTRICITY,179.0);
        xCoordinates.put(TechnologyType.REPLACEABLE_PARTS,4782.0);
        yCoordinates.put(TechnologyType.REPLACEABLE_PARTS,320.0);
        xCoordinates.put(TechnologyType.RAILROAD,4782.0);
        yCoordinates.put(TechnologyType.RAILROAD,440.0);
        xCoordinates.put(TechnologyType.DYNAMITE,4788.0);
        yCoordinates.put(TechnologyType.DYNAMITE,564.0);
        xCoordinates.put(TechnologyType.TELEGRAPH,5185.0);
        yCoordinates.put(TechnologyType.TELEGRAPH,73.0);
        xCoordinates.put(TechnologyType.RADIO,5185.0);
        yCoordinates.put(TechnologyType.RADIO,237.0);
        xCoordinates.put(TechnologyType.COMBUSTION,5185.0);
        yCoordinates.put(TechnologyType.COMBUSTION,408.0);

    }

    public void findTech(MouseEvent event) {
        String techName = techFinderText.getText();
        for(TechnologyType tech : xCoordinates.keySet()){
            if(tech.name().toLowerCase(Locale.ROOT).equals(techName)){
                double ratio = xCoordinates.get(tech) / backPane.getPrefWidth();
                scrollPane.setHvalue(ratio);
            }
        }
    }

    private void backButton(){
        ImageView backButton = new ImageView(ImagesAddress.BACK_BUTTON.getImage());
        backButton.setTranslateX(5);
        backButton.setTranslateY(5);
        backButton.setOnMouseClicked(e -> {
            GUIController.changeMenuManually(gameMapController.getBackPane());
        });
        backPane.getChildren().add(backButton);
    }
}

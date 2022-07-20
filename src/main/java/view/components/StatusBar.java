package view.components;

import controller.ProgramController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import model.civilization.Civilization;
import utils.GraphicUtils;
import utils.StringUtils;

public class StatusBar extends BorderPane {

    private final Civilization civilization;
    private Label beakerLabel;
    private Label goldLabel;
    private Label happinessLabel;
    private Label turnLabel;
    private final HBox left = new HBox();
    private final HBox right = new HBox();

    public StatusBar(Civilization civilization){
        this.civilization = civilization;
        init();
    }

    private void init(){
        initBorderPane();
        initLeft();
        initRight();

    }

    private void initLeft(){
        initBeaker();
        initGold();
        initHappiness();
        left.setAlignment(Pos.CENTER);
        left.setSpacing(7);
    }

    private void initRight(){
        initTurn();
        right.setAlignment(Pos.CENTER);
        right.setSpacing(3);
    }

    private void initBorderPane(){
        this.setMinWidth(1280);
        this.setMaxWidth(1280);
        this.setMinHeight(20);
        this.setStyle("-fx-background-color: black");
        this.setLeft(left);
        this.setRight(right);
        this.setPadding(new Insets(5,5,5,5));
    }

    private void initBeaker(){
        Rectangle rectangle = new Rectangle(10,10);
        //rectangle.setFill(GraphicUtils.getImage("/city/currency/science.png"));
        Tooltip tooltip = new Tooltip("Beaker (Science)");
        Tooltip.install(rectangle, tooltip);
        beakerLabel = new Label(StringUtils.makeNumberSigned(civilization.getBeaker()));
        beakerLabel.setStyle("-fx-text-fill: #1843ff");
        left.getChildren().addAll(rectangle, beakerLabel);
    }

    private void initGold(){
        Rectangle rectangle = new Rectangle(10,10);
        //rectangle.setFill(GraphicUtils.getImage("/city/currency/gold.png"));
        Tooltip tooltip = new Tooltip("Gold");
        Tooltip.install(rectangle, tooltip);
        goldLabel = new Label((civilization.getCurrency().getGold())+" ("+StringUtils.makeNumberSigned(civilization.getCurrency().getGold())+")");
        goldLabel.setStyle("-fx-text-fill: #ddb800");
        left.getChildren().add(rectangle);
        left.getChildren().add(goldLabel);
    }

    private void initHappiness(){
        Rectangle rectangle = new Rectangle(10,10);
        //rectangle.setFill(GraphicUtils.getImage("/civilization/happiness.png"));
        Tooltip tooltip = new Tooltip("Happiness");
        Tooltip.install(rectangle, tooltip);
        happinessLabel = new Label(String.valueOf(civilization.getHappiness()));
        happinessLabel.setStyle("-fx-text-fill: #e0c300");
        left.getChildren().add(rectangle);
        left.getChildren().add(happinessLabel);
    }

    private void initTurn(){
        turnLabel = new Label("Turn: "+ProgramController.getGame().getTurn());
        turnLabel.setStyle("-fx-text-fill: #ffeea0");
        right.getChildren().add(turnLabel);
    }

    public void update(){
        beakerLabel.setText(StringUtils.makeNumberSigned(civilization.getBeaker()));
        goldLabel.setText(civilization.getCurrency().getGold()+" ("+StringUtils.makeNumberSigned(civilization.getCurrency().getGold())+")");
        happinessLabel.setText(civilization.getBeaker()+"");
        turnLabel.setText("Turn: "+ProgramController.getGame().getTurn());
    }


}

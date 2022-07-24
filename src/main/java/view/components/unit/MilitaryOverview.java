package view.components.unit;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.civilization.Civilization;
import model.civilization.city.City;
import model.unit.Unit;
import org.w3c.dom.css.Rect;
import utils.GraphicUtils;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;

import java.util.Locale;

public class MilitaryOverview extends Pane {
    private Civilization civilization;

    public MilitaryOverview(Civilization civilization){
        this.civilization = civilization;
        init();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/MilitaryOverview.css").toExternalForm());
        this.getStyleClass().add("general");
        Label label = newLabel("Units", 20);
        label.setTranslateX(455);
        label.setTranslateY(2);
        VBox vbox = initLeft();
        vbox.setTranslateX(10);
        vbox.setTranslateY(10);
        ScrollPane scrollPane = initRight();
        scrollPane.setTranslateX(245);
        scrollPane.setTranslateY(40);
        this.getChildren().addAll(vbox, scrollPane, label);
    }

    private VBox initLeft(){
        VBox vbox = new VBox();
        vbox.getStyleClass().add("left-box");
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(newLabel("Unit Supply",18));
        vbox.getChildren().add(newSplitter(150));
        vbox.getChildren().add(staticRow("Base Supply", String.valueOf(civilization.getUnits().size())));
        vbox.getChildren().add(staticRow("Cities",String.valueOf(civilization.getCities().size())));
        int count = 0;
        for(City city : civilization.getCities())
            count+=city.getPopulation().size();
        vbox.getChildren().add(staticRow("Population",String.valueOf(count)));
        return vbox;
    }

    private ScrollPane initRight(){
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox();
        scrollPane.setContent(vbox);
        scrollPane.getStyleClass().add("right-box");
        for(Unit unit : civilization.getUnits()){
            vbox.getChildren().add(row(unit));
        }
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
        scrollPane.setStyle("-fx-focus-color: transparent");
        return scrollPane;
    }

    private Label newLabel(String text, int size){
        Label label = new Label(text);
        Font font = new Font("Times New Roman", size);
        label.setFont(font);
        label.setStyle("-fx-text-fill: #FFEEA0");
        return label;
    }

    private BorderPane staticRow(String left, String right){
        BorderPane out = new BorderPane();
        out.setMinHeight(20);
        out.setMaxHeight(20);
        out.setMinWidth(120);
        out.setMaxWidth(120);
        out.setLeft(newLabel(left,14));
        out.setRight(newLabel(right, 14));
        return out;
    }

    private Rectangle newSplitter(int width){
        Rectangle out = new Rectangle(width,1);
        out.setFill(Color.color(1,0.93,0.62));
        return out;
    }

    private BorderPane row(Unit unit){
        BorderPane out = new BorderPane();
        out.getStyleClass().add("row");
        out.setMaxWidth(450);
        out.setMinWidth(450);
        HBox left  = new HBox();
        HBox right = new HBox();

        left.setMinWidth(300);
        left.setMaxWidth(300);
        right.setMinWidth(150);
        right.setMaxWidth(150);
        out.setLeft(left);
        out.setRight(right);
        left.setPadding(new Insets(5,5,5,5));
        left.setSpacing(5);
        left.setAlignment(Pos.CENTER_LEFT);
        right.setPadding(new Insets(10,10,10,10));
        right.setSpacing(20);
        right.setAlignment(Pos.CENTER_RIGHT);
        Rectangle rectangle = new Rectangle(20,20);
        String path = unit.getType().getCombatType().name()+"/"+unit.getType().name()+".png";
        rectangle.setFill(GraphicUtils.getImage("/units/"+path));
        left.getChildren().add(rectangle);

        left.getChildren().add(newLabel(makeJustify(unit.getType().name()),13));

        Rectangle button = new Rectangle(15,15);
        button.setFill(GraphicUtils.getImage("/units/activate.png"));
        button.setOnMouseClicked(mouseEvent -> {
            unit.alert();
            new PopUp().run(PopUpStates.WARNING, "Unit alerted!");
        });
        right.getChildren().add(button);

        right.getChildren().add(newLabel(String.valueOf((int)unit.getMovementPoint()),13));
        right.getChildren().add(newLabel(String.valueOf((int)unit.getHealth()),13));
        right.getChildren().add(newLabel(String.valueOf(unit.getType().getRange()),13));
        return out;
    }

    public String makeJustify(String name){
        StringBuilder out = new StringBuilder(name.toLowerCase());
        out = new StringBuilder(out.toString().replaceAll("_", " "));
        out = new StringBuilder(out.substring(0, 1).toUpperCase() + out.substring(1));
        int count = 14 - out.length();
        out.append(" ".repeat(Math.max(0, count)));
        return out.toString();
    }



}

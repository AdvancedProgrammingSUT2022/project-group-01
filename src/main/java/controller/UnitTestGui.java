package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Getter;
import model.Game;
import model.civilization.city.City;
import model.unit.Unit;
import view.components.city.CityOverview;
import view.components.city.productionpanel.ProductionPanel;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;
import view.components.unit.UnitActionsPanel;
import view.components.unit.UnitView;


@Getter
public class UnitTestGui extends Application {

    private Pane pane = new Pane();
    Stage stage;
    Unit unit;
    public static UnitTestGui instance;
    public static double mouseX, mouseY;
    private static Game game;

    public UnitTestGui() {
        super();
        instance = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        initialize(stage);
        stage.show();
    }

    public void initialize(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        stage.setWidth(700);
        stage.setHeight(500);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        initBtn();
    }

    public void initBtn() {
        Button btn = new Button("select unit");
        pane.getChildren().add(btn);
        btn.setOnAction(actionEvent -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new PopUp().run(PopUpStates.WARNING, "hame chi khoobe inam jahate matne kheiiliii toollani base dige khodayi");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        });
    }


    public void run(Game game) {
        UnitTestGui.game = game;
        launch();
    }

    public void select() {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UnitTestGui.this.unit = (Unit) game.getSelectedObject();
//                    UnitView unitView = new UnitView(unit,pane);
//                    UnitActionsPanel unitActionsPanel = new UnitActionsPanel(unitView);
//                    unitActionsPanel.setLayoutX(300);
//                    unitActionsPanel.setLayoutY(200);
//                    pane.getChildren().add(unitActionsPanel);
//                } catch (Exception e) {
//                    System.out.println("game: " + game);
//                    System.out.println("selected: " + game.getSelectedObject());
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void addCityOverview() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                CityOverview co = new CityOverview((City) game.getSelectedObject());
                co.setLayoutX(40);
                co.setLayoutY(40);
                pane.getChildren().add(co);
            }
        });
    }

    public void addCityProduction(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ProductionPanel pp = new ProductionPanel((City) game.getSelectedObject());
                pp.setLayoutX(120);
                pp.setLayoutY(120);
                pane.getChildren().add(pp);
            }
        });
    }
}

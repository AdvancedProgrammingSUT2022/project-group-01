package view.components.gamePanelComponents.technologyPanel;

import com.jfoenix.controls.JFXButton;
import controller.GUIController;
import controller.ProgramController;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Player;
import model.technology.TechnologyType;
import view.Main;
import view.components.ImagesAddress;
import view.components.gamePanelComponents.TechnologyItem;
import view.components.gamePanelComponents.TechnologyTreeController;
import view.components.mapComponents.GameMapController;

import java.util.Vector;


public class TechnologyPanel {
    private Pane root = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private HBox hBox = new HBox();
    private JFXButton technologyTreeButton = new JFXButton();
    private Vector<TechnologyItem> futureTechnologyItems = new Vector<>();
    private TechnologyItem currentTechnologyItem;
    private GameMapController gameMapController;
    public TechnologyPanel(GameMapController gameMapController) {
        this.gameMapController = gameMapController;
        root.setPrefHeight(720);
        root.setPrefWidth(330);
        root.setStyle("-fx-background-color: rgba(35,33,33,0.93)");
        scrollPane.setTranslateY(190);
        scrollPane.setPrefWidth(330);
        hBox.setStyle("-fx-opacity: 0");
        hBox.setPrefHeight(581);
        hBox.setPrefWidth(328);
        hBox.setSpacing(10);
        scrollPane.setContent(hBox);
        root.getChildren().add(scrollPane);
        panesDesign();
        addCurrentTechnology();
        addFutureTechnologies();
        addTechnologyTreeButton();
        closePanel();
    }

    public Pane getRoot() {
        return root;
    }

    private void panesDesign(){
        scrollPane.setStyle("-fx-background: transparent");
        scrollPane.setStyle("-fx-background-color: transparent");
        scrollPane.getStylesheets().add(String.valueOf(Main.class.getResource("/css/InvitationMenu.css")));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hBox.setStyle("-fx-background-color: transparent");
        hBox.setStyle("-fx-background : transparent");
        hBox.setStyle("-fx-background-opacity: 0");
    }

    private void addCurrentTechnology(){
        Player p = ProgramController.getGame().getCurrentPlayer();
        TechnologyItem technologyItem = new TechnologyItem(p.getCivilization().getResearchTree().getCurrentResearch(),p,true);
        Pane currentTechnology = technologyItem.getPane();
        currentTechnology.setLayoutY(20);
        currentTechnology.setLayoutX(5);
        root.getChildren().add(currentTechnology);
        currentTechnologyItem = technologyItem;
    }

    private void addFutureTechnologies(){
        Player p = ProgramController.getGame().getCurrentPlayer();
        for(TechnologyType t : p.getCivilization().getResearchTree().getResearchableTechs()){
            TechnologyItem technologyItem = new TechnologyItem(t,p,true);
            Pane futureTechnology = technologyItem.getPane();
            futureTechnology.setOpacity(0.5);
            hBox.getChildren().add(futureTechnology);
            futureTechnologyItems.add(technologyItem);
        }
        mouseHoverFutureTechnologies();
        mouseDoubleClickOnFutureTechnologies();
    }

    private void mouseHoverFutureTechnologies(){
        for(TechnologyItem t : futureTechnologyItems){
            t.getPane().setOnMouseEntered(e -> {
                t.getPane().setOpacity(1);
            });
            t.getPane().setOnMouseExited(e -> {
                t.getPane().setOpacity(0.5);
            });
        }
    }

    private void mouseDoubleClickOnFutureTechnologies(){
        for(TechnologyItem t : futureTechnologyItems){
            t.getPane().setOnMouseClicked(event -> {
                if(event.getClickCount() == 2) {
                    ProgramController.getGame().getCurrentPlayer().getCivilization().getResearchTree().changeResearch(t.getTechnologyType());
                    updatePanel();
                }
        });
        }
    }

    private void updatePanel(){
        root.getChildren().remove(currentTechnologyItem.getPane());
        for(TechnologyItem t : futureTechnologyItems){
            root.getChildren().remove(t.getPane());
        }
        addCurrentTechnology();
        addFutureTechnologies();
    }

    private void addTechnologyTreeButton(){
        technologyTreeButton.setText("Technology Tree");
        technologyTreeButton.setTranslateY(120);
        technologyTreeButton.setTranslateX(5);
        technologyTreeButton.setPrefWidth(320);
        technologyTreeButton.setPrefHeight(50);
        technologyTreeButton.setStyle("-fx-background-color: rgba(77,146,215,0.5)");
        technologyTreeButton.setOnMouseEntered(e -> {
            technologyTreeButton.setStyle("-fx-background-color: rgb(77,146,215, 0.8)");
        });
        technologyTreeButton.setOnMouseExited(e -> {
            technologyTreeButton.setStyle("-fx-background-color: rgb(77, 146, 215, 0.5)");
        });
        technologyTreeButton.setOnMouseClicked(e -> {
            GUIController.changeMenuManually(new TechnologyTreeController(gameMapController).getRoot());
        });
        root.getChildren().add(technologyTreeButton);
    }

    private void closePanel(){
        ImageView backButton = new ImageView(ImagesAddress.BACK_BUTTON.getImage());
        backButton.setTranslateX(5);
        backButton.setTranslateY(5);
        backButton.setOnMouseClicked(e -> {
            gameMapController.getBackPane().getChildren().remove(this.getRoot());
        });
        root.getChildren().add(backButton);
    }
}

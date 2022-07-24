package view.components.unit;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.civilization.Civilization;

public class UnitPanel extends BorderPane {

    private Civilization civilization;

    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox vbox = new VBox();

    public UnitPanel(Civilization civilization){
        this.civilization = civilization;
        init();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/UnitPanel.css").toExternalForm());
        this.setMinWidth(300);
        initVBox();
        initScrollPane();
        initItems();
    }

    private void initScrollPane(){
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });

    }

    private void initVBox(){
        vbox.getStyleClass().add("v-box");
        scrollPane.setContent(vbox);
        this.setCenter(scrollPane);
    }

    private void initItems(){
        for (int i = 0; i < civilization.getUnits().size(); i++) {
            UnitRow unitRow = new UnitRow(civilization.getUnits().get(i));
            initAction(unitRow);
            vbox.getChildren().add(unitRow);

        }
    }

    private void initAction(UnitRow unitRow){
        //todo add focus and select request by parham
        unitRow.setOnMouseClicked(mouseEvent -> {
            System.out.println("you clicked here");
        });
    }

}

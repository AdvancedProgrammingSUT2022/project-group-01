package view.components.mapComponents.UserMapBuilder;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import view.components.mapComponents.UserMapBuilder.TileBuilderData;

import java.util.HashMap;
import java.util.Locale;

public class MapUserBuilderController {
    private Pane backPane;
    private Pane root = new Pane();
    private TileBuilderData tileBuilderData;
    private VBox list = new VBox();
    private HashMap<JFXButton, Terrain> terrainButton = new HashMap<>();
    public MapUserBuilderController(Pane pane, TileBuilderData tileBuilderData){
        this.backPane = pane;
        this.tileBuilderData = tileBuilderData;
        root.setPrefWidth(100);
        root.setTranslateX(10);
        root.setTranslateY(70);
        root.setStyle("-fx-background-color: rgba(26,94,128,0.89)");
        root.getChildren().add(list);
        backPane.getChildren().add(root);
        initList();
    }


    public Pane getRoot(){
        return root;
    }
    private void initList(){
        for(Terrain t : Terrain.values()){
            JFXButton button = new JFXButton(t.name().toLowerCase(Locale.ROOT));
            button.setTextFill(Color.WHITESMOKE);
            button.setOnAction(e -> {
                tileBuilderData.getTileReservedData().setTerrain(t);
                tileBuilderData.UpdatePane();
                featureList();
            });
            terrainButton.put(button, t);
            list.getChildren().add(button);
        }
    }
    private void featureList(){
        list.getChildren().clear();
        for(TerrainFeature t : tileBuilderData.getTileReservedData().getTerrain().possibleFeatures){
            JFXButton button = new JFXButton(t.name().toLowerCase(Locale.ROOT));
            button.setTextFill(Color.WHITESMOKE);
            button.setOnAction(e -> {
                tileBuilderData.getTileReservedData().setFeature(t);
                tileBuilderData.UpdatePane();
                openResourceList();
            });
            list.getChildren().add(button);
        }
        JFXButton next = new JFXButton("skip");
        next.setTextFill(Color.WHITESMOKE);
        list.getChildren().add(next);
        next.setOnAction(e -> {
            tileBuilderData.getTileReservedData().setFeature(null);
            tileBuilderData.UpdatePane();
            openResourceList();
        });
    }

    private void openResourceList(){
        list.getChildren().clear();
        for(ResourceType t : ResourceType.values()){
            if(!tileBuilderData.getTileReservedData().getTerrain().possibleResources.contains(t)){
                if(tileBuilderData.getTileReservedData().getFeature() == null || !tileBuilderData.getTileReservedData().getFeature().possibleResources.contains(t))
                    continue;
            }
            JFXButton button = new JFXButton(t.name().toLowerCase(Locale.ROOT));
            button.setTextFill(Color.WHITESMOKE);
            button.setOnAction(e -> {
                tileBuilderData.getTileReservedData().setResourceType(t);
                tileBuilderData.UpdatePane();
                openRiverList();
            });
            list.getChildren().add(button);
        }
        JFXButton next = new JFXButton("skip");
        next.setTextFill(Color.WHITESMOKE);
        list.getChildren().add(next);
        next.setOnAction(e -> {
            tileBuilderData.getTileReservedData().setResourceType(null);
            tileBuilderData.UpdatePane();
            openRiverList();
        });
    }

    private void openRiverList(){
        list.getChildren().clear();
        for(int i = 0; i < 6; i++){
            JFXButton button = new JFXButton(i + "");
            button.setTextFill(Color.WHITESMOKE);
            button.setOnAction(e -> {
                tileBuilderData.getTileReservedData().setRiver(Integer.parseInt(button.getText()));
                tileBuilderData.UpdatePane();
                openRiverList();
            });
            list.getChildren().add(button);
        }
        JFXButton next = new JFXButton("close");
        next.setTextFill(Color.WHITESMOKE);
        list.getChildren().add(next);
        next.setOnAction(e -> {
            tileBuilderData.UpdatePane();
            close();
        });
    }

    private void close(){
        backPane.getChildren().remove(root);
    }
}


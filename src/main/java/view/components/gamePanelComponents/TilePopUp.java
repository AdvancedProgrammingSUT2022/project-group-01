package view.components.gamePanelComponents;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.tile.Tile;
import view.components.ImagesAddress;

import java.util.Locale;

public class TilePopUp {
    private Pane root;
    private Tile tile;

    public TilePopUp(Tile tile) {
        this.tile = tile;
        initialize();
    }

    public Pane getRoot() {
        return root;
    }

    private void initialize(){
        root = new Pane();
        root.setStyle("-fx-background-color : rgb(229,222,218); -fx-background-radius: 20; -fx-border-width: 4px,2px,2px,2px; -fx-effect: dropshadow(gaussian, rgba(37,35,35,0.5), 0,0,0,0);");
        root.setPrefHeight(169);
        root.setPrefWidth(261);
        addNameLabels();
        addTerrainLabels();
        addFeatureLabels();
        addResourceLabels();
        addFoodImages();
        addProductImages();
        addGoldImages();
    }

    private void addNameLabels(){
        Label terrain = new Label();
        terrain.setTextFill(Color.rgb(61,58,51));
        if(tile.getTerrain() != null) {
            terrain.setText("Terrain: " + tile.getTerrain().name().toLowerCase(Locale.ROOT));
        }
        else{
            terrain.setText("Terrain: None");
        }
        terrain.setLayoutX(14);
        terrain.setLayoutY(36);
        terrain.setPrefHeight(17);
        terrain.setPrefWidth(130);
        Label feature = new Label();
        feature.setTextFill(Color.rgb(61,58,51));
        if(tile.getFeature() != null) {
            feature.setText("Feature: " + tile.getFeature().name().toLowerCase(Locale.ROOT));
        } else {
            feature.setText("Feature: None");
        }
        feature.setLayoutX(14);
        feature.setLayoutY(76);
        feature.setPrefHeight(17);
        feature.setPrefWidth(130);
        Label resource = new Label();
        resource.setTextFill(Color.rgb(61,58,51));
        if(tile.getAvailableResource() != null) {
            resource.setText("Resource: " + tile.getAvailableResource().name().toLowerCase(Locale.ROOT));
        } else {
            resource.setText("Resource: None");
        }
        resource.setLayoutX(14);
        resource.setLayoutY(116);
        resource.setPrefHeight(17);
        resource.setPrefWidth(130);
        root.getChildren().addAll(terrain, feature, resource);
    }

    private void addTerrainLabels(){
        Label terrainFood = new Label();
        terrainFood.setText(String.valueOf(tile.getTerrain().getFood()));
        terrainFood.setLayoutX(162);
        terrainFood.setLayoutY(36);
        terrainFood.setPrefHeight(17);
        terrainFood.setPrefWidth(15);
        terrainFood.setTextFill(Color.rgb(61,58,51));
        Label terrainGold = new Label();
        terrainGold.setText(String.valueOf(tile.getTerrain().getGold()));
        terrainGold.setLayoutX(195);
        terrainGold.setLayoutY(36);
        terrainGold.setPrefHeight(17);
        terrainGold.setPrefWidth(15);
        terrainGold.setTextFill(Color.rgb(61,58,51));
        Label terrainProduction = new Label();
        terrainProduction.setText(String.valueOf(tile.getTerrain().getProduction()));
        terrainProduction.setLayoutX(225);
        terrainProduction.setLayoutY(36);
        terrainProduction.setPrefHeight(17);
        terrainProduction.setPrefWidth(15);
        terrainProduction.setTextFill(Color.rgb(61,58,51));
        root.getChildren().addAll(terrainFood, terrainGold, terrainProduction);
    }

    private void addFeatureLabels(){
        Label featureFood = new Label();
        if(tile.getFeature() == null)
            featureFood.setText("0");
        else
            featureFood.setText(String.valueOf(tile.getFeature().getFood()));
        featureFood.setTextFill(Color.rgb(61,58,51));
        featureFood.setLayoutX(162);
        featureFood.setLayoutY(76);
        featureFood.setPrefHeight(17);
        featureFood.setPrefWidth(15);
        Label featureGold = new Label();
        if(tile.getFeature() == null)
            featureGold.setText("0");
        else
            featureGold.setText(String.valueOf(tile.getFeature().getGold()));
        featureGold.setTextFill(Color.rgb(61,58,51));
        featureGold.setLayoutX(195);
        featureGold.setLayoutY(76);
        featureGold.setPrefHeight(17);
        featureGold.setPrefWidth(15);
        Label featureProduction = new Label();
        if(tile.getFeature() == null)
            featureProduction.setText("0");
        else
            featureProduction.setText(String.valueOf(tile.getFeature().getProduction()));
        featureProduction.setTextFill(Color.rgb(61,58,51));
        featureProduction.setLayoutX(225);
        featureProduction.setLayoutY(76);
        featureProduction.setPrefHeight(17);
        featureProduction.setPrefWidth(15);
        root.getChildren().addAll(featureFood, featureGold, featureProduction);
    }

    private void addResourceLabels(){
        Label resourceFood = new Label();
        if(tile.getAvailableResource() == null)
            resourceFood.setText("0");
        else
            resourceFood.setText(String.valueOf(tile.getAvailableResource().food));
        resourceFood.setTextFill(Color.rgb(61,58,51));
        resourceFood.setLayoutX(162);
        resourceFood.setLayoutY(116);
        resourceFood.setPrefHeight(17);
        resourceFood.setPrefWidth(15);
        Label resourceGold = new Label();
        if(tile.getAvailableResource() == null)
            resourceGold.setText("0");
        else
            resourceGold.setText(String.valueOf(tile.getAvailableResource().gold));
        resourceGold.setTextFill(Color.rgb(61,58,51));
        resourceGold.setLayoutX(195);
        resourceGold.setLayoutY(116);
        resourceGold.setPrefHeight(17);
        resourceGold.setPrefWidth(15);
        Label resourceProduction = new Label();
        if(tile.getAvailableResource() == null)
            resourceProduction.setText("0");
        else
            resourceProduction.setText(String.valueOf(tile.getAvailableResource().production));
        resourceProduction.setTextFill(Color.rgb(61,58,51));
        resourceProduction.setLayoutX(225);
        resourceProduction.setLayoutY(116);
        resourceProduction.setPrefHeight(17);
        resourceProduction.setPrefWidth(15);
        root.getChildren().addAll(resourceFood, resourceGold, resourceProduction);
    }

    private void addFoodImages(){
        ImageView foodOne = new ImageView(ImagesAddress.TILE_PANEL_FOOD.getImage());
        foodOne.setFitHeight(15);
        foodOne.setFitWidth(15);
        foodOne.setLayoutX(147);
        foodOne.setLayoutY(37);
        foodOne.setPickOnBounds(true);
        foodOne.setPreserveRatio(true);
        ImageView foodTwo = new ImageView(ImagesAddress.TILE_PANEL_FOOD.getImage());
        foodTwo.setFitHeight(15);
        foodTwo.setFitWidth(15);
        foodTwo.setLayoutX(147);
        foodTwo.setLayoutY(77);
        foodTwo.setPickOnBounds(true);
        foodTwo.setPreserveRatio(true);
        ImageView foodThree = new ImageView(ImagesAddress.TILE_PANEL_FOOD.getImage());
        foodThree.setFitHeight(15);
        foodThree.setFitWidth(15);
        foodThree.setLayoutX(147);
        foodThree.setLayoutY(117);
        foodThree.setPickOnBounds(true);
        foodThree.setPreserveRatio(true);
        root.getChildren().addAll(foodOne, foodTwo, foodThree);
    }

    private void addGoldImages(){
        ImageView goldOne = new ImageView(ImagesAddress.TILE_PANEL_GOLD.getImage());
        goldOne.setFitHeight(15);
        goldOne.setFitWidth(15);
        goldOne.setLayoutX(178);
        goldOne.setLayoutY(37);
        goldOne.setPickOnBounds(true);
        goldOne.setPreserveRatio(true);
        ImageView goldTwo = new ImageView(ImagesAddress.TILE_PANEL_GOLD.getImage());
        goldTwo.setFitHeight(15);
        goldTwo.setFitWidth(15);
        goldTwo.setLayoutX(178);
        goldTwo.setLayoutY(77);
        goldTwo.setPickOnBounds(true);
        goldTwo.setPreserveRatio(true);
        ImageView goldThree = new ImageView(ImagesAddress.TILE_PANEL_GOLD.getImage());
        goldThree.setFitHeight(15);
        goldThree.setFitWidth(15);
        goldThree.setLayoutX(178);
        goldThree.setLayoutY(117);
        goldThree.setPickOnBounds(true);
        goldThree.setPreserveRatio(true);
        root.getChildren().addAll(goldOne, goldTwo, goldThree);
    }

    private void addProductImages(){
        ImageView productOne = new ImageView(ImagesAddress.TILE_PANEL_PRODUCTION.getImage());
        productOne.setFitHeight(15);
        productOne.setFitWidth(15);
        productOne.setLayoutX(210);
        productOne.setLayoutY(37);
        productOne.setPickOnBounds(true);
        productOne.setPreserveRatio(true);
        ImageView productTwo = new ImageView(ImagesAddress.TILE_PANEL_PRODUCTION.getImage());
        productTwo.setFitHeight(15);
        productTwo.setFitWidth(15);
        productTwo.setLayoutX(210);
        productTwo.setLayoutY(77);
        productTwo.setPickOnBounds(true);
        productTwo.setPreserveRatio(true);
        ImageView productThree = new ImageView(ImagesAddress.TILE_PANEL_PRODUCTION.getImage());
        productThree.setFitHeight(15);
        productThree.setFitWidth(15);
        productThree.setLayoutX(210);
        productThree.setLayoutY(117);
        productThree.setPickOnBounds(true);
        productThree.setPreserveRatio(true);
        root.getChildren().addAll(productOne, productTwo, productThree);
    }
}

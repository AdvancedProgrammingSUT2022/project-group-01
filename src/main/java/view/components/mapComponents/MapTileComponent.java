package view.components.mapComponents;

import controller.ProgramController;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.map.SavedMap;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import utils.Pair;
import view.components.GameVisualUI.UIStates;
import view.components.GameVisualUI.WorkingObjectType;
import view.components.ImagesAddress;
import view.components.city.CityOverview;
import view.components.gamePanelComponents.TilePopUp;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;
import view.components.unit.UnitView;

import java.util.HashMap;
import java.util.HashSet;


public class MapTileComponent {
    private final double radius = 200;
    Pane pane;
    SavedMap savedMap;
    Tile tile;
    Polygon backgroundShape;
    ImageView building;
    ImageView resource;
    ImageView improvement;
    ImageView militaryUtil;
    ImageView civilianUtil;
    ImageView road;
    UnitView armedUnit;
    UnitView civilianUnit;
    ImageView citizen;
    GameMapController gameMapController;

    public MapTileComponent(Tile tile,GameMapController gameMapController) {
        this.gameMapController = gameMapController;
        savedMap = ProgramController.getGame().getCurrentPlayer().getSavedMap();
        this.tile = tile;
    }

    public Pane initialize() {
        pane = new Pane();
        backgroundShape = new Hexagon(radius, getBackgroundImage()).getPolygon();
        pane.getChildren().add(backgroundShape);
        addResources();
        addImprovement();
        visibilityVisualization();
        //initCitizen();
        initCity();
        initRoad();
        addRivers();
        initRailRoad();
        initUnits();
        mouseClicks();
        return pane;
    }

    private void mouseClicks(){
        pane.setOnMouseClicked(event -> {
            tile.getBoarder(0).setRiver();
    if(event.isShiftDown()) {
        System.out.println("hmmmm?");
        gameMapController.destroyTemporaryPanels();
        Pane tilePopUp = new TilePopUp(tile).getRoot();
        gameMapController.addPanelToBackPane(tilePopUp);
        tilePopUp.setTranslateX(pane.getTranslateX());
        tilePopUp.setTranslateY(pane.getTranslateY());
        tilePopUp.setOnMouseClicked(event1 -> {
            gameMapController.destroyTemporaryPanels();
        });
    }else if(event.isAltDown()){
        gameMapController.getUiStatesController().addObjectByJob(new Pair<>(this,WorkingObjectType.CIVILIAN_UNIT));
        gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_FOUND_CITY);
    }

        });

    }

    private void initCitizen(){
        if(tile.getCivilization() == null) return;
        if(!tile.getCivilization().equals(ProgramController.getGame().getCurrentPlayer().getCivilization())) return;
        citizen = new ImageView(ImagesAddress.CITIZEN.getImage());
        citizen.setTranslateX(radius);
        citizen.setTranslateY(20);
        citizen.setEffect(new DropShadow(5, Color.BLACK));
        //TODO : click on citizen
        pane.getChildren().add(citizen);
    }

    private void initRoad(){
        if(!tile.doesHaveRoad()) return;
        road = new ImageView(ImagesAddress.ROAD.getImage());
        road.setTranslateX(radius);
        road.setTranslateY(10);
        pane.getChildren().add(road);
    }

    private void initRailRoad(){
        if(!tile.doesHaveRailRoad()) return;
        road = new ImageView(ImagesAddress.RAILROAD.getImage());
        road.setTranslateX(radius);
        road.setTranslateY(10);
        pane.getChildren().add(road);
    }
    private void initUnits(){
        if(savedMap.getVisibilityState(tile) != Tile.VisibilityState.VISIBLE) return;
        if(tile.getArmedUnit() != null){
            armedUnit = new UnitView(tile.getArmedUnit(),pane);
            armedUnit.setTranslateX(radius/2 - 20);
            armedUnit.setTranslateY(radius / 2);
            pane.getChildren().add(armedUnit);
        }
        if(tile.getCivilianUnit() != null){
            civilianUnit = new UnitView(tile.getCivilianUnit(),pane);
            civilianUnit.setTranslateX(radius);
            civilianUnit.setTranslateY(radius / 2);
            pane.getChildren().add(civilianUnit);
        }
    }

    private void initCityClick(){
        for(Pair<Tile,Integer> p : tile.getOwnerCity().getPurchasableTiles()){
            gameMapController.getTileComponentInMap(p.getFirst().getPCoordinate(), p.getFirst().getQCoordinate()).highlight(2);
        }
        for(Tile t : tile.getOwnerCity().getTiles()){
            gameMapController.getTileComponentInMap(t.getPCoordinate(),t.getQCoordinate()).initCitizen();
            gameMapController.getTileComponentInMap(t.getPCoordinate(),t.getQCoordinate()).highlight(1);
        }
    }

    private void initCity(){
        if(savedMap.getVisibilityState(tile) != Tile.VisibilityState.VISIBLE) return;
        if(tile.getInnerCity() == null) return;
        ImageView city = new ImageView(ImagesAddress.CITY.getImage());
        city.setTranslateX(radius/2 + 40);
        city.setTranslateY(radius/2 + 20);
        city.setOnMouseClicked(e->{
            gameMapController.getUiStatesController().setCurrentState(UIStates.CITY_INFO);
            gameMapController.getUiStatesController().addObjectByJob(new Pair<>(this, WorkingObjectType.CITY));
            initCityClick();
        });
        pane.getChildren().add(city);
    }
    private Image getBackgroundImage() {
        Terrain terrain = savedMap.getTerrain(tile);
        TerrainFeature feature = savedMap.getFeature(tile);
        if(feature == null){
            switch (terrain){
                case DESERT:
                    return ImagesAddress.DESERT.getImage();
                case GRASSLAND:
                    return ImagesAddress.GRASSLAND.getImage();
                case HILLS:
                    return ImagesAddress.HILLS.getImage();
                case MOUNTAIN:
                    return ImagesAddress.MOUNTAIN.getImage();
                case OCEAN:
                    return ImagesAddress.OCEAN.getImage();
                case PLAINS:
                    return ImagesAddress.PLAINS.getImage();
                case TUNDRA:
                    return ImagesAddress.TUNDRA.getImage();
                case SNOW:
                    return ImagesAddress.SNOW.getImage();
            }
        }
        else {
            switch (terrain) {
                case DESERT:
                    switch (feature) {
                        case FLOOD_PLAINS:
                            return ImagesAddress.DESERT_FLOODPLAINS.getImage();
                        case OASIS:
                            return ImagesAddress.DESERT_OASIS.getImage();
                        default:
                            return ImagesAddress.DESERT.getImage();
                    }
                case GRASSLAND:
                    switch (feature) {
                        case FOREST:
                            return ImagesAddress.GRASSLAND_FOREST.getImage();
                        case MARSH:
                            return ImagesAddress.GRASSLAND_MARSH.getImage();
                        default:
                            return ImagesAddress.GRASSLAND.getImage();
                    }
                case HILLS:
                    switch (feature) {
                        case JUNGLE:
                            return ImagesAddress.HILLS_JUNGLE.getImage();
                        case FOREST:
                            return ImagesAddress.HILLS_FOREST.getImage();
                        default:
                            return ImagesAddress.HILLS.getImage();
                    }
                case MOUNTAIN:
                    return ImagesAddress.MOUNTAIN.getImage();
                case OCEAN:
                    if (feature.equals(TerrainFeature.ICE)) return ImagesAddress.OCEAN_ICE.getImage();
                    return ImagesAddress.OCEAN.getImage();
                case PLAINS:
                    switch (feature) {
                        case JUNGLE:
                            return ImagesAddress.PLAINS_JUNGLE.getImage();
                        case FOREST:
                            return ImagesAddress.PLAINS_FOREST.getImage();
                        default:
                            return ImagesAddress.PLAINS.getImage();
                    }
                case SNOW:
                    return ImagesAddress.SNOW.getImage();
                case TUNDRA:
                    if (feature.equals(TerrainFeature.FOREST)) return ImagesAddress.TUNDRA_FOREST.getImage();
                    return ImagesAddress.TUNDRA.getImage();
                default:
                    return null;
            }
        }
        return null;
    }

    private void visibilityVisualization() {
        if (savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.DISCOVERED)) {
            Polygon polygon = new Polygon();
            polygon.setEffect(new GaussianBlur());
            polygon.setFill(Color.color(0, 0, 0, 0.7));
            double radian = Math.PI / 3;
            for (int i = 0; i < 6; i++) {
                double angle = radian * i;
                polygon.getPoints().add(Math.cos(angle) * radius / 1.1);
                polygon.getPoints().add(Math.sin(angle) * radius / 1.1);
            }
            pane.getChildren().add(polygon);
        } else if (savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.FOG_OF_WAR)) {
            pane.getChildren().add(new Hexagon(radius, ImagesAddress.FOG_OF_WAR.getImage()).getPolygon());
        }
    }

    private void addResources() {
        if (savedMap.getResourceTypes(tile) == null) return;
        switch (savedMap.getResourceTypes(tile)) {
            case BANANAS:
                resource = new ImageView(ImagesAddress.BANANAS.getImage());
                break;
            case CATTLE:
                resource = new ImageView(ImagesAddress.CATTLE.getImage());
                break;
            case DEER:
                resource = new ImageView(ImagesAddress.DEER.getImage());
                break;
            case SHEEP:
                resource = new ImageView(ImagesAddress.SHEEP.getImage());
                break;
            case WHEAT:
                resource = new ImageView(ImagesAddress.WHEAT.getImage());
                break;
            case COAL:
                resource = new ImageView(ImagesAddress.COAL.getImage());
                break;
            case HORSES:
                resource = new ImageView(ImagesAddress.HORSES.getImage());
                break;
            case IRON:
                resource = new ImageView(ImagesAddress.IRON.getImage());
                break;
            case COTTON:
                resource = new ImageView(ImagesAddress.COTTON.getImage());
                break;
            case DYES:
                resource = new ImageView(ImagesAddress.DYES.getImage());
                break;
            case FURS:
                resource = new ImageView(ImagesAddress.FURS.getImage());
                break;
            case GEMS:
                resource = new ImageView(ImagesAddress.GEMS.getImage());
                break;
            case GOLD:
                resource = new ImageView(ImagesAddress.GOLD.getImage());
                break;
            case INCENSE:
                resource = new ImageView(ImagesAddress.INCENSE.getImage());
                break;
            case IVORY:
                resource = new ImageView(ImagesAddress.IVORY.getImage());
                break;
            case MARBLE:
                resource = new ImageView(ImagesAddress.MARBLE.getImage());
                break;
            case SILK:
                resource = new ImageView(ImagesAddress.SILK.getImage());
                break;
            case SILVER:
                resource = new ImageView(ImagesAddress.SILVER.getImage());
                break;
            case SUGAR:
                resource = new ImageView(ImagesAddress.SUGAR.getImage());
        }
        pane.getChildren().add(resource);
        resource.setTranslateX(radius);
        resource.setTranslateY(radius * 1.5);
        resource.maxHeight(32);
        resource.maxWidth(32);
    }

    private void addImprovement(){
        if(savedMap.getVisibilityState(tile) != Tile.VisibilityState.VISIBLE) return;
        if(tile.getBuiltImprovement() == null) return;
        switch (tile.getBuiltImprovement()){
            case MANUFACTORY:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_MANUFACTORY.getImage());
                break;
            case TRADING_POST:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_TRADING_POST.getImage());
                break;
            case QUARRY:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_QUARRY.getImage());
                break;
            case LUMBER_MILL:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_LUMBER_MILL.getImage());
                break;
            case FARM:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_FARM.getImage());
                break;
            case PASTURE:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_PASTURE.getImage());
                break;
            case CAMP:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_CAMP.getImage());
                break;
            case MINE:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_MINE.getImage());
                break;
            case PLANTATION:
                improvement = new ImageView(ImagesAddress.IMPROVEMENT_PLANTATION.getImage());
                break;
        }
        pane.getChildren().add(improvement);
        improvement.setTranslateX(-24);
        improvement.setTranslateY(0);
        improvement.maxWidth(48);
        improvement.maxHeight(48);
    }

    private void addRivers(){
        if(savedMap.getVisibilityState(tile) == Tile.VisibilityState.FOG_OF_WAR) return;
        if(tile.getBoarder(0).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setTranslateX(50);
            river.setTranslateY(0);
        }
        if(tile.getBoarder(3).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setTranslateX(70);
            river.setRotate(180);
            river.setTranslateY(2*radius - 50);
            river.maxHeight(10);
            river.maxWidth(32);
        }
        if(tile.getBoarder(1).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(60);
            river.setTranslateX(radius + 25);
            river.setTranslateY(radius/2 - 10);
            river.maxHeight(32);
            river.maxWidth(10);
        }
        if(tile.getBoarder(2).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(120);
            river.setTranslateX(radius + 20);
            river.setTranslateY(3*radius/2 - 25);
            river.maxHeight(32);
            river.maxWidth(10);
        }
        if(tile.getBoarder(4).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(240);
            river.setTranslateX(-70);
            river.setTranslateY(3 *radius/2 - 25);
            river.maxHeight(10);
            river.maxWidth(32);
        }
        if(tile.getBoarder(5).isRiver()){
            ImageView river = new ImageView(ImagesAddress.RIVER.getImage());
            pane.getChildren().add(river);
            river.setRotate(300);
            river.setTranslateX(-80);
            river.setTranslateY(radius/2 - 10);
            river.maxHeight(32);
            river.maxWidth(10);
        }
    }

    private void highlight(int i ){
        if(i == 1)
            backgroundShape.setStroke(Color.rgb(51,255,159,0.7));
        else
            backgroundShape.setStroke(Color.rgb(255,252,10,0.7));
    }
    private void deHighlight(){
        backgroundShape.setStroke(Color.SANDYBROWN);
    }
    public Tile getTile(){
        return tile;
    }

    public static class Hexagon {
        private Polygon polygon;
        private final double radian = Math.PI / 3;

        public Hexagon(double radius, Image image) {
            makeHexagon(radius, image);
        }

        private void makeHexagon(double radius, Image image) {
            polygon = new Polygon();
            polygon.setFill(new ImagePattern(image));
            polygon.setStroke(Color.WHITESMOKE);
            polygon.setStrokeWidth(20);
            //polygon.setStroke(new ImagePattern(ImagesAddress.OCEAN.getImage()));
            polygon.setStroke(Color.SANDYBROWN);
            polygon.setStrokeType(StrokeType.OUTSIDE);
            for (int i = 0; i < 6; i++) {
                double angle = radian * i;
                polygon.getPoints().add(Math.cos(angle) * radius / 1);
                polygon.getPoints().add(Math.sin(angle) * radius / 1);
            }
            polygon.setTranslateX(radius);
            polygon.setTranslateY(radius);
        }

        public Polygon getPolygon() {
            return polygon;
        }

    }



}

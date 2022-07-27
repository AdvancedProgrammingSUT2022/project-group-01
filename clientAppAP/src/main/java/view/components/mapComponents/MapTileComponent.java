package view.components.mapComponents;

import controller.ProgramController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import lombok.Getter;
import model.civilization.Person;
import model.map.SavedMap;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
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
    @Getter
    private final double radius = 200;
    Pane pane;
    SavedMap savedMap;
    Tile tile;
    Polygon backgroundShape;
    ImageView resource;
    ImageView improvement;
    ImageView road;
    UnitView armedUnit;
    UnitView civilianUnit;
    ImageView citizen;
    ImageView buyTile;
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
            if(!ProgramController.getLoggedInUser().equals(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getUser())) return;
            if(event.getButton().equals(MouseButton.PRIMARY))
                if(event.isShiftDown())
                    gameMapController.getUiStatesController().addObjectByJob(new Pair<>(this,WorkingObjectType.TILE));
        });
    }

    public void initCitizen(){
        if(tile.getCivilization() == null) return;
        if(!tile.getCivilization().equals(ProgramController.getGame().getCurrentPlayer().getCivilization())) return;
        citizen = new ImageView(ImagesAddress.CITIZEN.getImage());
        citizen.setTranslateX(radius - 40);
        citizen.setTranslateY(30);
        citizen.setEffect(new DropShadow(5, Color.BLACK));
        System.out.println("in here citizen");
        if(tile.getPeopleInside().isEmpty())
            citizen.setOpacity(0.4);
        citizen.setOnMouseClicked(event -> {
            if(!ProgramController.getLoggedInUser().equals(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getUser())) return;
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(tile.getPeopleInside().isEmpty()){
                    for(int j = 0; j < tile.getOwnerCity().getPopulation().size(); j++){
                        if(tile.getOwnerCity().getPopulation().get(j).getTile() == null){
                            HashMap<String,String> data = new HashMap<>();
                            data.put("position",String.valueOf(tile.getMapNumber()));
                            data.put("index",String.valueOf(j + 1));
                            new PopUp().run(PopUpStates.OK,gameMapController.getGameMenuController().setTileForPopulation(data));
                            citizen.setOpacity(1);
                            break;
                        }
                    }
                }else{
                    HashMap<String,String> data = new HashMap<>();
                    for(int j = 0; j < tile.getOwnerCity().getPopulation().size(); j++){
                        if(tile.getOwnerCity().getPopulation().get(j).getTile().equals(tile)){
                            data.put("index",String.valueOf(j + 1));
                            new PopUp().run(PopUpStates.OK,gameMapController.getGameMenuController().deletePopulation(data));
                            citizen.setOpacity(0.4);
                            break;
                        }
                    }
                }
            }

        });
        pane.getChildren().add(citizen);
    }

    public void initTileBuy(Tile p){
        if(tile.getCivilization() != null) return;
        buyTile = new ImageView(ImagesAddress.BUY_TILE.getImage());
        buyTile.setTranslateX(radius - 40);
        buyTile.setTranslateY(40);
        buyTile.setEffect(new DropShadow(5, Color.BLACK));
        buyTile.setOnMouseClicked(event -> {
            if(!ProgramController.getLoggedInUser().equals(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getUser())) return;
            if(event.getButton().equals(MouseButton.PRIMARY)){
                HashMap<String,String> data = new HashMap<>();
                for(int i = 0; i < p.getOwnerCity().getPurchasableTiles().size(); i++){
                    if(p.getOwnerCity().getPurchasableTiles().get(i).getFirst().equals(tile)){
                        data.put("index",String.valueOf(i));
                        break;
                    }
                }
                new PopUp().run(PopUpStates.OK,gameMapController.getGameMenuController().purchaseTile(data));
                gameMapController.update();
            }
        });
        pane.getChildren().add(buyTile);

    }

    private void initRoad(){
        if(!tile.doesHaveRoad()) return;
        if(!savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE)) return;
        road = new ImageView(ImagesAddress.ROAD.getImage());
        road.setTranslateX(radius - 20);
        road.setTranslateY(40);
        pane.getChildren().add(road);
    }

    private void initRailRoad(){
        if(!tile.doesHaveRailRoad()) return;
        if(!savedMap.getVisibilityState(tile).equals(Tile.VisibilityState.VISIBLE)) return;
        road = new ImageView(ImagesAddress.RAILROAD.getImage());
        road.setTranslateX(radius + 20);
        road.setTranslateY(40);
        pane.getChildren().add(road);
    }
    private void initUnits(){
        if(savedMap.getVisibilityState(tile) != Tile.VisibilityState.VISIBLE) return;
        if(tile.getArmedUnit() != null){
            armedUnit = new UnitView(tile.getArmedUnit(),pane,this);
            armedUnit.setTranslateX(radius + 20);
            armedUnit.setTranslateY(radius / 2 - 30);
            pane.getChildren().add(armedUnit);
            armedUnit.addJobCircle();
        }
        if(tile.getCivilianUnit() != null){
            civilianUnit = new UnitView(tile.getCivilianUnit(),pane,this);
            civilianUnit.setTranslateX(radius + 20);
            civilianUnit.setTranslateY(radius / 2 + 60);
            pane.getChildren().add(civilianUnit);
            civilianUnit.addJobCircle();
        }
    }

    private void initCity(){
        if(savedMap.getVisibilityState(tile) != Tile.VisibilityState.VISIBLE) return;
        if(tile.getInnerCity() == null) return;
        ImageView city = new ImageView(ImagesAddress.CITY.getImage());
        city.setTranslateX(radius/2 + 40);
        city.setTranslateY(radius/2 + 20);
        city.setOnMouseClicked(e->{
            if(!ProgramController.getLoggedInUser().equals(gameMapController.getGameMenuController().getGame().getCurrentPlayer().getUser())) return;
            //gameMapController.getUiStatesController().setCurrentState(UIStates.CITY_INFO);
            gameMapController.getUiStatesController().addObjectByJob(new Pair<>(this, WorkingObjectType.CITY));
            //initCityClick();
        });
        city.setOnMouseEntered(e -> {
            showCityHealth();
        });
        pane.getChildren().add(city);
    }

    private void showCityHealth(){
        Rectangle base = new Rectangle();
        base.setArcHeight(5);
        base.setArcWidth(5);
        base.setWidth(60);
        base.setFill(Color.rgb(40,40,40));
        base.setHeight(13);
        Rectangle hitPoint = new Rectangle();
        hitPoint.setArcHeight(5);
        hitPoint.setFill(Color.rgb(210,79,79));
        hitPoint.setHeight(13);
        hitPoint.setArcWidth(5);
        hitPoint.setWidth((tile.getInnerCity().getHealth() / tile.getInnerCity().getCityMaxHealth()) * 60);

        base.setTranslateX(radius/2 + 40);
        base.setTranslateY(radius/2 - 10);
        hitPoint.setTranslateX(radius/2 + 40);
        hitPoint.setTranslateY(radius/2 - 10);
        pane.getChildren().add(base);
        pane.getChildren().add(hitPoint);
        Timeline t = new Timeline(new KeyFrame(Duration.millis(500), e -> {
        }));
        t.setOnFinished(e -> {
            pane.getChildren().remove(base);
            pane.getChildren().remove(hitPoint);
        });
        t.play();
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
            polygon.setTranslateX(radius);
            polygon.setTranslateY(radius);
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
            default:
                return;
        }
        pane.getChildren().add(improvement);
        improvement.setTranslateX(radius);
        improvement.setTranslateY(10);
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

    public void highlight(int i){
        if(i == 1)
            backgroundShape.setStroke(Color.rgb(51,255,159,0.7));
        else
            backgroundShape.setStroke(Color.rgb(255,252,10,0.7));
    }

    public Pane getPane() {
        return pane;
    }

    public UnitView getArmedUnit() {
        return armedUnit;
    }



    public UnitView getCivilianUnit() {
        return civilianUnit;
    }


    private void deHighlight(){
        backgroundShape.setStroke(Color.SANDYBROWN);
    }
    public Tile getTile(){
        return tile;
    }

    public GameMapController getGameMapController() {
        return gameMapController;
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
            polygon.setStroke(Color.rgb(185,175,163));
            polygon.setStrokeType(StrokeType.OUTSIDE);
            for (int i = 0; i < 6; i++) {
                double angle = radian * i;
                polygon.getPoints().add(Math.cos(angle) * radius / 1);
                polygon.getPoints().add(Math.sin(angle) * radius / 1);
                Line line = new Line();
            }
            polygon.setTranslateX(radius);
            polygon.setTranslateY(radius);
        }

        public Polygon getPolygon() {
            return polygon;
        }

    }



}

package view.components.GameVisualUI;

import controller.GameMenuController;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import model.tile.Tile;
import utils.Pair;
import view.components.city.CityOverview;
import view.components.gamePanelComponents.TilePopUp;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.MapTileComponent;
import view.components.popup.PopUp;
import view.components.popup.PopUpStates;
import view.components.unit.UnitActionsPanel;

import java.util.HashMap;
import java.util.Vector;

public class UIStatesController {
    private Vector<Pair<MapTileComponent,WorkingObjectType>> savedClicks = new Vector<>();
    private UIStates currentState = UIStates.NONE;
    private GameMapController gameMapController;
    private GameMenuController gameMenuController;


    public UIStatesController(GameMapController gameMapController, GameMenuController gameMenuController) {
        this.gameMapController = gameMapController;
        this.gameMenuController = gameMenuController;
    }


    public void setCurrentState(UIStates state) {
        currentState = state;
        doOnceJob();
        gameMapController.update();
    }

    private void doOnceJob() {
        if(savedClicks.isEmpty()) return;
        if(currentState.equals(UIStates.UNIT_SLEEP))
            sleepUnit();
        else if(currentState.equals(UIStates.UNIT_ALERT))
            alertUnit();
        else if(currentState.equals(UIStates.UNIT_FORTIFY))
            fortifyUnit();
        else if(currentState.equals(UIStates.UNIT_GARRISON))
            garrisonUnit();
        else if(currentState.equals(UIStates.UNIT_FORTIFY_UNTIL_HEEL))
            fortifyUntilHeelUnit();
        else if(currentState.equals(UIStates.UNIT_PILLAGE))
            pillageUnit();
        else if(currentState.equals(UIStates.UNIT_FOUND_CITY))
            foundCity();
        else if(currentState.equals(UIStates.UNIT_CANCEL))
            unitCancel();
        else if(currentState.equals(UIStates.UNIT_WAKE))
            wakeUnit();
        else if(currentState.equals(UIStates.UNIT_DELETE))
            deleteUnit();
        else if(currentState.equals(UIStates.UNIT_BUILD_ROAD))
            buildRoad();
        else if(currentState.equals(UIStates.UNIT_BUILD_RAILROAD))
            buildRailRoad();
        else if(currentState.equals(UIStates.REPAIR))
            repairWorker();
        else if(currentState.equals(UIStates.REMOVE))
            removeWorker();
        else if(currentState.equals(UIStates.FARM))
            buildFarm();
        else if(currentState.equals(UIStates.CAMP))
            buildCamp();
        else if(currentState.equals(UIStates.MINE))
            buildMine();
        else if(currentState.equals(UIStates.TRADING_POST))
            buildTradingPost();
        else if(currentState.equals(UIStates.LUMBER_MILL))
            buildLumberMill();
        else if(currentState.equals(UIStates.PASTURE))
            buildPasture();
        else if(currentState.equals(UIStates.PLANTATION))
            buildPlantation();
        else if(currentState.equals(UIStates.QUARRY))
            buildQuarry();
        else if(currentState.equals(UIStates.MANUFACTORY))
            buildManufactory();
        else if(currentState.equals(UIStates.UNIT_SETUP))
            unitSetup();
    }

    public void addObjectByJob(Pair<MapTileComponent,WorkingObjectType> newClick) {
        System.out.println(currentState);
        if(currentState.equals(UIStates.NONE)){
            if(newClick.getSecond().equals(WorkingObjectType.CITY)){
                reset();
                selectCity(newClick);
                currentState = UIStates.CITY_INFO;
                System.out.println("here city info");
                showCityPanel(newClick);
                return;
            }
            else if(newClick.getSecond().equals(WorkingObjectType.ARMED_UNIT) || newClick.getSecond().equals(WorkingObjectType.CIVILIAN_UNIT)){
                reset();
                selectUnit(newClick);
                savedClicks.add(newClick);
            }
            else if(newClick.getSecond().equals(WorkingObjectType.TILE)){
                System.out.println("tile chosen");
                reset();
                currentState = UIStates.TILE_INFO;
                showTileInfoPopUp(newClick);
            }
        }
        else if(currentState.equals(UIStates.CITY_INFO)){
            if(newClick.getSecond().equals(WorkingObjectType.CITY)){
                reset();
                showCityPanel(newClick);
                return;
            }else if(newClick.getSecond().equals(WorkingObjectType.ARMED_UNIT) || newClick.getSecond().equals(WorkingObjectType.CIVILIAN_UNIT)){
                reset();
                selectUnit(newClick);
                savedClicks.add(newClick);
            }else if(newClick.getSecond().equals(WorkingObjectType.TILE)){
                reset();
            }
        }
        else if(currentState.equals(UIStates.TILE_INFO)){
            if(newClick.getSecond().equals(WorkingObjectType.TILE)){
                reset();
                showTileInfoPopUp(newClick);
            }else if(newClick.getSecond().equals(WorkingObjectType.ARMED_UNIT) || newClick.getSecond().equals(WorkingObjectType.CIVILIAN_UNIT)){
                reset();
                selectUnit(newClick);
                savedClicks.add(newClick);
            }
        }
        else if(currentState.equals(UIStates.UNIT_MOVE))
            moveUnit(newClick);
        else if(currentState.equals(UIStates.UNIT_RANGED_ATTACK))
            rangedAttackUnit(newClick);
        else if(currentState.equals(UIStates.UNIT_MELEE_ATTACK))
            meleeAttackUnit(newClick);
        else if(currentState.equals(UIStates.CITY_ATTACK))
            cityAttack(newClick);
        gameMapController.update();
    }


    private void moveUnit(Pair<MapTileComponent,WorkingObjectType> destination) {
        HashMap<String,String> sendingData = new HashMap<>();
        sendingData.put("position",String.valueOf(destination.getFirst().getTile().getMapNumber()));
        new PopUp().run(PopUpStates.OK,gameMenuController.unitMove(sendingData));
        if(savedClicks.get(0).getSecond().equals(WorkingObjectType.ARMED_UNIT))
            savedClicks.get(0).getFirst().getArmedUnit().move(destination.getFirst());
        else
            savedClicks.get(0).getFirst().getCivilianUnit().move(destination.getFirst());
        reset();
    }

    private void selectCity(Pair<MapTileComponent,WorkingObjectType> city) {
        savedClicks.add(city);
        HashMap<String,String> data = new HashMap<>();
        data.put("position",String.valueOf(city.getFirst().getTile().getMapNumber()));
        gameMapController.getGameMenuController().selectCity(data);
    }

    private void selectUnit(Pair<MapTileComponent,WorkingObjectType> origin){
        HashMap<String,String> selectUnit = new HashMap<>();
        selectUnit.put("position",String.valueOf(origin.getFirst().getTile().getMapNumber()));
        UnitActionsPanel uap;
        if(origin.getSecond().equals(WorkingObjectType.CIVILIAN_UNIT)) {
            selectUnit.put("section", "civilian");
            uap = new UnitActionsPanel(origin.getFirst().getCivilianUnit());
            uap.setTranslateX(origin.getFirst().getPane().getTranslateX() + origin.getFirst().getCivilianUnit().getTranslateX() - 10);
            uap.setTranslateY(origin.getFirst().getPane().getTranslateY() + origin.getFirst().getCivilianUnit().getTranslateY() - 20);
            System.out.println("camee tooo heeereeee");
        }
        else {
            selectUnit.put("section", "armed");
            uap = new UnitActionsPanel(origin.getFirst().getArmedUnit());
            uap.setTranslateX(origin.getFirst().getPane().getTranslateX() + origin.getFirst().getArmedUnit().getTranslateX() - 10);
            uap.setTranslateY(origin.getFirst().getPane().getTranslateY() + origin.getFirst().getArmedUnit().getTranslateY() - 20);
        }
        if(!uap.getUnit().getOwnerCivilization().equals(gameMenuController.getGame().getCurrentPlayer().getCivilization()))
            return;
        System.out.println("even here?");
        gameMenuController.selectUnit(selectUnit);
        gameMapController.addPanelToBackPane(uap);
    }

    private void sleepUnit() {
        gameMenuController.unitSleep(new HashMap<>());
        reset();
    }

    private void alertUnit(){
        gameMenuController.unitAlert(new HashMap<>());
        reset();
    }

    private void reset(){
        savedClicks.clear();
        currentState = UIStates.NONE;
        gameMapController.clearBackPanePanels();
    }

    private void fortifyUnit(){
        gameMenuController.unitFortify(new HashMap<>());
        reset();
    }

    private void garrisonUnit(){
        reset();
    }

    private void fortifyUntilHeelUnit(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitFortifyUntilHeal(new HashMap<>()));
        reset();
    }

    private void rangedAttackUnit(Pair<MapTileComponent, WorkingObjectType> newClick) {
        HashMap<String,String> sendingData = new HashMap<>();
        sendingData.put("position",String.valueOf(newClick.getFirst().getTile().getMapNumber()));
        new PopUp().run(PopUpStates.OK,gameMenuController.unitRangedAttack(sendingData));
        reset();
    }

    private void meleeAttackUnit(Pair<MapTileComponent, WorkingObjectType> newClick) {
        HashMap<String,String> sendingData = new HashMap<>();
        sendingData.put("position",String.valueOf(newClick.getFirst().getTile().getMapNumber()));
        new PopUp().run(PopUpStates.OK,gameMenuController.unitMeleeAttack(sendingData));
        reset();
    }

    private void cityAttack(Pair<MapTileComponent, WorkingObjectType> newClick) {
        //TODO: implement
//        HashMap<String,String> sendingData = new HashMap<>();
//        sendingData.put("position",String.valueOf(newClick.getFirst().getTile().getMapNumber()));
//        gameMenuController.cityAttack()
//        reset();
    }

    private void pillageUnit(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitPillage(new HashMap<>()));
        reset();
    }

    private void foundCity(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitFoundCity(new HashMap<>()));
        reset();
    }

    private void unitCancel(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitCancelMission(new HashMap<>()));
        reset();
    }

    private void wakeUnit(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitWake(new HashMap<>()));
        reset();
    }

    private void deleteUnit(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitDelete(new HashMap<>()));
        reset();
    }

    private void repairWorker(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitRepair(new HashMap<>()));
        reset();
    }

    private void removeWorker(){
        new PopUp().run(PopUpStates.OK,gameMenuController.unitRemove(new HashMap<>()));
        reset();
    }

    private void buildRoad(){
        new PopUp().run(PopUpStates.OK,gameMenuController.buildRoad(new HashMap<>()));
        reset();
    }

    private void buildRailRoad(){
        new PopUp().run(PopUpStates.OK,gameMenuController.buildRail(new HashMap<>()));
        reset();
    }

    private void showCityPanel(Pair<MapTileComponent, WorkingObjectType> newClick){
        CityOverview overview = new CityOverview(newClick.getFirst().getTile().getInnerCity());
        overview.setTranslateY(20);
        overview.toFront();
        gameMapController.addPaneToPanels(overview);
        overview.setOnMouseClicked(event -> {
            gameMapController.removePaneFromPanels(overview);
        });
        for(Pair<Tile, Integer> p : newClick.getFirst().getTile().getOwnerCity().getPurchasableTiles()){
            gameMapController.getTileComponentInMap(p.getFirst().getPCoordinate(), p.getFirst().getQCoordinate()).highlight(2);
            gameMapController.getTileComponentInMap(p.getFirst().getPCoordinate(),p.getFirst().getQCoordinate()).initTileBuy(newClick.getFirst().getTile());
        }
        for(Tile t : newClick.getFirst().getTile().getOwnerCity().getTiles()){
            gameMapController.getTileComponentInMap(t.getPCoordinate(),t.getQCoordinate()).initCitizen();
            gameMapController.getTileComponentInMap(t.getPCoordinate(),t.getQCoordinate()).highlight(1);
        }
    }

    private void showTileInfoPopUp(Pair<MapTileComponent, WorkingObjectType> newClick){
        gameMapController.destroyTemporaryPanels();
        Pane tilePopUp = new TilePopUp(newClick.getFirst().getTile()).getRoot();
        gameMapController.addPanelToBackPane(tilePopUp);
        tilePopUp.setTranslateX(newClick.getFirst().getPane().getTranslateX());
        tilePopUp.setTranslateY(newClick.getFirst().getPane().getTranslateY());
        tilePopUp.setOnMouseClicked(event1 -> {
            if(event1.getButton().equals(MouseButton.PRIMARY))
                gameMapController.destroyTemporaryPanels();
        });
    }

    private void buildFarm() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","farm");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }

    private void buildCamp() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","camp");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildMine() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","mine");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildTradingPost() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","trading_post");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildLumberMill() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","lumber_mill");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildPasture() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","pasture");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildPlantation() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","plantation");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }
    private void buildQuarry() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","quarry");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }

    private void buildManufactory() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","manufactory");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }

    private void unitSetup() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","farm");
        new PopUp().run(PopUpStates.OK,gameMenuController.buildImprovement(data));
        reset();
    }


}

package view.components.GameVisualUI;

import controller.GameMenuController;
import utils.Pair;
import view.components.city.CityOverview;
import view.components.mapComponents.GameMapController;
import view.components.mapComponents.MapTileComponent;

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
    }

    public void addObjectByJob(Pair<MapTileComponent,WorkingObjectType> newClick) {
        if(currentState.equals(UIStates.NONE)){
            selectUnit(newClick);
            savedClicks.add(newClick);
        }
        else if(currentState.equals(UIStates.CITY_INFO)){
            if(newClick.getSecond().equals(WorkingObjectType.CITY)){
                showCityPanel(newClick);
            }
        }
        else if(currentState.equals(UIStates.TILE_INFO)){
            if(newClick.getSecond().equals(WorkingObjectType.TILE)){
                showTileInfoPopUp(newClick);
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
        gameMenuController.unitMove(sendingData);
        reset();
    }

    private void selectUnit(Pair<MapTileComponent,WorkingObjectType> origin){
        HashMap<String,String> selectUnit = new HashMap<>();
        selectUnit.put("position",String.valueOf(origin.getFirst().getTile().getMapNumber()));
        if(origin.getSecond().equals(WorkingObjectType.CIVILIAN_UNIT))
            selectUnit.put("section","civilian");
        else
            selectUnit.put("section","armed");
        gameMenuController.selectUnit(selectUnit);
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
    }

    private void fortifyUnit(){
        gameMenuController.unitFortify(new HashMap<>());
        reset();
    }

    private void garrisonUnit(){
        reset();
    }

    private void fortifyUntilHeelUnit(){
        gameMenuController.unitFortifyUntilHeal(new HashMap<>());
        reset();
    }

    private void rangedAttackUnit(Pair<MapTileComponent, WorkingObjectType> newClick) {
        HashMap<String,String> sendingData = new HashMap<>();
        sendingData.put("position",String.valueOf(newClick.getFirst().getTile().getMapNumber()));
        gameMenuController.unitRangedAttack(sendingData);
        reset();
    }

    private void meleeAttackUnit(Pair<MapTileComponent, WorkingObjectType> newClick) {
        HashMap<String,String> sendingData = new HashMap<>();
        sendingData.put("position",String.valueOf(newClick.getFirst().getTile().getMapNumber()));
        gameMenuController.unitMeleeAttack(sendingData);
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
        gameMenuController.unitPillage(new HashMap<>());
        reset();
    }

    private void foundCity(){
        gameMenuController.unitFoundCity(new HashMap<>());
        reset();
    }

    private void unitCancel(){
        gameMenuController.unitCancelMission(new HashMap<>());
        reset();
    }

    private void wakeUnit(){
        gameMenuController.unitWake(new HashMap<>());
        reset();
    }

    private void deleteUnit(){
        gameMenuController.unitDelete(new HashMap<>());
        reset();
    }

    private void repairWorker(){
        gameMenuController.unitRepair(new HashMap<>());
        reset();
    }

    private void removeWorker(){
        gameMenuController.unitRemove(new HashMap<>());
        reset();
    }

    private void buildRoad(){
        gameMenuController.buildRoad(new HashMap<>());
        reset();
    }

    private void buildRailRoad(){
        gameMenuController.buildRail(new HashMap<>());
        reset();
    }

    private void showCityPanel(Pair<MapTileComponent, WorkingObjectType> newClick){
        CityOverview overview = new CityOverview(newClick.getFirst().getTile().getInnerCity());
        gameMapController.getBackground().getChildren().add(overview);
        overview.setTranslateY(20);
        overview.toFront();

        reset();
    }

    private void showTileInfoPopUp(Pair<MapTileComponent, WorkingObjectType> newClick){
        //TODO show tile info pop up
        reset();
    }


}

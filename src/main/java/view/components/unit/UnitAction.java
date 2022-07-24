package view.components.unit;

import javafx.scene.paint.ImagePattern;
import lombok.Getter;
import utils.GraphicUtils;
import view.components.GameVisualUI.UIStates;
import view.components.mapComponents.GameMapController;

@Getter
public enum UnitAction { //todo check list
    ALERT(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_ALERT);
        }
    },
    BUILD_ROUTE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
        }
    },
    CANCEL(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_CANCEL);
        }
    },
    DELETE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_DELETE);
        }
    },
    FORTIFY(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_FORTIFY);
        }
    },
    FOUND_CITY(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_FOUND_CITY);
        }
    },
    HEAL(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_FORTIFY_UNTIL_HEEL);
        }
    },
    MOVE_TO(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_MOVE);
        }
    },
    RANGE_ATTACK(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_RANGED_ATTACK);
        }
    },
    SLEEP(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_SLEEP);
        }
    },
    WAKE_UP(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_WAKE);
        }
    },
    SETUP(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_SETUP);
        }
    },
    ATTACK(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_MELEE_ATTACK);
        }
    },
    CITY_ATTACK(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.CITY_ATTACK);
        }
    },
    PILLAGE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_PILLAGE);
        }
    },
    BUILD_RAIL_ROAD(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_BUILD_RAILROAD);
        }
    },
    BUILD_ROAD(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.UNIT_BUILD_ROAD);
        }
    },
    REPAIR(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.REPAIR);
        }
    },
    REMOVE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.REMOVE);
        }
    },
    CAMP(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.CAMP);
        }
    },
    FARM(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.FARM);
        }
    },
    MINE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.MINE);
        }
    },
    TRADING_POST(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.TRADING_POST);
        }
    },
    LUMBER_MILL(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.LUMBER_MILL);
        }
    },
    PASTURE(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.PASTURE);
        }
    },
    PLANTATION(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.PLANTATION);
        }
    },
    QUARRY(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.QUARRY);
        }
    },
    MANUFACTORY(){
        @Override
        public void effect(UnitView unitView, GameMapController gameMapController) {
            gameMapController.getUiStatesController().setCurrentState(UIStates.MANUFACTORY);
        }
    };



    private ImagePattern icon;

    UnitAction() {
        this.icon = GraphicUtils.getImage("/units/actions/"+name().toLowerCase()+".png");
    }

    public void effect(UnitView unitView, GameMapController gameMapController){
        System.out.println("I do shit");
    }
}

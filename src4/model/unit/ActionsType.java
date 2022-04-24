package model.unit;
import model.improvement.Improvement;
import model.improvement.ImprovementType;

public enum ActionsType {
    //worker actions
    BUILD_ROAD(3,null),
    BUILD_RAILROAD(3,null),
    REMOVE_ROADS(3,null),
    REMOVE_JUNGLE(6,null),
    REMOVE_FOREST(3,null),
    REMOVE_MARSH(5,null),
    REPAIR(3,null),
    BUILD_CAMP(-1, ImprovementType.CAMP),
    BUILD_FARM(-1,ImprovementType.FARM),
    BUILD_MINE(-1,ImprovementType.MINE),
    BUILD_TRADING_POST(-1,ImprovementType.TRADING_POST),
    BUILD_LUMBER_MILL(-1,ImprovementType.LUMBER_MILL),
    BUILD_PASTURE(-1,ImprovementType.PASTURE),
    BUILD_PLANTATION(-1,ImprovementType.PLANTATION),
    BUILD_QUARRY(-1,ImprovementType.QUARRY),
    MOVE_TO(1,null),
    SETTLE(1,null);



    public final int time;
    public final Object mainKind;
    ActionsType(int time, Object mainKind){
        this.time = time;
        this.mainKind =mainKind;
    }
}

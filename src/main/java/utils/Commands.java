package utils;


public enum Commands {
    //info
    INFO("info", null, null, 1),
    //select
    SELECT_UNIT("select unit", new String[]{"position"}, null,0),
    SELECT_CITY("select city", null, new String[]{"name", "position"},0),
    //unit
    UNIT_MOVE("unit moveto", new String[]{"position"}, null,0),
    UNIT_SLIP("unit sleep", null, null, 0),
    UNIT_ALERT("unit alert", null,null,0),
    UNIT_FORTIFY("unit fortify",null,null,0),
    UNIT_FORTIFY_UNTIL_HEAL("unit fortify heal",null,null,0),
    UNIT_GARRISON("unit garrison",null,null,0),
    UNIT_SETUP("unit setup ranged",null,null,0),
    UNIT_ATTACK("unit attack", new String[]{"position"}, null,0),
    UNIT_FOUND_CITY("unit found city",null,null,0),
    UNIT_CANCEL_MISSION("unit cancel mission", null,null,0),
    UNIT_WAKE("unit wake", null,null,0),
    UNIT_DELETE("unit delete", null, null,0),
    UNIT_BUILD("unit build",null,null,1),
    UNIT_REMOVE("unit remove", null,null,1),
    UNIT_REPAIR("unit repair", null,null,0),
    MAP_SHOW("map show", null,new String[]{"position","cityname"},0),
    MAP_MOVE("map move", new String[]{"count"}, null, 1);

    String offset;
    String[] requiredKeys;
    String[] optionalKeys;
    int singleArgsCount;

    Commands(String offset, String[] requiredKeys, String[] optionalKeys, int singleArgsCount) {
        this.offset = offset;
        this.requiredKeys = requiredKeys;
        this.optionalKeys = optionalKeys;
        this.singleArgsCount = singleArgsCount;
    }
}

package utils;


public enum Commands {
    //GAME
        //info
        INFO("info", null, null, 1,null),
        //select
        SELECT_UNIT("select unit", new String[]{"position"}, null,0,null),
        SELECT_CITY("select city", null, new String[]{"name", "position"},0,null),
        //unit
        UNIT_MOVE("unit moveto", new String[]{"position"}, null,0,null),
        UNIT_SLIP("unit sleep", null, null, 0,null),
        UNIT_ALERT("unit alert", null,null,0,null),
        UNIT_FORTIFY("unit fortify",null,null,0,null),
        UNIT_FORTIFY_UNTIL_HEAL("unit fortify heal",null,null,0,null),
        UNIT_GARRISON("unit garrison",null,null,0,null),
        UNIT_SETUP("unit setup ranged",null,null,0,null),
        UNIT_ATTACK("unit attack", new String[]{"position"}, null,0,null),
        UNIT_FOUND_CITY("unit found city",null,null,0,null),
        UNIT_CANCEL_MISSION("unit cancel mission", null,null,0,null),
        UNIT_WAKE("unit wake", null,null,0,null),
        UNIT_DELETE("unit delete", null, null,0,null),
        UNIT_BUILD("unit build",null,null,1,null),
        UNIT_REMOVE("unit remove", null,null,1,null),
        UNIT_REPAIR("unit repair", null,null,0,null),
        MAP_SHOW("map show", null,new String[]{"position","cityname"},0,null),
        MAP_MOVE("map move", new String[]{"count"}, null, 1,null),
    //LOGIN
        REGISTER("user create", new String[]{"username","password","nickname"}, null,0,null),
        LOGIN("user login", new String[]{"username", "password"}, null,0,null),
    //MAIN
        PLAY_GAME("play game", null,null,0,new String[]{"player"}),
        LOGOUT("user logout", null,null, 0,null),
    //GLOBAL
        MENU_ENTER("menu enter", null, null, 1,null),
        MENU_EXIT("menu exit", null,null,0,null),
        CURRENT_MENU("menu show-current",null,null,0,null),
    //PROFILE
        PROFILE_CHANGE("profile change",null, new String[]{"new","current"},1,null);

    private final String offset;
    private final String[] requiredKeys;
    private final String[] optionalKeys;
    private final int singleArgsCount;
    private final String[] probableAbbreviation;
    Commands(String offset, String[] requiredKeys, String[] optionalKeys, int singleArgsCount,String[] probableAbbreviation) {
        this.offset = offset;
        this.requiredKeys = requiredKeys;
        this.optionalKeys = optionalKeys;
        this.singleArgsCount = singleArgsCount;
        this.probableAbbreviation = probableAbbreviation;
    }

    public String getOffset() {
        return offset;
    }

    public String[] getRequiredKeys() {
        return requiredKeys;
    }

    public String[] getOptionalKeys() {
        return optionalKeys;
    }

    public int getSingleArgsCount() {
        return singleArgsCount;
    }

    public String[] getProbableAbbreviation() {
        return probableAbbreviation;
    }
}

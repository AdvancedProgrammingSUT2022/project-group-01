package utils;


public enum Commands {
    //GAME
        //select
        SELECT_UNIT("select unit", new String[]{"position"}, null,1,null),
        SELECT_CITY("select city", null, new String[]{"name", "position"},0,null),
        //unit
        UNIT_MOVE("unit moveto", new String[]{"position"}, null,0,null),
        UNIT_SLEEP("unit sleep", null, null, 0,null),
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
        UNIT_ACTION_LIST("unit action list", null, null, 0, null),
    //WORKER
        UNIT_BUILD_IMPROVEMENT("improvement build", new String[]{"name"}, null, 0, null),
    //MAP
        MAP_SHOW("map show", null,new String[]{"position","cityname"},0,null),
        MAP_MOVE("map move", new String[]{"count"}, null, 1,null),
        SHOW_NEXT_TILES("show next tiles",null,null,0,null),
    //LOGIN
        REGISTER("user create", new String[]{"username","password","nickname"}, null,0,null),
        LOGIN("user login", new String[]{"username", "password"}, null,0,null),
    //MAIN
        PLAY_GAME("play game", null,null,0,new String[]{"player"}),
        LOGOUT("user logout", null,null, 0,null),
    //CITY
        PURCHASE_PRODUCTION("purchase production", new String[]{"type"},null,0,null),
        SET_PRODUCTION("set production", new String[]{"type"}, null, 0,null),
        LIST_OF_ALL_OF_PRODUCTIONS("list of productions", null,null,1,null),
        LIST_OF_PRODUCTIONS("list of productions", null, null,0,null),
        GET_PURCHASABLE_TILES("get purchasable tiles",null,null,0,null),
        PURCHASE_TILE("purchase tile", new String[]{"index"},null,0,null),
        LIST_OF_POPULATION("list of population",null,null,0,null),
        SET_TILE_FOR_POPULATION("set tile for population", new String[]{"index","position"},null,0,null),
        DELETE_POPULATION_FROM_TILE("delete population from tile", new String[]{"index"},null,0,null),
    //GLOBAL
        MENU_ENTER("menu enter", null, null, 1,null),
        MENU_EXIT("menu exit", null,null,0,null),
        CURRENT_MENU("menu show-current",null,null,0,null),
    //CHEATS
        INCREASE_RESOURCE("increase", new String[]{"amount"}, null, 1, null),
        SPAWN_UNIT("spawn unit", new String[]{"position", "name"}, null,0,null),
        ADD_TECHNOLOGY("add technology", new String[]{"name"}, null, 0, null),
        ADD_BEAKER("add beaker", new String[]{"amount"}, null, 0, null),
        CHEAT_NEXT_TURN("cheat next turn", null, null, 0, null),
        TELEPORT("teleport", new String[]{"position"}, null,0,null),
        MAKE_TILE_VISIBLE("make tile visible",  new String[]{"position"}, null, 0,null),
        ADD_HAPPINESS("add happiness", new String[]{"amount"}, null, 0,null),
        CREATE_FEATURE("create feature", new String[]{"type"}, null, 0,null),
        ADD_SCORE("add score", new String[]{"amount"}, null,0,null),
    //PROFILE
        PROFILE_CHANGE("profile change",null, new String[]{"new","current"},1,null),
    // TECHNOLOGY
        SHOW_RESEARCHABLE_TECHS("get researchable techs", null, null, 0, null),
        GET_CURRENT_RESEARCH("get current research", null, null, 0, null),
        RESEARCH("research", new String[]{"name"}, null, 0, null),
        CHANGE_RESEARCH("change research", new String[]{"name"}, null, 0, null);

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

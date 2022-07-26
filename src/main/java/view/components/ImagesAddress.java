package view.components;

import javafx.scene.image.Image;
import model.technology.TechnologyType;
import view.Main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;

public enum ImagesAddress {
    GAME_BACKGROUND("MainMenuBG", ".JPEG"),
    MAIL("mail",".png"),
    MAILED("mailSent",".png"),
    SEARCH("search",".png"),
    DESERT_FLOODPLAINS("tiles/desert-floodplains",".jpg"),
    DESERT_OASIS("tiles/desert-oasis",".jpg"),
    DESERT("tiles/desert",".jpg"),
    GRASSLAND_FOREST("tiles/grassland-forest",".jpg"),
    GRASSLAND_MARSH("tiles/grassland-marsh",".jpg"),
    GRASSLAND("tiles/grassland",".jpg"),
    HILLS_JUNGLE("tiles/hills-jungle",".png"),
    HILLS_FOREST("tiles/hills-forest",".png"),
    HILLS("tiles/hills",".png"),
    MOUNTAIN("tiles/mountain",".jpg"),
    OCEAN_ICE("tiles/ocean-ice",".jpg"),
    OCEAN("tiles/ocean",".jpg"),
    PLAINS_FOREST("tiles/plains-forest",".jpg"),
    PLAINS_JUNGLE("tiles/plains-jungle",".jpg"),
    PLAINS("tiles/plains",".jpg"),
    SNOW("tiles/snow",".jpg"),
    TUNDRA_FOREST("tiles/tundra-forest",".jpg"),
    TUNDRA("tiles/tundra",".jpg"),
    FOG_OF_WAR("tiles/fog",".jpg"),
    //resources
    BANANAS("resources/bananas",".png"),
    CATTLE("resources/cattle",".png"),
    DEER("resources/deer",".png"),
    SHEEP("resources/sheep",".png"),
    WHEAT("resources/wheat",".png"),
    COAL("resources/coal",".png"),
    HORSES("resources/horse",".png"),
    IRON("resources/iron",".png"),
    COTTON("resources/cotton",".png"),
    DYES("resources/dyes",".png"),
    FURS("resources/fur",".png"),
    GEMS("resources/gem",".png"),
    GOLD("resources/gold",".png"),
    INCENSE("resources/incense",".png"),
    IVORY("resources/ivory",".png"),
    MARBLE("resources/marble",".png"),
    SILK("resources/silk",".png"),
    SILVER("resources/silver",".png"),
    SUGAR("resources/sugar",".png"),
    // game setting
    MAP_NUMBER_MINUS("mainmenu/minus",".png"),
    MAP_NUMBER_PLUS("mainmenu/plus",".png"),
    // avatars
    AVATAR1("preMainMenu/avatar1",".jpeg"),
    AVATAR2("preMainMenu/avatar2",".jpeg"),
    AVATAR3("preMainMenu/avatar3",".png"),
    AVATAR4("preMainMenu/avatar4",".jpeg"),
    LIGHTSABER("mainmenu/lightsaber",".png"),
    TECHNOLOGY_BACKGROUND("technology/background",".JPG"),
    GAME_BOARD_BACKGROUND("gameMap/background",".jpeg"),
    //Improvements
    IMPROVEMENT_CAMP("improvement/camp",".png"),
    IMPROVEMENT_FARM("improvement/farm",".png"),
    IMPROVEMENT_LUMBER_MILL("improvement/lumber_mill",".png"),
    IMPROVEMENT_MINE("improvement/mine",".png"),
    IMPROVEMENT_PASTURE("improvement/pasture",".png"),
    IMPROVEMENT_PLANTATION("improvement/plantation",".png"),
    IMPROVEMENT_QUARRY("improvement/quarry",".png"),
    IMPROVEMENT_TRADING_POST("improvement/trading_post",".png"),
    IMPROVEMENT_MANUFACTORY("improvement/manufactory",".png"),
    CITIZEN("mapData/citizen",".png" ),
    ROAD("mapData/road",".png" ),
    RAILROAD("mapData/railroad",".png" ),
    CITY("mapData/city",".png" ),
    BACK_BUTTON("backButton",".png" ),
    MAP_ICON("mapData/mapIcon",".png" ),
    //Tile Panel Pictures
    TILE_PANEL_FOOD("gamePanels/food",".png" ),
    TILE_PANEL_PRODUCTION("gamePanels/production",".png" ),
    TILE_PANEL_GOLD("gamePanels/gold",".png" ),
    RIVER("tiles/river",".png" ),
    CHEAT_BACKGROUND("gamePanels/cheat",".JPG" ),
    HEX("tiles/hex",".png" ),
    NEXT_TURN("mapData/nextTurn",".png"),
    ROUND_NEXT_TURN("mapData/roundNextTurn",".png"),
    ROUND_NEXT_TURN_SHINE("mapData/roundNextTurnShine",".png"),
    //info
    INFO_BACK("info/infoBack",".png"),
    BLUE_BUTTON("info/blueButton",".png"),
    CITY_IN_CITY_PANEL("info/cityInCityPanel",".png"),
    SHIELD_CITY_INFO("info/shield",".png"),
    CITY_LIST_INFO_ICON("info/cityListInfoIcon",".png"),
    SIDE_LOG("info/sideLog",".png" ),
    CITY_PANEL("info/cityPanel",".png" ),
    INFO_CLOSE("info/close",".png" ),
    GRAY_BUTTON("info/grayButton",".png" ),
    BLUE_HEX("info/blueHex",".png" ),
    DEMOGRAPHIC_INFO_ICON("info/demographicInfoIcon",".png" ),
    BUY_TILE("mapData/buyTile",".png" ),
    FRIENDS_SEARCH_BUTTON("other/magnifier",".png" ),
    FRIEND_REQUEST("other/friendRequest",".png" ),
    CIRCLE_RING("other/ring",".png" ),
    FRIEND_ITEM("other/quest",".png"),
    END_GAME_ITEM("other/endGame/resultItem",".png" ),
    GOLD_SIDE_HEX("other/endGame/goldSideHex",".png" ),
    BLUE_SIDE_HEX("other/endGame/blueSideHex",".png" ),
    END_GAME_BACKGROUND("other/endGame/endGameBackground",".jpg" ),
    SCORE_BOARD_TEXT_IMAGE("other/scoreBoard/scoreBoardText",".png"),
    SCORE_BOARD_ITEM_USER("other/scoreBoard/scoreBoardItem",".png"),
    SCORE_BOARD_LAST_VISIT_BOX("other/scoreBoard/lastVisitBox",".png"),
    TECHNOLOGY_INFO_ICON("info/technologyInfoIcon",".png" ),
    // trade
    ACCEPT_TRADE("other/trade/accept",".png" ),
    DECLINE_TRADE("other/trade/decline",".png" ),
    TRADE_OFFER_BOX("other/trade/offerBox",".png" ),
    LOBBY_NEW_GAME_BUTTON("lobby/newGameButton",".png" ),
    LOBBY_REFRESH_BUTTON("lobby/refresh",".png" ),
    // game stop
    GAME_STOP_ITEM("gameStop/item",".png" ),
    STOP_GAME_ICON("gameStop/icon",".png" ),
    NOTIFICATION_INFO_ICON("info/notification",".png" ),
    MILITARY_OVERVIEW_ICON("info/militaryOverview",".png" ),
    UNIT_PANEL_ICON("info/unitPanel",".png" ),
    DEMOCRACY_PANEL_ICON("info/democracyIcon",".png" ),
    LOBBY_ITEM_BACKGROUND("lobby/itemBackground",".jpg" ),
    PROFILE_PIC_1("profilePics/1",".jpg"),
    PROFILE_PIC_2("profilePics/2",".jpg"),
    PROFILE_PIC_3("profilePics/3",".jpg"),
    PROFILE_PIC_4("profilePics/4",".jpg"),
    START_GAME_LOBBY_ICON("lobby/newGameButton",".png" ),
    INVITATION_ICON("lobby/invitation",".png" ),
    PRIVACY_ICON("lobby/privacy",".png" ),
    LOBBY_BACK("lobby/lobyVisualBackground", ".png");


    private final String partA;
    private final String partB;

    ImagesAddress(String partA, String partB) {
        this.partA = partA;
        this.partB = partB;
    }

    public String getAddress(String i) {
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(Main.class.getResource("/asset/"+ partA + i + partB)).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(address).toString();
    }

    public String getAddress() {
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(Main.class.getResource("/asset/" + partA + partB)).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(address).toString();
    }

    public Image getImage(int i, String... specifiers) {
        StringBuilder output = new StringBuilder(String.valueOf(i));
        for (String string : specifiers) output.append(string);
        return new Image(getAddress(output.toString()));
    }

    public Image getImage() {
        return new Image(getAddress());
    }

    public static Image getTechnologyImage(TechnologyType type){
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(Main.class.getResource("/asset/technology/" + type.name().toLowerCase(Locale.ROOT) + ".png")).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new Image(address.toString());
    }
}

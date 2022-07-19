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
    DESERT_FLOODPLAINS("tiles/desert-floodplains",".png"),
    DESERT_OASIS("tiles/desert-oasis",".png"),
    DESERT("tiles/desert",".png"),
    GRASSLAND_FOREST("tiles/grassland-forest",".png"),
    GRASSLAND_MARSH("tiles/grassland-marsh",".png"),
    GRASSLAND("tiles/grassland",".png"),
    HILLS_JUNGLE("tiles/hills-jungle",".png"),
    HILLS_FOREST("tiles/hills-forest",".png"),
    HILLS("tiles/hills",".png"),
    MOUNTAIN("tiles/mountain",".png"),
    OCEAN_ICE("tiles/ocean-ice",".png"),
    OCEAN("tiles/ocean",".png"),
    PLAINS_FOREST("tiles/plains-forest",".png"),
    PLAINS_JUNGLE("tiles/plains-jungle",".png"),
    PLAINS("tiles/plains",".png"),
    SNOW("tiles/snow",".png"),
    TUNDRA_FOREST("tiles/tundra-forest",".png"),
    TUNDRA("tiles/tundra",".png"),
    FOG_OF_WAR("tiles/fog",".png"),
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
    MAP_ICON("mapData/mapIcon",".png" );

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

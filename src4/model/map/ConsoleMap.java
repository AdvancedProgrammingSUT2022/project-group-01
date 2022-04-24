package model.map;

import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.unit.UnitType;

import java.util.HashMap;
import java.util.Vector;

public class ConsoleMap {
    private Vector<Vector<String>> screenMap;
    private static HashMap<Object, String> charRepresentation;

    public ConsoleMap(int mapSize) {
        initializeScreenMap(mapSize);
        charRepresentation = new HashMap<>();
        initializeCharRepresent();
    }
    private void initializeScreenMap(int mapSize){
        screenMap = new Vector<>();
        for (int i = 0; i < 31; i++) {
            screenMap.add(new Vector<>());
            for (int j = 0; j < 53; j++) {
                screenMap.get(i).add(" ");
            }
        }
    }

    public Vector<Vector<String>> getScreenMap() {
        return screenMap;
    }

    public static String getRepresentation(Object object){
        return charRepresentation.get(object);
    }

    public void setScreenMapIndex(int x, int y, String string){
        screenMap.get(y).set(x, string);
    }



    private void initializeCharRepresent(){
        charRepresentation.put("/",colorCharacter.BLACK.setTextColor("/"));
        charRepresentation.put("\\",colorCharacter.BLACK.setTextColor("\\"));
        charRepresentation.put("_",colorCharacter.WHITE.setTextColor("_"));
        charRepresentation.put("_R",colorCharacter.BLUE.setTextColor("_"));
        charRepresentation.put("/R",colorCharacter.BLUE.setTextColor("/"));
        charRepresentation.put("\\R",colorCharacter.BLUE.setTextColor("\\"));
        // Resources
        charRepresentation.put(ResourceType.BANANAS,colorCharacter.YELLOW.setTextColor("Ba"));
        charRepresentation.put(ResourceType.CATTLE, colorCharacter.WHITE.setTextColor("Ca"));
        charRepresentation.put(ResourceType.DEER, colorCharacter.RED.setTextColor("De"));
        charRepresentation.put(ResourceType.SHEEP, colorCharacter.WHITE.setTextColor("Sh"));
        charRepresentation.put(ResourceType.WHEAT, colorCharacter.RED.setTextColor("Wh"));
        charRepresentation.put(ResourceType.COAL, colorCharacter.BLACK.setTextColor("Cl"));
        charRepresentation.put(ResourceType.HORSES, colorCharacter.RED.setTextColor("Ho"));
        charRepresentation.put(ResourceType.IRON, colorCharacter.WHITE.setTextColor("Ir"));
        charRepresentation.put(ResourceType.COTTON, colorCharacter.YELLOW.setTextColor("Co"));
        charRepresentation.put(ResourceType.DYES, colorCharacter.RED.setTextColor("Dy"));
        charRepresentation.put(ResourceType.FURS, colorCharacter.WHITE.setTextColor("Fu"));
        charRepresentation.put(ResourceType.GEMS, colorCharacter.GREEN.setTextColor("Ge"));
        charRepresentation.put(ResourceType.GOLD, colorCharacter.YELLOW.setTextColor("Go"));
        charRepresentation.put(ResourceType.INCENSE, colorCharacter.WHITE.setTextColor("In"));
        charRepresentation.put(ResourceType.MARBLE, colorCharacter.BLUE.setTextColor("Ma"));
        charRepresentation.put(ResourceType.SILK, colorCharacter.WHITE.setTextColor("Sl"));
        charRepresentation.put(ResourceType.SILVER, colorCharacter.BLACK.setTextColor("Si"));
        charRepresentation.put(ResourceType.SUGAR, colorCharacter.WHITE.setTextColor("Su"));
        //Terrain Features
        charRepresentation.put(TerrainFeature.ICE, colorCharacter.BRIGHT_WHITE.setTextColor("IC"));
        charRepresentation.put(TerrainFeature.FOREST, colorCharacter.BRIGHT_GREEN.setTextColor("FO"));
        charRepresentation.put(TerrainFeature.JUNGLE, colorCharacter.BRIGHT_GREEN.setTextColor("JU"));
        charRepresentation.put(TerrainFeature.MARSH, colorCharacter.BRIGHT_MARSH_COLOR.setTextColor("MA"));
        charRepresentation.put(TerrainFeature.OASIS, colorCharacter.BRIGHT_BLUE.setTextColor("OA"));
        //Terrains
        charRepresentation.put(Terrain.DESERT, colorCharacter.YELLOW_BACKGROUND.color);
        charRepresentation.put(Terrain.GRASSLAND, colorCharacter.GREEN_BACKGROUND.color);
        charRepresentation.put(Terrain.HILLS, colorCharacter.BROWN_BACKGROUND.color);
        charRepresentation.put(Terrain.MOUNTAIN, colorCharacter.GRAY_BACKGROUND.color);
        charRepresentation.put(Terrain.OCEAN, colorCharacter.BLUE_BACKGROUND.color);
        charRepresentation.put(Terrain.PLAINS, colorCharacter.PLAIN_GREEN_BACKGROUND.color);
        charRepresentation.put(Terrain.SNOW, colorCharacter.BRIGHT_WHITE_BACKGROUND.color);
        charRepresentation.put(Terrain.TUNDRA, colorCharacter.SHADED_BROWN_BACKGROUND.color);
        //Units
        charRepresentation.put(UnitType.ANTI_TANK_GUN, "ag");
        charRepresentation.put(UnitType.ARCHER, "ar");
        charRepresentation.put(UnitType.ARTILLERY, "at");
        charRepresentation.put(UnitType.CANON, "cn");
        charRepresentation.put(UnitType.CATAPULT, "ct");
        charRepresentation.put(UnitType.CAVALRY, "ca");
        charRepresentation.put(UnitType.CHARIOT_ARCHER, "cA");
        charRepresentation.put(UnitType.CROSSBOW_MAN, "cM");
        charRepresentation.put(UnitType.HORSEMAN, "ho");
        charRepresentation.put(UnitType.INFANTRY, "in");
        charRepresentation.put(UnitType.KNIGHT, "kn");
        charRepresentation.put(UnitType.LANCER, "la");
        charRepresentation.put(UnitType.LONG_SWORDSMAN, "lS");
        charRepresentation.put(UnitType.MUSKET_MAN, "mm");
        charRepresentation.put(UnitType.PANZER, "pa");
        charRepresentation.put(UnitType.PIKE_MAN, "pM");
        charRepresentation.put(UnitType.RIFLE_MAN, "rM");
        charRepresentation.put(UnitType.SCOUT, "sc");
        charRepresentation.put(UnitType.SETTLER, "se");
        charRepresentation.put(UnitType.SPEARMAN, "sp");
        charRepresentation.put(UnitType.SWORDSMAN, "sw");
        charRepresentation.put(UnitType.TANK, "ta");
        charRepresentation.put(UnitType.TREBUCHET, "tr");
        charRepresentation.put(UnitType.WARRIOR, "wa");
        charRepresentation.put(UnitType.WORKER, "wo");
        // Improvements
        charRepresentation.put(ImprovementType.MANUFACTORY, colorCharacter.WHITE.setTextColor("MA"));
        charRepresentation.put(ImprovementType.CAMP, colorCharacter.WHITE.setTextColor("CA"));
        charRepresentation.put(ImprovementType.TRADING_POST, colorCharacter.WHITE.setTextColor("TP"));
        charRepresentation.put(ImprovementType.QUARRY, colorCharacter.WHITE.setTextColor("QU"));
        charRepresentation.put(ImprovementType.PLANTATION, colorCharacter.WHITE.setTextColor("PL"));
        charRepresentation.put(ImprovementType.LUMBER_MILL, colorCharacter.WHITE.setTextColor("LM"));
        charRepresentation.put(ImprovementType.FARM, colorCharacter.WHITE.setTextColor("FA"));
        charRepresentation.put(ImprovementType.PASTURE, colorCharacter.WHITE.setTextColor("PA"));
        charRepresentation.put(ImprovementType.MINE, colorCharacter.WHITE.setTextColor("MI"));

    }




    public enum colorCharacter{
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m"),
        BLACK_BACKGROUND("\u001B[40m"),
        RED_BACKGROUND("\u001B[41m"),
        GREEN_BACKGROUND("\u001B[42m"),
        YELLOW_BACKGROUND("\u001B[43m"),
        BLUE_BACKGROUND("\u001B[44m"),
        WHITE_BACKGROUND("\u001B[47m"),
        TUNDRA_BACKGROUND("\u001b[48;5;166m"),
        BROWN_BACKGROUND("\u001b[48;5;136m"),
        BRIGHT_WHITE_BACKGROUND("\u001b[47;1m"),
        PLAIN_GREEN_BACKGROUND("\u001b[48;5;107m"),
        GRAY_BACKGROUND("\u001b[48;5;244m"),
        FOG_OF_WAR("\u001b[48;5;117m"),
        PURPLE_HIGH_INTENSITY("\033[0;95m"),
        WHITE_BOLD("\u001b[1m\u001b[38;5;255m"),
        BRIGHT_GREEN("\u001b[38;5;82m"),
        BRIGHT_WHITE("\u001b[38;5;255m"),
        BRIGHT_BLUE("\u001b[38;5;122m"),
        BRIGHT_MARSH_COLOR("\\u001b[38;5;222m"),
        SHADED_BROWN_BACKGROUND("\u001b[48;5;95m");


        public final String color;
        colorCharacter(String color) {
            this.color =color;
        }
        public String setTextColor(String string){
            return  color + string + RESET.color;
        }

    }

}

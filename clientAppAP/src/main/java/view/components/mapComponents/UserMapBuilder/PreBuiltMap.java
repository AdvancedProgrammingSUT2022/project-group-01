package view.components.mapComponents.UserMapBuilder;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

public class PreBuiltMap {
    private static Vector<PreBuiltMap> maps = new Vector<>();
    private int mapSize;
    private TileReservedData[][] tiles;

    public PreBuiltMap(int mapsize) {
        this.mapSize = mapsize;
        tiles = new TileReservedData[mapsize][mapsize];
    }

    public static Vector<PreBuiltMap> getMaps() {
        return maps;
    }

    public int getMapSize() {
        return mapSize;
    }

    public TileReservedData[][] getTiles() {
        return tiles;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public void setTiles(TileReservedData[][] tiles) {
        this.tiles = tiles;
    }

    public void setTile(int x, int y, TileReservedData tile) {
        tiles[x][y] = tile;
    }

    public void toJson() {
        if(maps == null) maps = new Vector<>();
        maps.add(this);
        try {
            Gson gson = new Gson();
            FileWriter fileWriter = new FileWriter("./preBuiltMaps/" + "map.json");
            gson.toJson(maps, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fromJson() {
        File file = new File("./preBuiltMaps/map.json");
        if(!file.exists())
            return;
        Gson gson = new Gson();
        try (Reader reader = new FileReader("./preBuiltMaps/map.json")) {
            // Convert JSON File to Java Object
            Type listType = new TypeToken<Vector<PreBuiltMap>>(){}.getType();
            maps = gson.fromJson(reader, listType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

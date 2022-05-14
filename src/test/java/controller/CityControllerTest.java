package controller;

import controller.civilization.city.CityController;
import model.civilization.Civilization;
import model.civilization.Currency;
import model.civilization.city.City;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityControllerTest {

    CityController cityController;
    @BeforeEach
    public void init(){
        cityController = new CityController(null);
    }

    @Test
    public void getNextTilesTest(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        when(tile.getMapNumber()).thenReturn(2);
        Vector<Tile> tiles = new Vector<Tile>();
        tiles.add(tile);
        when(city.getNextTiles()).thenReturn(tiles);
        String answer = "1- 2";
        Assertions.assertEquals(answer, cityController.getNextTiles(city));
    }

    @Test
    public void getPurchasableTilesTest(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        when(tile.getMapNumber()).thenReturn(2);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        String answer = "1- 2 : 50$\n";
        Assertions.assertEquals(answer, cityController.getPurchasableTile(city));
    }

    @Test
    public void purchaseTileTestInvalidIndex(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        String answer = "invalid index!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 2));
    }

    @Test
    public void purchaseTileTestNotEnoughGold(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Civilization civilization = mock(Civilization.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        when(city.getCivilization()).thenReturn(civilization);
        when(civilization.getCurrency()).thenReturn(new Currency(49,0,0));

        String answer = "you don't have enough gold!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 1));
    }

    @Test
    public void purchaseTileTestEnoughGold(){
        City city = mock(City.class);
        Tile tile = mock(Tile.class);
        Civilization civilization = mock(Civilization.class);
        Vector<Pair<Tile,Integer>> tiles = new Vector<>();
        tiles.add(new Pair<>(tile, 50));
        when(city.getPurchasableTiles()).thenReturn(tiles);
        when(city.getCivilization()).thenReturn(civilization);
        when(civilization.getCurrency()).thenReturn(new Currency(100,0,0));
        //when(city.addNewTiles()).thenReturn(null);
        String answer = "tile purchased!";
        Assertions.assertEquals(answer, cityController.purchaseTile(city, 1));
    }

}

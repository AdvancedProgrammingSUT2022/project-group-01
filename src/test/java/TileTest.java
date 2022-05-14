

import controller.*;
import model.*;

import java.util.*;

import model.Map;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.improvement.ImprovementType;

import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Boarder;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TileTest {
    @Test
    public void testFeature(){
        Tile tile = new Tile(Terrain.PLAINS, TerrainFeature.FOREST,null, ResourceType.IRON, 10,10,10);
        Assertions.assertEquals(tile.getFeature(), TerrainFeature.FOREST);
    }
    @Test
    public void repairTest(){
        Tile tile = new Tile(Terrain.PLAINS,TerrainFeature.FOREST,null,ResourceType.IRON,10,10,10);
        tile.buildRoad();
        Assertions.assertTrue(tile.getHasRoute());
    }
    @Test
    public void boarderTest(){
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.JUNGLE, null, ResourceType.GEMS,15,50,50);
        Boarder boarder = new Boarder(tile,null,true,18);
        boarder.setRiver();
        Assertions.assertFalse(tile.hasRiverNearby());
    }
    @Test
    public void mapShowTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        MapController mc = new MapController(game);
        String s = mc.getConsoleMap(game.getCurrentPlayer().getMapCenterTile()).substring(0,2);
        Assertions.assertTrue(s.startsWith(" "));
        Assertions.assertEquals(17, game.getMap().getMapSize());
    }


   @Test
    public void mapGenerator1(){
        TileController.initializeEnums();
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        Player p1 = new Player(user1);
        Player p2 = new Player(user2);
        Vector<Player> pvec = new Vector<>(List.of(p1,p2));
        Game game = new Game(pvec, 21);
        MapGenerationController mgc = new MapGenerationController(game);


        mgc.generateMap(21);
        Assertions.assertNotNull(game.getMap().getTile(14,14).getTerrain());
    }


    @Mock
    Game game;
    @Test
    public void setPositionTest(){
        TileController.initializeEnums();
        MapController mc = new MapController(game);
        Map map = new Map(4);
        when(game.getMap()).thenReturn(map);
        Assertions.assertEquals(mc.setPosition(-2), "invalid position");
        Assertions.assertEquals(mc.setPosition(1000),"invalid position");
    }


    @Test
    public void moveCenterTileTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        mc.moveCenterTile(1,"right");

        Assertions.assertSame(spyGame.getCurrentPlayer().getMapCenterTile(),tile.getBoarder(1).getOtherTile(tile));
        Assertions.assertEquals(mc.moveMap("left",3),"changed map position");
    }

    @Mock
    Game game2;
    MapController mc2;
    @Test
    public void showTileInfoTest(){
        Tile tile = new Tile(Terrain.DESERT,TerrainFeature.FOREST,null,ResourceType.GEMS,10,10,4);
        GameController gc = new GameController(game2 , mc2);
        GameController gcSpy = spy(gc);
        Tile tileSpy = spy(tile);
        Mockito.doReturn(Terrain.OCEAN).when(tileSpy).getTerrain();
        Assertions.assertTrue(gc.showTileInfo(tileSpy).contains("Tile Number :"));

    }

@Test
    public void mapGeneration2Test(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);

    }

    @Test
    public void unitInMapControllerTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);

        Assertions.assertSame(spyGame.getCurrentPlayer().getMapCenterTile(),tile.getBoarder(1).getOtherTile(tile));
        Assertions.assertEquals(mc.moveMap("left",3),"changed map position");
    }

    @Test
    public void improvementBuilding(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        MapController mc = new MapController(game);
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        Assertions.assertEquals(tile.getImprovementInventoryState(),ProgressState.COMPLETE);
    }

   @Test
    public void gameTestRun1(){
        Database database = new Database();
        database.load();
        MainMenuController mainMenuController = new MainMenuController(database);
        HashMap<String, String> h = new HashMap<>();
        h.put("player1","username");
        h.put("player2","username");
       Assertions.assertNotEquals(mainMenuController.playGame(h),"Game Started!");
   }

   @Test
    public void buildingRoadAbility(){
       User user1 = new User("a","b","c");
       User user2 = new User("aa","bb","cc");
       TileController.initializeEnums();
       Vector<User> vec = new Vector<>(List.of(user1,user2));
       GameInitializer gi = new GameInitializer();
       Game game = gi.startGame(vec,17);
       Game spyGame = spy(game);
       MapController mc = new MapController(spyGame);

       Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
       tile.setCivilization(game.getCurrentPlayer().getCivilization());
       Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
       Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
       tile.setArmedUnit(armed);
       tile.setCivilianUnit(civilian);
       tile.setAvailableResource(ResourceType.IRON);
       tile.buildRoad();
       tile.buildImprovement(ImprovementType.LUMBER_MILL);
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
       Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
       mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
       mc.moveCenterTile(1,"right");
       mc.getConsoleMap(tile);
       Assertions.assertFalse(TileController.isAbleToBuildRailRoad(civilian));
       Assertions.assertFalse(TileController.isAbleToBuildRoad(civilian));
   }

    @Test
    public void improvementFunctions(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();

        for(ImprovementType i : ImprovementType.values()) {
            i.isEligibleToBuild(game.getCurrentPlayer().getCivilization(),tile);
            i.getProductionTime(tile);
        }
        for(TechnologyType technologyType : TechnologyType.values())
            game.getCurrentPlayer().getCivilization().getResearchTree().research(technologyType);
        for(Terrain terrain : Terrain.values()){
            for(TerrainFeature terrainFeature : TerrainFeature.values()){
                for(ResourceType resourceType : ResourceType.values()){
                    Tile newTile = new Tile(terrain,terrainFeature,game.getCurrentPlayer().getCivilization(),resourceType,1,1,1);
                    for(ImprovementType i : ImprovementType.values()) {
                        i.isEligibleToBuild(game.getCurrentPlayer().getCivilization(),newTile);
                        i.getProductionTime(newTile);
                        i.improvementSpecialAction(tile);
                    }
                }
            }
        }
    }

    @Test
    public void informationTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        Information information = new Information(game);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        Assertions.assertNotEquals(city, information.getCityPanelByName(city.getName()));
        System.out.println(information.demographicScreen());
        information.cityPanel(city);
    }

    @Test
    public void cityTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        city.updateBeaker();
        Unit unit = new Armed(UnitType.WARRIOR,city.getCenter(),game.getCurrentPlayer().getCivilization());
        city.setGarrisonedUnit(unit);
        city.getGarrisonedUnit();
        city.updateDefencePower(2);
        city.setAttackPower(4);
        Assertions.assertEquals(city.getAttackPower(),4);
        city.setHealth(10);
        Assertions.assertEquals(city.getHealth(),10);
        city.getPurchasableTiles();
        city.addNewTiles(new Vector<Tile>(List.of(tile)));
        city.increaseDefencePower(23);
        city.payCurrency(0,0,0);
        Assertions.assertEquals(city.getScreen().get("name"), game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1]);
        city.setName("Hi");
        Assertions.assertEquals("Hi", city.getName());
        Assertions.assertFalse(city.getTiles().isEmpty());
        Assertions.assertEquals(city.getCivilization(),game.getCurrentPlayer().getCivilization());
        Assertions.assertNotNull(city.getCurrency());
        Assertions.assertNotNull(city.getProductionInventory());
        city.setAttackPower(100);
        city.getAttackPower();
        city.getHealth();
    }

    @Test
    public void improvementTest(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        Game game = gi.startGame(vec,17);
        Game spyGame = spy(game);
        MapController mc = new MapController(spyGame);

        Tile tile = spyGame.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.IN_PROGRESS);
        tile.stopImprovementProgress();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        tile.getCurrency();
        TurnBasedLogic.callNextTurns(game.getCurrentPlayer().getCivilization());
        tile.pillageImprovement();
        tile.repairImprovement();
        tile.getCurrency();
        tile.removeImprovement();


    }

    @Test
    public void boarderRemainedTest(){
        Tile firstTile = new Tile(Terrain.MOUNTAIN,TerrainFeature.FLOOD_PLAINS,null,ResourceType.MARBLE,10,10,10);
        Tile secondTile = new Tile(Terrain.MOUNTAIN,TerrainFeature.FLOOD_PLAINS,null,ResourceType.MARBLE,11,9,12);
        Boarder boarder = new Boarder(firstTile,secondTile,true,21);
        Assertions.assertTrue(boarder.isNearTile(firstTile));
        Assertions.assertNotNull(boarder.getWeight());
        boarder.destroyRiver();
        Assertions.assertNotNull(boarder.getAdjacentTiles());
    }

}

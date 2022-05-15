package controller;

import controller.civilization.city.CityController;
import controller.unit.UnitController;
import controller.unit.WorkerController;
import model.Game;
import model.ProgressState;
import model.User;
import model.civilization.city.City;
import model.improvement.ImprovementType;
import model.resource.ResourceType;
import model.technology.TechnologyType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitType;
import model.unit.armed.Armed;
import model.unit.civilian.Civilian;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
@ExtendWith(MockitoExtension.class)
public class GameMenuControllerTestPRCR {
    private Game game;
    private  MapController mc;
    private  GameMenuController gameMenuController;
    @BeforeEach
    public void beforechert(){
        User user1 = new User("a","b","c");
        User user2 = new User("aa","bb","cc");
        TileController.initializeEnums();
        Vector<User> vec = new Vector<>(List.of(user1,user2));
        GameInitializer gi = new GameInitializer();
        game = gi.startGame(vec,17);
        mc = new MapController(game);
        WorkerController workerController = new WorkerController(game);
        gameMenuController = new GameMenuController(game,new GameController(game,mc),new CityController(game),new UnitController(game), workerController);
        game.getMap().getMap();
        game.setMap(game.getMap());
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        tile.setCivilization(game.getCurrentPlayer().getCivilization());
        Armed armed = new Armed(UnitType.WARRIOR,tile,game.getCurrentPlayer().getCivilization());
        Civilian civilian = new Civilian(UnitType.WORKER,tile,game.getCurrentPlayer().getCivilization());
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(civilian);
        tile.setAvailableResource(ResourceType.IRON);
        tile.buildRoad();
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        tile.buildImprovement(ImprovementType.LUMBER_MILL);
        Assertions.assertEquals(tile.getImprovementInventoryState(), ProgressState.COMPLETE);
        mc.updateSavedMap(game.getCurrentPlayer(),new Vector<>(List.of(tile)),game.getMap());
        mc.moveCenterTile(1,"right");
        mc.getConsoleMap(tile);
        TileController.initializeEnums();
        City city = new City(game.getCurrentPlayer().getCivilization().getCivilization().getCityNames()[1],game.getCurrentPlayer().getCivilization(),tile);
        game.getCurrentPlayer().getCivilization().addNewCity(city);
        tile.setOwnerCity(city);
        city.updateBeaker();
        Unit unit = new Armed(UnitType.WARRIOR,city.getCenter(),game.getCurrentPlayer().getCivilization());
        city.setGarrisonedUnit(unit);
        city.getGarrisonedUnit();
        city.updateDefencePower(2);
        city.setAttackPower(4);
        game.getCurrentPlayer().getMapCenterTile().setOwnerCity(city);
    }

    @Test
    void mapMove(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("section", "left");
        hashMap.put("count","2");
        gameMenuController.mapMove(hashMap);
    }

    @Test
    void menuEnter(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("section", "info");
        gameMenuController.menuEnter(hashMap);
        hashMap.put("section", "nonsense");
        gameMenuController.menuEnter(hashMap);
    }
    @Mock
    Armed armed;
    @Mock
    Worker worker;
    @Test
    void spawnUnitTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("position","1");
        gameMenuController.spawnUnit(hashMap);
        hashMap.replace("position","a");
        gameMenuController.spawnUnit(hashMap);
        Tile tile = game.getCurrentPlayer().getMapCenterTile();
        hashMap.replace("position",String.valueOf(tile.getMapNumber()));
        hashMap.put("name","fandogh");
        gameMenuController.spawnUnit(hashMap);
        hashMap.replace("name","warrior");
        tile.setArmedUnit(armed);
        tile.setCivilianUnit(worker);
        gameMenuController.spawnUnit(hashMap);
        tile.removeUnit(armed);
        hashMap.replace("name","worker");
        gameMenuController.spawnUnit(hashMap);
        tile.removeUnit(armed);
        tile.removeUnit(worker);
        gameMenuController.spawnUnit(hashMap);
    }

    @Test
    void listOfProductionsTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.listOfProductions(null);
    }

    @Test
    void listOfAllProductionsTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("section","-b");
        gameMenuController.listOfAllProductions(hashMap);
        hashMap.replace("section","-a");
        game.setSelectedObject(null);
        gameMenuController.listOfAllProductions(hashMap);
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.listOfAllProductions(hashMap);
    }

    @Test
    void purchaseProductionTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type","worker");
        gameMenuController.purchaseProduction(hashMap);
    }

    @Test
    void setProduction(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type","worker");
        gameMenuController.setProduction(hashMap);
    }

    @Test
    void showNextTilesTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.showNextTiles(null);
        game.setSelectedObject(null);
        gameMenuController.showNextTiles(null);
    }

    @Test
    void getPurchasableTiles(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.getPurchasableTiles(null);
    }
    @Test
    void purchaseTileTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("index","1");
        gameMenuController.purchaseTile(hashMap);
    }
    @Test
    void getResearchableTechnologies(){
        gameMenuController.getResearchableTechnologies(null);
    }
    @Test
    void listOfPopulationTest(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        gameMenuController.listOfPopulation(null);
    }

    @Test
    void addTechnology(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","Agri");
        gameMenuController.addTechnology(hashMap);
        hashMap.replace("name","wheel");
        gameMenuController.addTechnology(hashMap);
        gameMenuController.addTechnology(hashMap);
    }

    @Test
    void researchTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","potery");
        gameMenuController.research(hashMap);
        hashMap.replace("name","pottery");
        gameMenuController.research(hashMap);
        hashMap.replace("name","mining");
        gameMenuController.research(hashMap);
        hashMap.replace("name","agriculture");
        gameMenuController.research(hashMap);

    }

    @Test
    void changeResearch(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","agriculture");
        gameMenuController.research(hashMap);
        hashMap.replace("name","pottery");
        gameMenuController.changeResearch(hashMap);
        hashMap.replace("name","agriculture");
        gameMenuController.changeResearch(hashMap);
    }

    @Test
    void setTileForPopulationTest(){
        game.setSelectedObject(null);
        gameMenuController.setTileForPopulation(null);
        HashMap<String,String> hashMap = new HashMap<>();
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        hashMap.put("index","1");
        hashMap.put("position","20");
        gameMenuController.setTileForPopulation(hashMap);
        hashMap.put("position",String.valueOf(game.getCurrentPlayer().getCivilization().getCities().get(0).getTiles().get(1).getMapNumber()));
        gameMenuController.setTileForPopulation(hashMap);
    }
    @Test
    void deletePopulation(){
        game.setSelectedObject(game.getCurrentPlayer().getCivilization().getCities().get(0));
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("index","1");
        gameMenuController.deletePopulation(hashMap);
    }

    @Test
    void getCurrentResearchTest(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","agriculture");
        gameMenuController.research(hashMap);
        gameMenuController.getCurrentResearch(null);
    }
    @Test
    void addTechnologyTwo(){
        game.getCurrentPlayer().getCivilization().getResearchTree().addScience(300);
        game.getCurrentPlayer().getCivilization().getResearchTree().research(TechnologyType.AGRICULTURE);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","agriculture");
        gameMenuController.addTechnology(hashMap);
    }

}

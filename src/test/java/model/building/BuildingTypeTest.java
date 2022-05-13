package model.building;

import model.Player;
import model.User;
import model.civilization.Civilization;
import model.civilization.Civilizations;
import model.civilization.city.City;
import model.resource.ResourceType;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildingTypeTest {

    @Test
    void testEffect() {
        // Setup
        final Player player = new Player(new User("username", "password", "nickname"));
        final City city = new City("name", new Civilization(Civilizations.MONGOLIAN, null),
                new Tile(Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0));

        // Run the test
        BuildingType.BARRACKS.effect(player, city);
        BuildingType.GRANARY.effect(player, city);
        BuildingType.LIBRARY.effect(player, city);
        BuildingType.MONUMENT.effect(player, city);
        BuildingType.WALLS.effect(player, city);
        BuildingType.WATER_MILL.effect(player, city);
        BuildingType.ARMORY.effect(player, city);
        BuildingType.BURIAL_TOMB.effect(player, city);
        BuildingType.CIRCUS.effect(player, city);
        BuildingType.COLOSSEUM.effect(player, city);
        BuildingType.COURTHOUSE.effect(player, city);
        BuildingType.STABLE.effect(player, city);
        BuildingType.TEMPLE.effect(player, city);
        BuildingType.CASTLE.effect(player, city);
        BuildingType.FORGE.effect(player, city);
        BuildingType.GARDEN.effect(player, city);
        BuildingType.MARKET.effect(player, city);
        BuildingType.MINT.effect(player, city);
        BuildingType.MONASTERY.effect(player, city);
        BuildingType.UNIVERSITY.effect(player, city);
        BuildingType.WORKSHOP.effect(player, city);
        BuildingType.BANK.effect(player, city);
        BuildingType.MILITARY_ACADEMY.effect(player, city);
        BuildingType.MUSEUM.effect(player, city);
        BuildingType.OPERA_HOUSE.effect(player, city);
        BuildingType.PUBLIC_SCHOOL.effect(player, city);
        BuildingType.SATRAPS_COURT.effect(player, city);
        BuildingType.THEATRE.effect(player, city);
        BuildingType.WINDMILL.effect(player, city);
        BuildingType.ARSENAL.effect(player, city);
        BuildingType.BROADCAST_TOWER.effect(player, city);
        BuildingType.FACTORY.effect(player, city);
        BuildingType.HOSPITAL.effect(player, city);
        BuildingType.MILITARY_BASE.effect(player, city);
        BuildingType.STOCK_EXCHANGE.effect(player, city);

        // Verify the results
    }

    @Test
    void testIsEligibleToBuild() {
        assertTrue(BuildingType.BARRACKS.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.GRANARY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.LIBRARY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MONUMENT.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WALLS.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WATER_MILL.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.ARMORY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.BURIAL_TOMB.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.CIRCUS.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.COLOSSEUM.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.COURTHOUSE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.STABLE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.TEMPLE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.CASTLE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.FORGE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.GARDEN.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MARKET.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MINT.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MONASTERY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.UNIVERSITY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WORKSHOP.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.BANK.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MILITARY_ACADEMY.isEligibleToBuild(
                new Player(new User("username", "password", "nickname")), new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MUSEUM.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.OPERA_HOUSE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(
                BuildingType.PUBLIC_SCHOOL.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                        new City("name", new Civilization(
                                Civilizations.MONGOLIAN, null), new Tile(
                                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(
                BuildingType.SATRAPS_COURT.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                        new City("name", new Civilization(
                                Civilizations.MONGOLIAN, null), new Tile(
                                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.THEATRE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WINDMILL.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.ARSENAL.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(
                BuildingType.BROADCAST_TOWER.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                        new City("name", new Civilization(
                                Civilizations.MONGOLIAN, null), new Tile(
                                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.FACTORY.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.HOSPITAL.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(
                BuildingType.MILITARY_BASE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                        new City("name", new Civilization(
                                Civilizations.MONGOLIAN, null), new Tile(
                                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(
                BuildingType.STOCK_EXCHANGE.isEligibleToBuild(new Player(new User("username", "password", "nickname")),
                        new City("name", new Civilization(
                                Civilizations.MONGOLIAN, null), new Tile(
                                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
    }

    @Test
    void testGetProductionTime() {
        assertEquals(0, BuildingType.BARRACKS.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.GRANARY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.LIBRARY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MONUMENT.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WALLS.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WATER_MILL.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.ARMORY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BURIAL_TOMB.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.CIRCUS.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.COLOSSEUM.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.COURTHOUSE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.STABLE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.TEMPLE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.CASTLE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.FORGE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.GARDEN.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MARKET.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MINT.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MONASTERY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.UNIVERSITY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WORKSHOP.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BANK.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MILITARY_ACADEMY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MUSEUM.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.OPERA_HOUSE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.PUBLIC_SCHOOL.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.SATRAPS_COURT.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.THEATRE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WINDMILL.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.ARSENAL.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BROADCAST_TOWER.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.FACTORY.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.HOSPITAL.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MILITARY_BASE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.STOCK_EXCHANGE.getProductionTime(new Civilization(
                        Civilizations.MONGOLIAN, new City("name", null, new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, null, ResourceType.BANANAS, 0, 0, 0))),
                new City("name", new Civilization(
                        Civilizations.MONGOLIAN, null), new Tile(
                        Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                        Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
    }

    @Test
    void testProduce() {
        // Setup
        final City city = new City("name", new Civilization(Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(Civilizations.MONGOLIAN, null),
                ResourceType.BANANAS, 0, 0, 0));

        // Run the test
        BuildingType.BARRACKS.produce(city);
        BuildingType.GRANARY.produce(city);
        BuildingType.LIBRARY.produce(city);
        BuildingType.MONUMENT.produce(city);
        BuildingType.WALLS.produce(city);
        BuildingType.WATER_MILL.produce(city);
        BuildingType.ARMORY.produce(city);
        BuildingType.BURIAL_TOMB.produce(city);
        BuildingType.CIRCUS.produce(city);
        BuildingType.COLOSSEUM.produce(city);
        BuildingType.COURTHOUSE.produce(city);
        BuildingType.STABLE.produce(city);
        BuildingType.TEMPLE.produce(city);
        BuildingType.CASTLE.produce(city);
        BuildingType.FORGE.produce(city);
        BuildingType.GARDEN.produce(city);
        BuildingType.MARKET.produce(city);
        BuildingType.MINT.produce(city);
        BuildingType.MONASTERY.produce(city);
        BuildingType.UNIVERSITY.produce(city);
        BuildingType.WORKSHOP.produce(city);
        BuildingType.BANK.produce(city);
        BuildingType.MILITARY_ACADEMY.produce(city);
        BuildingType.MUSEUM.produce(city);
        BuildingType.OPERA_HOUSE.produce(city);
        BuildingType.PUBLIC_SCHOOL.produce(city);
        BuildingType.SATRAPS_COURT.produce(city);
        BuildingType.THEATRE.produce(city);
        BuildingType.WINDMILL.produce(city);
        BuildingType.ARSENAL.produce(city);
        BuildingType.BROADCAST_TOWER.produce(city);
        BuildingType.FACTORY.produce(city);
        BuildingType.HOSPITAL.produce(city);
        BuildingType.MILITARY_BASE.produce(city);
        BuildingType.STOCK_EXCHANGE.produce(city);

        // Verify the results
    }

    @Test
    void testIsProducible() {
        assertTrue(BuildingType.BARRACKS.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.GRANARY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.LIBRARY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MONUMENT.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WALLS.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WATER_MILL.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.ARMORY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.BURIAL_TOMB.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.CIRCUS.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.COLOSSEUM.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.COURTHOUSE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.STABLE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.TEMPLE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.CASTLE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.FORGE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.GARDEN.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MARKET.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MINT.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MONASTERY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.UNIVERSITY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WORKSHOP.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.BANK.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MILITARY_ACADEMY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MUSEUM.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.OPERA_HOUSE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.PUBLIC_SCHOOL.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.SATRAPS_COURT.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.THEATRE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.WINDMILL.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.ARSENAL.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.BROADCAST_TOWER.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.FACTORY.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.HOSPITAL.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.MILITARY_BASE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertTrue(BuildingType.STOCK_EXCHANGE.isProducible(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
    }

    @Test
    void testGetCost() {
        assertEquals(0, BuildingType.BARRACKS.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.GRANARY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.LIBRARY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MONUMENT.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WALLS.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WATER_MILL.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.ARMORY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BURIAL_TOMB.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.CIRCUS.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.COLOSSEUM.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.COURTHOUSE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.STABLE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.TEMPLE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.CASTLE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.FORGE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.GARDEN.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MARKET.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MINT.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MONASTERY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.UNIVERSITY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WORKSHOP.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BANK.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MILITARY_ACADEMY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MUSEUM.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.OPERA_HOUSE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.PUBLIC_SCHOOL.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.SATRAPS_COURT.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.THEATRE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.WINDMILL.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.ARSENAL.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.BROADCAST_TOWER.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.FACTORY.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.HOSPITAL.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.MILITARY_BASE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
        assertEquals(0, BuildingType.STOCK_EXCHANGE.getCost(new City("name", new Civilization(
                Civilizations.MONGOLIAN, null), new Tile(
                Terrain.DESERT, TerrainFeature.FLOOD_PLAINS, new Civilization(
                Civilizations.MONGOLIAN, null), ResourceType.BANANAS, 0, 0, 0))));
    }

    @Test
    void testToString() {
        assertEquals("name", BuildingType.BARRACKS.toString());
        assertEquals("name", BuildingType.GRANARY.toString());
        assertEquals("name", BuildingType.LIBRARY.toString());
        assertEquals("name", BuildingType.MONUMENT.toString());
        assertEquals("name", BuildingType.WALLS.toString());
        assertEquals("name", BuildingType.WATER_MILL.toString());
        assertEquals("name", BuildingType.ARMORY.toString());
        assertEquals("name", BuildingType.BURIAL_TOMB.toString());
        assertEquals("name", BuildingType.CIRCUS.toString());
        assertEquals("name", BuildingType.COLOSSEUM.toString());
        assertEquals("name", BuildingType.COURTHOUSE.toString());
        assertEquals("name", BuildingType.STABLE.toString());
        assertEquals("name", BuildingType.TEMPLE.toString());
        assertEquals("name", BuildingType.CASTLE.toString());
        assertEquals("name", BuildingType.FORGE.toString());
        assertEquals("name", BuildingType.GARDEN.toString());
        assertEquals("name", BuildingType.MARKET.toString());
        assertEquals("name", BuildingType.MINT.toString());
        assertEquals("name", BuildingType.MONASTERY.toString());
        assertEquals("name", BuildingType.UNIVERSITY.toString());
        assertEquals("name", BuildingType.WORKSHOP.toString());
        assertEquals("name", BuildingType.BANK.toString());
        assertEquals("name", BuildingType.MILITARY_ACADEMY.toString());
        assertEquals("name", BuildingType.MUSEUM.toString());
        assertEquals("name", BuildingType.OPERA_HOUSE.toString());
        assertEquals("name", BuildingType.PUBLIC_SCHOOL.toString());
        assertEquals("name", BuildingType.SATRAPS_COURT.toString());
        assertEquals("name", BuildingType.THEATRE.toString());
        assertEquals("name", BuildingType.WINDMILL.toString());
        assertEquals("name", BuildingType.ARSENAL.toString());
        assertEquals("name", BuildingType.BROADCAST_TOWER.toString());
        assertEquals("name", BuildingType.FACTORY.toString());
        assertEquals("name", BuildingType.HOSPITAL.toString());
        assertEquals("name", BuildingType.MILITARY_BASE.toString());
        assertEquals("name", BuildingType.STOCK_EXCHANGE.toString());
    }
}

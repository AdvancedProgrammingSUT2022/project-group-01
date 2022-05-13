package controller;

import model.Game;
import model.map.OpenSimplexNoise;
import model.resource.ResourceType;
import model.tile.Boarder;
import model.tile.Terrain;
import model.tile.TerrainFeature;
import model.tile.Tile;

import java.util.Random;
import java.util.Vector;

public class MapGenerationController extends Controller {
    private final Game game;
    Random rand = new Random(58);

    public MapGenerationController(Game game) {
        this.game = game;
    }

    public void generateMap(int mapSize) {
        Vector<Vector<Tile>> map = new Vector<>();
        for (int i = 0; i < mapSize; i++) {
            map.add(new Vector<>());
            for (int j = 0; j < mapSize; j++) {
                map.get(i).add(null);
            }
        }
        OpenSimplexNoise elevationNoise = new OpenSimplexNoise();
        OpenSimplexNoise humidityNoise = new OpenSimplexNoise(2);
        double[][] elevation;
        double[][] humidity;
        elevation = makeNoise(elevationNoise, mapSize);
        humidity = makeNoise(humidityNoise, mapSize);
        Tile[][] boardMap = new Tile[mapSize][mapSize];
        int j = 1;
        for (int q = 0; q < mapSize; q++) {
            for (int p = 0; p < mapSize; p++) {
                if ((p + q < (mapSize - 1) / 2) || (p + q > 3 * (mapSize - 1) / 2)) {
                    boardMap[q][p] = null;
                    continue;
                }
                boardMap[q][p] = new Tile(p, q, j);
                boardMap[q][p].setTerrain(getTerrainByElevation(elevation[q][p]));
                j++;
            }
        }

        initializeBoarders(boardMap, mapSize);
        setRiverRoots(boardMap);
        setFeatures(boardMap, humidity);
        setResources(boardMap);
        Vector<Vector<Tile>> generatedMap = new Vector<>();
        for (int q = 0; q < mapSize; q++) {
            generatedMap.add(new Vector<>());
            for (int p = 0; p < mapSize; p++) {
                generatedMap.get(q).add(boardMap[q][p]);
            }
        }
        game.getMap().setMap(generatedMap);
        setContinent();
    }


    private void setFeatures(Tile[][] board, double[][] humidity) {
        int mapSize = game.getMap().getMapSize();
        for (int q = 0; q < mapSize; q++) {
            for (int p = 0; p < mapSize; p++) {
                if ((p + q < (mapSize - 1) / 2) || (p + q > 3 * (mapSize - 1) / 2)) continue;
                board[q][p].setFeature(getFeatureByHumidity(humidity[q][p], board[q][p]));
            }
        }
    }

    private double[][] makeNoise(OpenSimplexNoise noise, int mapSize) {
        Random rand = new Random(58);
        double[][] array = new double[mapSize][mapSize];
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                double nx = (double) x / mapSize - 0.5;
                double ny = (double) y / mapSize - 0.5;
                array[y][x] = 1 * noise.eval(1 * nx, 1 * ny) + 0.5 * noise.eval(2 * nx, 2 * ny) + 0.25 * noise.eval(4 * nx, 4 * ny);
                array[y][x] = (array[y][x] + 1.75) / 3.5;
            }
        }
        for (int x = (mapSize - 1) / 2; x < mapSize; x++) {
            int i = rand.nextInt(5) + 1;
            for (int y = 0; y < i; y++) {
                array[y][x] = 0;
            }
        }
        for (int x = 0; x < (mapSize - 1) / 2; x++) {
            int i = rand.nextInt(5) + 1;
            for (int y = 0; y <= i; y++) {
                array[(mapSize - 1) / 2 - x + y][x] = 0;
            }
        }
        for (int x = 0; x < (mapSize + 1) / 2; x++) {
            int i = rand.nextInt(5) + 1;
            for (int j = 0; j <= i; j++) {
                array[mapSize - 1 - j][x] = 0;
            }
        }
        for (int x = (mapSize + 1) / 2; x < mapSize; x++) {
            int i = rand.nextInt(5) + 1;
            for (int j = 0; j <= i; j++) {
                array[mapSize - 1 - (x - (mapSize - 1) / 2) - i][x] = 0;
            }
        }
        for (int y = 0; y < (mapSize + 1) / 2; y++) {
            int i = rand.nextInt(5) + 1;
            for (int j = 0; j <= i; j++) {
                array[y][mapSize - 1 - j] = 0;
            }
        }
        for (int y = (mapSize - 1) / 2; y < mapSize; y++) {
            int i = rand.nextInt(5) + 1;
            for (int j = 0; j <= i; j++) {
                array[y][j] = 0;
            }
        }
        return array;
    }

    private Terrain getTerrainByElevation(double noise) {
        if (noise < 0.003) return Terrain.OCEAN;
        if (noise < 0.2) return Terrain.DESERT;
        if (noise < 0.4) return Terrain.PLAINS;
        if (noise < 0.5) return Terrain.GRASSLAND;
        if (noise < 0.6) return Terrain.TUNDRA;
        if (noise < 0.75) return Terrain.HILLS;
        if (noise < 0.9) return Terrain.MOUNTAIN;
        return Terrain.SNOW;
    }

    private TerrainFeature getFeatureByHumidity(double noise, Tile tile) {
        Terrain terrain = tile.getTerrain();
        switch (terrain) {
            case DESERT : {
                if (noise < 0.3) return TerrainFeature.OASIS;
                if (noise > 0.3) {
                    if (tile.hasRiverNearby()) return TerrainFeature.FLOOD_PLAINS;
                }
                return null;
            }
            case GRASSLAND : {
                if (noise < 0.3) return TerrainFeature.FOREST;
                if (noise < 0.6) return TerrainFeature.MARSH;
                return null;
            }
            case HILLS :
            case PLAINS : {
                if (noise < 0.3) return TerrainFeature.FOREST;
                if (noise < 0.6) return TerrainFeature.JUNGLE;
                return null;
            }
            case OCEAN : {
                if (noise < 0.3) return TerrainFeature.ICE;
                return null;
            }
            case TUNDRA : {
                if (noise < 0.4) return TerrainFeature.FOREST;
                return null;
            }
            default : {
                return null;
            }
        }
    }


    private void initializeBoarders(Tile[][] board, int mapSize) {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                if (board[y][x] != null) {
                    setTileBoarders(y, x, board, mapSize);
                }
            }
        }
    }

    private void setTileBoarders(int p, int q, Tile[][] board, int mapSize) {
        int nextP;
        int nextQ;
        // boarder 0
        nextP = p + 1;
        nextQ = q - 1;
        setBoardSide(p, q, nextP, nextQ, 0, board, mapSize);
        // boarder 1
        nextP = p + 1;
        nextQ = q;
        setBoardSide(p, q, nextP, nextQ, 1, board, mapSize);
        // boarder 2
        nextP = p;
        nextQ = q + 1;
        setBoardSide(p, q, nextP, nextQ, 2, board, mapSize);
        // boarder 3
        nextP = p - 1;
        nextQ = q + 1;
        setBoardSide(p, q, nextP, nextQ, 3, board, mapSize);
        // boarder 4
        nextP = p - 1;
        nextQ = q;
        setBoardSide(p, q, nextP, nextQ, 4, board, mapSize);
        // boarder 5
        nextP = p;
        nextQ = q - 1;
        setBoardSide(p, q, nextP, nextQ, 5, board, mapSize);
    }

    private void setBoardSide(int p, int q, int nextP, int nextQ, int i, Tile[][] board, int mapSize) {
        int j = (i + 3) % 6;

        Boarder tempBoarder;
        if ((nextP < 0) || (nextP > mapSize - 1) || (nextQ < 0) || (nextQ > mapSize - 1) || (nextP + nextQ < (mapSize - 1) / 2) || (nextP + nextQ > 3 * (mapSize - 1) / 2)) {
            tempBoarder = new Boarder(board[q][p], null, false, mapSize);
            board[q][p].setBoarder(tempBoarder, i);
        } else {
            if (board[q][p].getBoarder(i) != null) return;
            tempBoarder = new Boarder(board[q][p], board[nextQ][nextP], false, mapSize);
            board[q][p].setBoarder(tempBoarder, i);
            board[nextQ][nextP].setBoarder(tempBoarder, j);
        }
    }

    private void setRiverRoots(Tile[][] board) {
        Vector<Tile> mountains = new Vector<>();
        for (int q = 0; q < game.getMap().getMapSize(); q++) {
            for (int p = 0; p < game.getMap().getMapSize(); p++) {
                if (board[q][p] != null) {
                    if (board[q][p].getTerrain().equals(Terrain.MOUNTAIN)) mountains.add(board[q][p]);
                }
            }
        }
        //add rivers for mountains
        for (Tile tile : mountains) {
            if (rand.nextInt() % 2 == 0) continue;
            addRivers(tile.getBoarder(Math.abs(rand.nextInt() % 6)));
        }
    }

    private void addRivers(Boarder boarder) {
        Boarder currentBoarder;
        currentBoarder = boarder;
        if (currentBoarder.getWeight() == 0) {
            currentBoarder.destroyRiver();
            return;
        }
        if (currentBoarder.getAdjacentTiles()[0].getTerrain().equals(Terrain.OCEAN)) {
            currentBoarder.destroyRiver();
            return;
        }
        if (currentBoarder.getAdjacentTiles()[1].getTerrain().equals(Terrain.OCEAN)) {
            currentBoarder.destroyRiver();
            return;
        }
        if (currentBoarder.isRiver()) return;
        currentBoarder.setRiver();
        int firstTileDirection;
        int secondTileDirection;
        firstTileDirection = (currentBoarder.getAdjacentTiles()[0].getBoarderDirection(currentBoarder) + 1) % 6;
        secondTileDirection = (currentBoarder.getAdjacentTiles()[1].getBoarderDirection(currentBoarder) - 1) % 6;
        if (firstTileDirection < 0) firstTileDirection += 6;
        if (secondTileDirection < 0) secondTileDirection += 6;
        Boarder firstBoarder;
        Boarder secondBoarder;
        firstBoarder = currentBoarder.getAdjacentTiles()[0].getBoarder(firstTileDirection);
        secondBoarder = currentBoarder.getAdjacentTiles()[1].getBoarder(secondTileDirection);
        if (firstBoarder.isRiver() || secondBoarder.isRiver()) {
            firstTileDirection = (currentBoarder.getAdjacentTiles()[0].getBoarderDirection(currentBoarder) - 1) % 6;
            secondTileDirection = (currentBoarder.getAdjacentTiles()[1].getBoarderDirection(currentBoarder) + 1) % 6;
            if (firstTileDirection < 0) firstTileDirection += 6;
            if (secondTileDirection < 0) secondTileDirection += 6;
            firstBoarder = currentBoarder.getAdjacentTiles()[0].getBoarder(firstTileDirection);
            secondBoarder = currentBoarder.getAdjacentTiles()[1].getBoarder(secondTileDirection);
            if (firstBoarder.isRiver() || secondBoarder.isRiver()) return;
        }
        if (firstBoarder.getWeight() < secondBoarder.getWeight()) {
            addRivers(firstBoarder);
            return;
        }
        if (firstBoarder.getWeight() > secondBoarder.getWeight()) {
            addRivers(secondBoarder);
            return;
        }
        if (rand.nextInt() % 2 == 0) addRivers(firstBoarder);
        else addRivers(secondBoarder);
    }

    private void setContinent() {
        Vector<Tile> continentVector = new Vector<>();
        Vector<Tile> adjacentTiles = new Vector<>();
        int centerCoordinate = game.getMap().getMapSize();
        centerCoordinate = (centerCoordinate - 1) / 2;
        adjacentTiles.add(game.getMap().getTile(centerCoordinate, centerCoordinate));
        Vector<Tile> tempTile = new Vector<>();
        boolean resume = true;
        while (resume) {
            resume = false;
            for (Tile tile : adjacentTiles) {
                continentVector.add(tile);
                for (Tile adjacentTile : tile.getAdjacentTiles()) {
                    if (!adjacentTile.getTerrain().equals(Terrain.OCEAN)) {
                        if ((!continentVector.contains(adjacentTile)) && (!adjacentTiles.contains(adjacentTile)) && (!tempTile.contains(adjacentTile))) {
                            tempTile.add(adjacentTile);
                            resume = true;
                        }
                    }
                }
                adjacentTiles = new Vector<>(tempTile);
                tempTile = new Vector<>();
            }
        }
        game.getMap().setReachableTiles(continentVector);
    }

    private void setResources(Tile[][] board) {
        for (int q = 0; q < game.getMap().getMapSize(); q++) {
            for (int p = 0; p < game.getMap().getMapSize(); p++) {
                if (board[q][p] == null) continue;
                if (rand.nextInt() % 5 == 0) continue;
                ResourceType resourceType;
                int numberOfResources;
                int terrainResourcesCount = board[q][p].getTerrain().possibleResources.size();
                int featureResourcesCount = 0;
                if (board[q][p].getFeature() != null)
                    featureResourcesCount = board[q][p].getFeature().possibleResources.size();
                numberOfResources = terrainResourcesCount + featureResourcesCount;
                if (numberOfResources <= 0) continue;
                int resourceIndex = Math.abs(rand.nextInt(numberOfResources));
                if (resourceIndex < terrainResourcesCount) {
                    resourceType = board[q][p].getTerrain().possibleResources.elementAt(resourceIndex);
                } else {
                    resourceType = board[q][p].getFeature().possibleResources.elementAt(resourceIndex - terrainResourcesCount);
                }

                board[q][p].setAvailableResource(resourceType);
            }
        }
    }


}

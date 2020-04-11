package Objects;

import ObjectComponents.MapObject;
import ObjectComponents.ObjectSizeHandler;
import UI.Screens.LevelContainer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    LevelContainer levelContainer;
    ArrayList<MapObject> mapObjects = new ArrayList<>();

    private float wallThickness = 20;
    private float tileSize = 10;
    private float screenWidth = ObjectSizeHandler.screenSize.width;
    private float screenHeight = ObjectSizeHandler.screenSize.height;
    private MapObject top, bottom, left, right;

    public void settings(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
    }

    public void genMap()
    {
        mapObjects.clear();
        setFourWalls();
        generateTiles();
    }

    private void generateTiles()
    {
        float horizontalTileAmount = screenWidth / tileSize;
        float verticalTileAmount = screenHeight / tileSize;
        System.out.println("Horizontal: " + horizontalTileAmount + ", Vertical: " + verticalTileAmount);

        for(int v = 0; v < verticalTileAmount + 1; v++)
        {
            for(int h = 0; h < horizontalTileAmount + 1; h++)
            {
                MapObject tile = new MapObject(levelContainer, (h * tileSize) - (tileSize / 2), (v * tileSize) - (tileSize / 2), tileSize, tileSize, 0);
                if(!tile.intersect(top) && !tile.intersect(bottom) && !tile.intersect(left) && !tile.intersect(right))
                {
                    mapObjects.add(tile);
                }
            }
        }
        float[] testTile = generateRandomWalls();
        mapObjects.clear(); //Remove all mapObjects
        MapObject testRandomGenTile = new MapObject(levelContainer, testTile[0], testTile[1], testTile[2], testTile[3], testTile[4]);
        mapObjects.add(testRandomGenTile);
    }

    private float[] generateRandomWalls()
    {
        Random random = new Random();

        float x, y, width, height, angle;
        float[] wall = new float[5];

        float minX = mapObjects.get(0).getxPos();
        float minY = mapObjects.get(0).getyPos();
        float maxX = mapObjects.get(mapObjects.size() - 1).getxPos();
        float maxY = mapObjects.get(mapObjects.size() - 1).getyPos();

        int rangeX = (int) (mapObjects.get(mapObjects.size() - 1).getxPos() - mapObjects.get(0).getxPos());
        int rangeY = (int) (mapObjects.get(mapObjects.size() - 1).getyPos() - mapObjects.get(0).getyPos());

        x = minX + random.nextInt(rangeX);
        y = minY + random.nextInt(rangeY);
        width = 25;
        height = 25;
        angle = 25;

        wall[0] = x;
        wall[1] = y;
        wall[2] = width;
        wall[3] = height;
        wall[4] = angle;

        return wall;
    }

    private void setFourWalls()
    {
        top = new MapObject(levelContainer, screenWidth / 2, wallThickness / 2, screenWidth, wallThickness, 0);
        bottom = new MapObject(levelContainer, screenWidth / 2, screenHeight - wallThickness / 2, screenWidth, wallThickness, 0);
        left = new MapObject(levelContainer, wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);
        right = new MapObject(levelContainer, screenWidth - wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);

        //mapObjects.add(top);
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
}

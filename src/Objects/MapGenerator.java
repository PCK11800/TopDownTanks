package Objects;

import ObjectComponents.MapObject;
import ObjectComponents.ObjectSizeHandler;
import ObjectComponents.RotatingObject;
import UI.Screens.LevelContainer;
import org.jsfml.graphics.Color;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    LevelContainer levelContainer;
    ArrayList<MapObject> mapObjects = new ArrayList<>();
    ArrayList<MapObject> pathTiles = new ArrayList<>();

    private float wallThickness = 20;
    private float tileSize = 10;
    private float screenWidth = ObjectSizeHandler.screenSize.width;
    private float screenHeight = ObjectSizeHandler.screenSize.height;
    private float horizontalTileAmount, verticalTileAmount;
    private MapObject top, bottom, left, right;
    private float minX, minY, maxX, maxY;

    public void settings(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
    }

    public void genMap()
    {
        mapObjects.clear();
        placeholder();
        generateAvailableTiles();
    }

    private void generateAvailableTiles()
    {
        setFourWalls();
        horizontalTileAmount = screenWidth / tileSize;
        verticalTileAmount = screenHeight / tileSize;
        System.out.println("Horizontal: " + horizontalTileAmount + ", Vertical: " + verticalTileAmount);

        for(int v = 0; v < verticalTileAmount + 1; v++)
        {
            for(int h = 0; h < horizontalTileAmount + 1; h++)
            {
                MapObject tile = new MapObject(levelContainer, (h * tileSize) - (tileSize / 2), (v * tileSize) - (tileSize / 2), tileSize, tileSize, 0, Color.GREEN, true);
                if(!tile.intersect(top) && !tile.intersect(bottom) && !tile.intersect(left) && !tile.intersect(right))
                {
                    boolean intersect = false;
                    for(MapObject mapObject : mapObjects)
                    {
                        if(tile.intersect(mapObject) || mapObject.contains(tile.getxPos(), tile.getyPos()))
                        {
                            intersect = true;
                        }
                    }
                    if(!intersect)
                    {
                        pathTiles.add(tile);
                    }
                }
            }
        }

        minX = mapObjects.get(0).getxPos();
        minY = mapObjects.get(0).getyPos();
        maxX = mapObjects.get(mapObjects.size() - 1).getxPos();
        maxY = mapObjects.get(mapObjects.size() - 1).getyPos();
    }

    private void setFourWalls()
    {
        top = new MapObject(levelContainer, screenWidth / 2, wallThickness / 2, screenWidth, wallThickness, 0, Color.WHITE, false);
        bottom = new MapObject(levelContainer, screenWidth / 2, screenHeight - wallThickness / 2, screenWidth, wallThickness, 0,  Color.WHITE, false);
        left = new MapObject(levelContainer, wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90,  Color.WHITE, false);
        right = new MapObject(levelContainer, screenWidth - wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90,  Color.WHITE, false);

        mapObjects.add(top);
        mapObjects.add(bottom);
        mapObjects.add(left);
        mapObjects.add(right);
    }


    private void placeholder()
    {
        MapObject tile = new MapObject(levelContainer, screenWidth/2, screenHeight/2, 200, 200, 0,  Color.WHITE, false);
        mapObjects.add(tile);
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
    public ArrayList<MapObject> getPathTiles() { return pathTiles; }
}

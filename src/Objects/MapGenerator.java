package Objects;

import ObjectComponents.MapObject;
import ObjectComponents.ObjectSizeHandler;
import UI.Screens.LevelContainer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    LevelContainer levelContainer;
    ArrayList<MapObject> mapObjects = new ArrayList<>();

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

        setFourWalls();
        placeholder();
    }

    private void setMinMax()
    {
        setFourWalls();
        horizontalTileAmount = screenWidth / tileSize;
        verticalTileAmount = screenHeight / tileSize;
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

        minX = mapObjects.get(0).getxPos();
        minY = mapObjects.get(0).getyPos();
        maxX = mapObjects.get(mapObjects.size() - 1).getxPos();
        maxY = mapObjects.get(mapObjects.size() - 1).getyPos();

        mapObjects.clear();
        setFourWalls();
    }

    private void setFourWalls()
    {
        top = new MapObject(levelContainer, screenWidth / 2, wallThickness / 2, screenWidth, wallThickness, 0);
        bottom = new MapObject(levelContainer, screenWidth / 2, screenHeight - wallThickness / 2, screenWidth, wallThickness, 0);
        left = new MapObject(levelContainer, wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);
        right = new MapObject(levelContainer, screenWidth - wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);

        mapObjects.add(top);
        mapObjects.add(bottom);
        mapObjects.add(left);
        mapObjects.add(right);
    }

    private void readMap(String fileName)
    {
        try{
            BufferedReader mapReader = new BufferedReader(new FileReader(fileName));
            int tile;
            int tileNum = 1;
            while((tile = mapReader.read()) != -1){
                char character = (char) tile;
                tile = Character.getNumericValue(character);
                tileNum++;

                if(tile == 1)
                {
                    addTile(tileNum);
                }
            }
        } catch (Exception e)
        {
            System.out.println("Map not found!");
        }
    }

    private void addTile(int tileNum)
    {
        float xVal = 0;
        float yVal = 0;
        int usedNum = tileNum;

        while (usedNum >= horizontalTileAmount)
        {
            usedNum = (int) (usedNum - horizontalTileAmount);
            yVal++;
        }

        xVal = usedNum - yVal;

        MapObject tile = new MapObject(levelContainer, (xVal * tileSize) - (tileSize / 2), (yVal * tileSize) - (tileSize / 2), tileSize, tileSize, 0);
        mapObjects.add(tile);
    }

    private void placeholder()
    {
        MapObject tile = new MapObject(levelContainer, screenWidth/2, screenHeight/2, 200, 200, 0);
        mapObjects.add(tile);
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
}

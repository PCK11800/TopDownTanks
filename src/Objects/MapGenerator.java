package Objects;

import ObjectComponents.MapObject;
import ObjectComponents.ObjectSizeHandler;
import UI.Screens.LevelContainer;

import java.awt.*;
import java.util.ArrayList;

public class MapGenerator {

    LevelContainer levelContainer;
    ArrayList<MapObject> mapObjects = new ArrayList<>();

    private float wallThickness = 20;
    private float tileSize = 40;
    private float screenWidth = ObjectSizeHandler.defaultWidth;
    private float screenHeight = ObjectSizeHandler.defaultHeight;
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
    }

    private void setFourWalls()
    {
        top = new MapObject(levelContainer, screenWidth / 2, wallThickness / 2, screenWidth, wallThickness, 0);
        bottom = new MapObject(levelContainer, screenWidth / 2, screenHeight - wallThickness / 2, screenWidth, wallThickness, 0);
        left = new MapObject(levelContainer, wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);
        right = new MapObject(levelContainer, screenWidth - wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90);
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
}

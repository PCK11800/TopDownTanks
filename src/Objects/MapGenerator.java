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
    private float screenWidth = ObjectSizeHandler.defaultWidth;
    private float screenHeight = ObjectSizeHandler.defaultHeight;
    private float[] scaleConstant = ObjectSizeHandler.scaleConstant();

    public void settings(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
    }

    public void genMap()
    {
        mapObjects.clear();

        //Add four walls
        mapObjects.add(new MapObject(levelContainer, screenWidth / 2, wallThickness / 2, screenWidth, wallThickness, 0)); //Top
        mapObjects.add(new MapObject(levelContainer, screenWidth / 2, screenHeight - wallThickness / 2, screenWidth, wallThickness, 0)); //Bottom
        mapObjects.add(new MapObject(levelContainer, screenWidth - wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90)); //Right
        mapObjects.add(new MapObject(levelContainer, wallThickness / 2, screenHeight / 2, screenHeight, wallThickness, 90)); //Left
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
}

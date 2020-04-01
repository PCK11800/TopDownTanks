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
    private float screenWidth, screenHeight;
    private float[] scaleConstant = ObjectSizeHandler.scaleConstant();

    public void settings(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
    }

    public void genMap()
    {
        mapObjects.clear();

        //Add four walls
        screenWidth = ObjectSizeHandler.defaultWidth;
        screenHeight = ObjectSizeHandler.defaultHeight;
        mapObjects.add(new MapObject(levelContainer, screenWidth / 2, wallThickness, screenWidth, wallThickness, 0));
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
}

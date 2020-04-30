package Objects;

import EnemyAI.AStarSearch;
import ObjectComponents.MapObject;
import ObjectComponents.ObjectSizeHandler;
import ObjectComponents.PathTile;
import ObjectComponents.RotatingObject;
import UI.Screens.LevelContainer;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;
import org.jsfml.window.Keyboard;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    LevelContainer levelContainer;
    ArrayList<MapObject> mapObjects = new ArrayList<>();
    ArrayList<PathTile> pathTiles = new ArrayList<>();

    private float wallThickness = 20;
    private float tileSize = 10;
    private float screenWidth = ObjectSizeHandler.screenSize.width;
    private float screenHeight = ObjectSizeHandler.screenSize.height;
    private MapObject top, bottom, left, right;
    private float minX, minY, maxX, maxY;
    private int horizontalTileNum, verticalTileNum;

    int r = 0;
    int c = 0;

    public void settings(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
    }

    public void genMap()
    {
        mapObjects.clear();
        placeholder();
        generateAvailableTiles();
        calculateRowColumnNumber();
        generatePathTileNeighbors();
        searchTest();
    }

    private void generateAvailableTiles()
    {
        setFourWalls();
        float maxHorizontalTileAmount = screenWidth / tileSize;
        float maxVerticalTileAmount = screenHeight / tileSize;
        System.out.println("Horizontal: " + maxHorizontalTileAmount + ", Vertical: " + maxVerticalTileAmount);

        for(int v = 0; v < maxVerticalTileAmount + 1; v++)
        {
            for(int h = 0; h < maxHorizontalTileAmount + 1; h++)
            {
                PathTile tile = new PathTile(levelContainer, (h * tileSize) - (tileSize / 2), (v * tileSize) - (tileSize / 2), tileSize, tileSize);
                if(!tile.intersect(top) && !tile.intersect(bottom) && !tile.intersect(left) && !tile.intersect(right))
                {
                    for(MapObject mapObject : mapObjects)
                    {
                        if(tile.intersect(mapObject) || mapObject.contains(tile.getxPos(), tile.getyPos()))
                        {
                        }
                    }
                    pathTiles.add(tile);
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
        //MapObject tile = new MapObject(levelContainer, screenWidth/2, screenHeight/2, 200, 200, 0,  Color.WHITE, false);
        //mapObjects.add(tile);
    }

    private void generatePathTileNeighbors()
    {
        for(int r = 0; r < verticalTileNum; r++)
        {
            for(int c = 0; c < horizontalTileNum; c++)
            {
                PathTile thisTile = tileAt(r, c);
                //Has North
                if(r > 0){
                    thisTile.addNeighbor(tileAt(r-1, c));
                }
                //Has South
                if(r < verticalTileNum - 1){
                    thisTile.addNeighbor(tileAt(r+1, c));
                }
                //Has West
                if(c > 0){
                    thisTile.addNeighbor(tileAt(r, c-1));
                }
                //Has East
                if(c < horizontalTileNum - 1){
                    thisTile.addNeighbor(tileAt(r, c+1));
                }
                //Has North West
                if(r > 0 && c > 0) {
                    thisTile.addNeighbor(tileAt(r-1, c-1));
                }
                //Has North East
                if(r > 0 && c < horizontalTileNum - 1){
                    thisTile.addNeighbor(tileAt(r-1, c+1));
                }
                //Has South West
                if(r < verticalTileNum - 1 && c > 0){
                    thisTile.addNeighbor(tileAt(r+1, c-1));
                }
                //Has South East
                if(r < verticalTileNum - 1 && c < horizontalTileNum - 1){
                    thisTile.addNeighbor(tileAt(r+1, c+1));
                }
            }
        }
    }

    private PathTile tileAt(int r, int c)
    {
        return pathTiles.get(r * horizontalTileNum + c);
    }

    private void calculateRowColumnNumber()
    {
        int horizontalTileNum = 0;
        int verticalTileNum = 0;

        PathTile firstTile = pathTiles.get(0);
        float firstTileYPos = firstTile.getyPos();
        boolean nextRow = false;

        while(!nextRow){
            PathTile nextTile = pathTiles.get(horizontalTileNum);
            if(nextTile.getyPos() == firstTileYPos){
                horizontalTileNum++;
            }
            else{
                nextRow = true;
            }
        }

        verticalTileNum = pathTiles.size() / horizontalTileNum;

        this.horizontalTileNum = horizontalTileNum;
        this.verticalTileNum = verticalTileNum;

        System.out.println("Horizontal and Vertical tile amount: " + this.horizontalTileNum + ", " + this.verticalTileNum);
    }

    private void searchTest()
    {
        AStarSearch searchEngine = new AStarSearch();
        List path = searchEngine.findPath(pathTiles.get(0), pathTiles.get(pathTiles.size() - 1));


        for(int i = 0; i < path.size(); i++)
        {
            PathTile tile = (PathTile) path.get(i);
            tile.setFillColor(Color.RED);
        }
    }


    Clock controlClock = new Clock();
    public void control()
    {
        if(controlClock.getElapsedTime().asMilliseconds() >= 100){
            if(Keyboard.isKeyPressed(Keyboard.Key.W)) {
                r--;
                System.out.println(r + ", " + c);
                controlClock.restart();
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.A)) {
                c--;
                System.out.println(r + ", " + c);
                controlClock.restart();
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.S)) {
                r++;
                System.out.println(r + ", " + c);
                controlClock.restart();
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.D)) {
                c++;
                System.out.println(r + ", " + c);
                controlClock.restart();
            }

            for(PathTile tile : pathTiles){
                tile.setFillColor(Color.GREEN);
            }
        }
        if(r < verticalTileNum && r > -1){
            if(c < horizontalTileNum && c > -1){
                tileAt(r, c).setFillColor(Color.RED);
                for(PathTile neighbor : tileAt(r, c).getNeighbors()){
                    neighbor.setFillColor(Color.YELLOW);
                }
            }
        }
    }

    public ArrayList<MapObject> getMapObjects() { return mapObjects; }
    public ArrayList<PathTile> getPathTiles() { return pathTiles; }
}

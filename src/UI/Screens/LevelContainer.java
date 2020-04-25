package UI.Screens;

import EnemyAI.AI;
import ObjectComponents.MapObject;
import ObjectComponents.TankComponents.Shell;
import Objects.MapGenerator;
import Objects.Player;
import Objects.Tank;
import Window.Window;

import java.util.ArrayList;

public class LevelContainer {

    private Window window;
    private Player player;
    private AI ai;
    private ArrayList<MapObject> mapObjects;
    private ArrayList<Shell> activeShells = new ArrayList<>();
    private MapGenerator mapGenerator = new MapGenerator();

    public void initialize(Window window)
    {
        this.window = window;
        mapGenerator.settings(this);
        mapGenerator.genMap();
        mapObjects = mapGenerator.getMapObjects();

        player = new Player();
        player.setLevelContainer(this);

        ai = new AI();
        ai.setLevelContainer(this);
    }

    public Window getWindow()
    {
        return window;
    }

    public ArrayList<Shell> getActiveShells()
    {
        return activeShells;
    }

    private void handleShells()
    {
        for(int i = 0; i < activeShells.size(); i++)
        {
            Shell shell = activeShells.get(i);
            if(!shell.isActive())
            {
                activeShells.remove(shell);
                activeShells.trimToSize();
            }
            else
            {
                shell.update();
            }
        }
    }

    private void handleMapObjects()
    {
        for(int i = 0; i < mapObjects.size(); i++)
        {
            MapObject mapObject = mapObjects.get(i);
            mapObject.update();
        }
    }

    public ArrayList<MapObject> getMapObjects()
    {
        return mapObjects;
    }
    public Player getPlayer() { return player; }

    public void update()
    {
        handleShells();
        handleMapObjects();
        player.update();
        ai.update();
    }
}

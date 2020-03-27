package UI.Screens;

import ObjectComponents.TankComponents.Shell;
import ObjectComponents.Textures;
import Objects.Tank;
import Window.Window;

import java.util.ArrayList;

public class LevelContainer {

    private Window window;
    private Tank tank;
    private ArrayList<Shell> activeShells = new ArrayList<>();

    public void initialize(Window window)
    {
        this.window = window;
        tank = new Tank();
        tank.setLevelContainer(this);
        tank.setSize(80, 80);
        //tank.setTankColor(Color.GREEN, Color.GREEN);
        tank.setOutline();
        tank.setHullTexture(Textures.hull_default);
        tank.setTurretTexture(Textures.turret_default);
        tank.setLocation(200, 100);
        tank.setVelocity(4);
        tank.setTurningDistance(2);
        tank.setPlayerControlled();
        tank.setShellParameters(Textures.shell_default, 4);
        tank.setRateOfFire(100);
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
            if(shell.checkOutOfBounds())
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

    public void update()
    {
        handleShells();
        tank.update();
    }
}

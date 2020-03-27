package UI.Screens;

import ObjectComponents.TankComponents.Shell;
import Objects.Player;
import Window.Window;

import java.util.ArrayList;

public class LevelContainer {

    private Window window;
    private Player player;
    private ArrayList<Shell> activeShells = new ArrayList<>();

    public void initialize(Window window)
    {
        this.window = window;
        player = new Player();
        player.setLevelContainer(this);
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
        player.update();
    }
}

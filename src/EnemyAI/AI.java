package EnemyAI;

import ObjectComponents.Textures;
import Objects.Tank;
import UI.Screens.LevelContainer;
import org.jsfml.graphics.Color;

public class AI {

    private Tank tank;
    private LogicEngine logic;

    public AI()
    {
        tank = new Tank();
        tank.setSize(100, 100);
        tank.setTankColor(Color.GREEN, Color.GREEN);
        tank.setOutline();
        tank.setHullTexture(Textures.hull_default);
        tank.setTurretTexture(Textures.turret_default);
        tank.setLocation(800, 100);
        tank.setVelocity(4);
        tank.setTurningDistance(2);
        tank.setShellParameters(Textures.shell_default, 4);
        tank.setRateOfFire(100);
    }

    public void setLevelContainer(LevelContainer levelContainer)
    {
        tank.setLevelContainer(levelContainer);
        logic = new LogicEngine(levelContainer.getPlayer().getTank(), tank, levelContainer.getPathTiles());
    }

    public void update()
    {
        logic.update();
    }

    public Tank getTank() { return tank; }
}

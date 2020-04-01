package Objects;

import ObjectComponents.Textures;
import UI.Screens.LevelContainer;

public class Player {

    private Tank tank;

    public Player()
    {
        tank = new Tank();
        tank.setSize(65, 65);
        //tank.setTankColor(Color.GREEN, Color.GREEN);
        //tank.setOutline();
        tank.setHullTexture(Textures.hull_default);
        tank.setTurretTexture(Textures.turret_default);
        tank.setLocation(200, 100);
        tank.setVelocity(4);
        tank.setTurningDistance(2);
        tank.setPlayerControlled();
        tank.setShellParameters(Textures.shell_default, 4);
        tank.setRateOfFire(100);
    }

    public void setLevelContainer(LevelContainer levelContainer)
    {
        tank.setLevelContainer(levelContainer);
    }

    public void update()
    {
        tank.update();
    }

}

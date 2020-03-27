package UI.Screens;

import ObjectComponents.Textures;
import Objects.Tank;
import Window.Window;

public class GameManager {

    private Window window;
    private Tank tank;

    public void initialize(Window window)
    {
        this.window = window;
        tank = new Tank();
        tank.setWindow(window);
        tank.setSize(80, 80);
        //tank.setTankColor(Color.GREEN, Color.GREEN);
        tank.setOutline();
        tank.setHullTexture(Textures.hull_default);
        tank.setTurretTexture(Textures.turret_default);
        tank.setLocation(200, 100);
        tank.setVelocity(4);
        tank.setTurningDistance(2);
        tank.setPlayerControlled();
    }

    public void update()
    {
        tank.update();
    }
}

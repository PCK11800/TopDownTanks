package Objects;

import ObjectComponents.TankComponents.Hull;
import ObjectComponents.TankComponents.Turret;
import Window.Window;
import org.jsfml.graphics.Color;

public class Tank {

    protected Window window;
    protected Hull hull;
    protected Turret turret;

    private float xPos, yPos;
    private Color hullColor, turretColor;

    public Tank()
    {
        this.hull = new Hull();
        this.turret = new Turret();
        turret.setHull(hull);
    }

    public void setSize(float width, float height)
    {
        width = width / 100;
        height = height / 100;
        hull.setSize(width * 53, height * 75);
        turret.setSize(width * 53, height * 75);
    }

    public void setTankColor(Color hullColor, Color turretColor)
    {
        this.hullColor = hullColor;
        this.turretColor = turretColor;
        hull.setFillColor(hullColor);
        turret.setFillColor(turretColor);
    }

    public void setOutline()
    {
        hull.setOutlineColor(Color.WHITE);
        turret.setOutlineColor(Color.WHITE);
        hull.setOutlineThickness(1);
        turret.setOutlineThickness(1);
    }

    public void setHullTexture(String texturePath)
    {
        hull.setObjectTexture(texturePath);
    }

    public void setTurretTexture(String texturePath)
    {
        turret.setObjectTexture(texturePath);
    }

    public void setWindow(Window window)
    {
        this.window = window;
        hull.setWindow(window);
        turret.setWindow(window);
    }

    public void setLocation(float x, float y)
    {
        xPos = x;
        yPos = y;
        hull.setLocation(x, y);
        turret.setTurretPosition();
    }

    public void setTurningDistance(float turningDistance)
    {
        hull.setTurningDistance(turningDistance);
    }

    public void setVelocity(float velocity)
    {
        hull.setVelocity(velocity);
    }

    public void setPlayerControlled()
    {
        hull.setPlayerControlled();
    }

    public void update()
    {
        hull.update();
        turret.setTurretDirection_MouseControlled();
        turret.update();
    }

}

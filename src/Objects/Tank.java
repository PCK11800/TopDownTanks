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
        hull.setSize(width, (float)(width * 1.5));
        turret.setSize(width, width);
    }

    public void setTankColor(Color hullColor, Color turretColor, boolean outline)
    {
        this.hullColor = hullColor;
        this.turretColor = turretColor;
        hull.setFillColor(hullColor);
        turret.setFillColor(turretColor);
        if(outline)
        {
            turret.setOutlineColor(Color.WHITE);
            turret.setOutlineThickness(1);
        }
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

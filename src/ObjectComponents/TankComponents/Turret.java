package ObjectComponents.TankComponents;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class Turret extends RotatingObject {

    protected Window window;
    private Hull hull;
    private float turningDistance;

    public void setTurretPosition()
    {
        float xPos = hull.getxPos();
        float yPos = hull.getyPos();
        setLocation(xPos, yPos);
    }

    public void setTurretDirection_MouseControlled()
    {
        Vector2i mousePos = Mouse.getPosition(window);
        float dx = xPos - mousePos.x;
        float dy = yPos - mousePos.y;
        double rotationAngle = Math.toDegrees(Math.atan2(dy, dx));
        if(rotationAngle >= 360) {
            rotationAngle = rotationAngle - 360;
        }
        else if(rotationAngle <= 0) {
            rotationAngle = rotationAngle + 360;
        }

        rotateObject((float) rotationAngle - 90);
    }

    public void setHull(Hull hull)
    {
        this.hull = hull;
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public void update()
    {
        setTurretPosition();
        draw(window);
    }
}

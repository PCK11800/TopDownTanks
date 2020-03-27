package ObjectComponents.TankComponents;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.window.Keyboard;

public class Hull extends RotatingObject {

    protected Window window;
    private float velocity;
    private float turningDistance;

    public void moveForward()
    {
        xPos = (float) (xPos + (velocity * Math.sin(Math.toRadians(objectDirection))));
        yPos = (float) (yPos - (velocity * Math.cos(Math.toRadians(objectDirection))));
        setLocation(xPos, yPos);
    }

    public void moveBackward()
    {
        xPos = (float) (xPos - (velocity * Math.sin(Math.toRadians(objectDirection))));
        yPos = (float) (yPos + (velocity * Math.cos(Math.toRadians(objectDirection))));
        setLocation(xPos, yPos);
    }

    public void turnRight()
    {
        rotateObject(objectDirection + turningDistance);
    }

    public void turnLeft()
    {
        rotateObject(objectDirection - turningDistance);
    }

    public float getVelocity()
    {
        return velocity;
    }

    public void setVelocity(float velocity)
    {
        this.velocity = velocity;
    }

    public float getTurningDistance()
    {
        return turningDistance;
    }

    public void setTurningDistance(float turningDistance)
    {
        this.turningDistance = turningDistance;
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public void update()
    {
        draw(window);
    }

}

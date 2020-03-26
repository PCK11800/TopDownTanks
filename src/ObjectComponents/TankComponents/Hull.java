package ObjectComponents.TankComponents;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.window.Keyboard;

public class Hull extends RotatingObject {

    protected Window window;
    private float velocity;
    private float turningDistance;
    private boolean isPlayerControlled = false; //Default false

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

    public void setPlayerControlled()
    {
        isPlayerControlled = true;
    }

    private void playerControl()
    {
        if(Keyboard.isKeyPressed(Keyboard.Key.W))
        {
            moveForward();
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.A))
        {
            turnLeft();
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.S))
        {
            moveBackward();
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.D))
        {
            turnRight();
        }
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public void update()
    {
        if(isPlayerControlled)
        {
            playerControl();
        }
        draw(window);
    }

}

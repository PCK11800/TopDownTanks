package ObjectComponents.TankComponents;

import ObjectComponents.RotatingObject;
import Window.Window;

public class Shell extends RotatingObject {

    protected Window window;
    private Turret connectedTurret;
    private float speed;

    public Shell(Window window, Turret connectedTurret, float speed)
    {
        this.window = window;
        this.connectedTurret = connectedTurret;
        this.speed = speed;
        setLocation(
                //Adjustments needed so shells go out of the barrel instead - to prevent hitting oneself
                connectedTurret.getxPos(),
                connectedTurret.getyPos()
        );
        rotateObject(connectedTurret.getObjectDirection());
    }

    public void shotForward()
    {
        xPos = xPos + (float)(speed * Math.sin(Math.toRadians(this.objectDirection)));
        yPos = yPos - (float)(speed * Math.cos(Math.toRadians(this.objectDirection)));
        setLocation(xPos, yPos);
    }

    public boolean checkOutOfBounds()
    {
        float[] windowSize = window.getDimensions();
        if(getxPos() > windowSize[0] || getxPos() < 0) {
            return true;
        }
        else if (getyPos() > windowSize[1] || getyPos() < 0) {
            return true;
        }
        return false;
    }

    public void update()
    {
        draw(window);
        shotForward();
    }
}

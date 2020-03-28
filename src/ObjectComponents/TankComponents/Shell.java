package ObjectComponents.TankComponents;

import ObjectComponents.MapObject;
import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.system.Vector2f;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.logging.Level;

public class Shell extends RotatingObject {

    protected Window window;
    private Turret connectedTurret;
    private float speed;
    private ArrayList<MapObject> mapObjects;
    private boolean isActive = true;

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

    public void setMapObjects(ArrayList<MapObject> mapObjects)
    {
        this.mapObjects = mapObjects;
    }

    public void shotForward()
    {
        xPos = xPos + (float)(speed * Math.sin(Math.toRadians(this.objectDirection)));
        yPos = yPos - (float)(speed * Math.cos(Math.toRadians(this.objectDirection)));
        setLocation(xPos, yPos);
    }

    public void checkOutOfBounds()
    {
        float[] windowSize = window.getDimensions();
        if(getxPos() > windowSize[0] || getxPos() < 0) {
            isActive = false;
        }
        else if (getyPos() > windowSize[1] || getyPos() < 0) {
            isActive = false;
        }
        isActive = true;
    }

    //WORK IN PROGRESS - AIM TO MAKE RICOCHET WORKS FOR ALL ANGLE
    public void handleMapObjects()
    {
        for(int i = 0; i < mapObjects.size(); i++) {
            Line2D shellBounds[] = getObjectBounds();
            Line2D top = shellBounds[0];
            Line2D left = shellBounds[2];
            Line2D right = shellBounds[3];

            Line2D objectBounds[] = mapObjects.get(i).getObjectBounds();

            Line2D map_top = objectBounds[0];
            Line2D map_bottom = objectBounds[1];
            Line2D map_left = objectBounds[2];
            Line2D map_right = objectBounds[3];

            float mapObjectDirection = mapObjects.get(i).getObjectDirection();

            if (top.intersectsLine(map_top) || right.intersectsLine(map_top) || left.intersectsLine(map_top)) {
                rotateObject(180 - objectDirection + (mapObjectDirection * 2));
            }
            if (top.intersectsLine(map_bottom) || right.intersectsLine(map_bottom) || left.intersectsLine(map_bottom)) {
                rotateObject(180 - objectDirection + (mapObjectDirection * 2));
            }
            if (top.intersectsLine(map_left) || right.intersectsLine(map_left) || left.intersectsLine(map_left)) {
                rotateObject(0 - objectDirection + (mapObjectDirection * 2));
            }
            if (top.intersectsLine(map_right) || right.intersectsLine(map_right) || left.intersectsLine(map_right)) {
                rotateObject(0 - objectDirection + (mapObjectDirection * 2));
            }
        }
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void update()
    {
        draw(window);
        shotForward();
        handleMapObjects();
    }
}

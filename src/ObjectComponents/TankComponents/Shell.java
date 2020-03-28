package ObjectComponents.TankComponents;

import ObjectComponents.MapObject;
import ObjectComponents.RotatingObject;
import Window.Window;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.logging.Level;

public class Shell extends RotatingObject {

    protected Window window;
    private Turret connectedTurret;
    private float speed;
    private ArrayList<MapObject> mapObjects;

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

    public void handleMapObjects()
    {
        for(int i = 0; i < mapObjects.size(); i++)
        {
            Line2D shellBounds[] = getObjectBounds();
            Line2D top = shellBounds[0];
            Line2D bottom = shellBounds[1];
            Line2D left = shellBounds[2];
            Line2D right = shellBounds[3];

            Line2D objectBounds[] = mapObjects.get(i).getObjectBounds();

            Line2D map_top = objectBounds[0];
            Line2D map_bottom = objectBounds[1];
            Line2D map_left = objectBounds[2];
            Line2D map_right = objectBounds[3];

            int collisionState = 0; //0 = no collision, 1 = top, 10 = bottom, 33 = left, 71 = right

            if (top.intersectsLine(map_top) || right.intersectsLine(map_top) || left.intersectsLine(map_top)) {
                collisionState = collisionState + 1;
            }
            if (top.intersectsLine(map_bottom) || right.intersectsLine(map_bottom) || left.intersectsLine(map_bottom)) {
                collisionState = collisionState + 10;
            }
            if (top.intersectsLine(map_left) || right.intersectsLine(map_left) || left.intersectsLine(map_left)) {
                collisionState = collisionState + 33;
            }
            if (top.intersectsLine(map_right) || right.intersectsLine(map_right) || left.intersectsLine(map_right)) {
                collisionState = collisionState + 71;
            }
            if (bottom.intersectsLine(map_top) || bottom.intersectsLine(map_bottom) || bottom.intersectsLine(map_right) || bottom.intersectsLine(map_left)) {
                collisionState = 0;
            }

            if (collisionState > 0) {

                if (collisionState == 1 || collisionState == 10) {
                    rotateObject(180 - objectDirection);
                } else if (collisionState == 33 || collisionState == 71) {
                    rotateObject(0 - objectDirection);
                } else {
                    //Top left & top right corner
                    if (collisionState == 34 || collisionState == 72) {
                        if (objectDirection < 270 && objectDirection > 90) {
                            rotateObject(180 - objectDirection);
                        } else {
                            rotateObject(0 - objectDirection);
                        }
                    }
                    //Bottom left & bottom right corner
                    if (collisionState == 43 || collisionState == 81) {
                        if (objectDirection >= 270 || objectDirection <= 90) {
                            rotateObject(180 - objectDirection);
                        } else {
                            rotateObject(0 - objectDirection);
                        }
                    }
                }
            }
        }
    }

    public void update()
    {
        draw(window);
        shotForward();
        handleMapObjects();
    }
}

package ObjectComponents.TankComponents;

import ObjectComponents.MapObject;
import ObjectComponents.RotatingObject;
import Window.Window;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Hull extends RotatingObject {

    protected Window window;
    private float velocity;
    private float turningDistance;
    private ArrayList<MapObject> mapObjects;
    private RotatingObject ghost = new RotatingObject();

    private void moveForward()
    {
        xPos = (float) (xPos + (velocity * Math.sin(Math.toRadians(objectDirection))));
        yPos = (float) (yPos - (velocity * Math.cos(Math.toRadians(objectDirection))));
        setLocation(xPos, yPos);
    }

    private void moveBackward()
    {
        xPos = (float) (xPos - (velocity * Math.sin(Math.toRadians(objectDirection))));
        yPos = (float) (yPos + (velocity * Math.cos(Math.toRadians(objectDirection))));
        setLocation(xPos, yPos);
    }

    private void turnRight()
    {
        rotateObject(objectDirection + turningDistance);
    }

    private void turnLeft()
    {
        rotateObject(objectDirection - turningDistance);
    }

    private boolean handleCollision(String move)
    {
        boolean canMove = true;
        ghost.setSizeVector(this.getSize());
        ghost.setLocation(xPos, yPos);
        ghost.rotateObject(objectDirection);

        if(move.equals("forward")){
            float ghostxPos = (float) (xPos + (velocity * Math.sin(Math.toRadians(objectDirection))));
            float ghostyPos = (float) (yPos - (velocity * Math.cos(Math.toRadians(objectDirection))));
            ghost.setLocation(ghostxPos, ghostyPos);
        }
        if(move.equals("backward")){
            float ghostxPos = (float) (xPos - (velocity * Math.sin(Math.toRadians(objectDirection))));
            float ghostyPos = (float) (yPos + (velocity * Math.cos(Math.toRadians(objectDirection))));
            ghost.setLocation(ghostxPos, ghostyPos);
        }
        if(move.equals("left")){
            ghost.rotateObject(objectDirection - turningDistance);
        }
        if(move.equals("right")){
            ghost.rotateObject(objectDirection + turningDistance);
        }

        for(int i = 0; i < mapObjects.size(); i++)
        {
            MapObject mapObject = mapObjects.get(i);

            if(ghost.intersect(mapObject))
            {
                canMove = false;
            }
        }

        return canMove;
    }

    public void move(String move)
    {
        if(move.equals("forward")) {
            if(handleCollision("forward")){
                moveForward();
            }
        }
        if(move.equals("backward")) {
            if(handleCollision("backward")){
                moveBackward();
            }
        }
        if(move.equals("left")) {
            if(handleCollision("left")){
                turnLeft();
            }
        }
        if(move.equals("right")) {
            if(handleCollision("right")){
                turnRight();
            }
        }
    }

    public void setMapObjects(ArrayList<MapObject> mapObjects)
    {
        this.mapObjects = mapObjects;
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

    public void updateMapObjectList(ArrayList<MapObject> mapObjects)
    {
        this.mapObjects = mapObjects;
    }

}

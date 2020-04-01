package ObjectComponents;

import Window.Window;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class RotatingObject extends RectangleShape {

    protected float xPos, yPos;
    protected float width, height;
    protected float xCenter, yCenter;
    protected float objectDirection;
    protected Texture objectTexture;
    protected String texture;

    public void setObjectTexture(String texturePath)
    {
        texture = texturePath;
        Path imagePath = FileSystems.getDefault().getPath(texturePath);
        objectTexture = new Texture();
        try
        {
            objectTexture.loadFromFile(imagePath);
            objectTexture.setSmooth(true);
            setTexture(objectTexture);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Moves the centroid to the center of the object.
     */
    public void centerObject()
    {
        this.xCenter = getWidth() / 2;
        this.yCenter = getHeight() / 2;
        setOrigin(xCenter, yCenter);
    }

    /**
     * Rotates the object to a specific direction.
     * Direction is from 0 to 360 starting north.
     * @param objectDirection
     */
    public void rotateObject(float objectDirection)
    {
        centerObject();
        this.objectDirection = objectDirection;

        if(this.objectDirection >= 360)
        {
            this.objectDirection = this.objectDirection - 360;
        }

        else if(this.objectDirection < 0)
        {
            this.objectDirection = this.objectDirection + 360;
        }
        setRotation(objectDirection);
    }

    public void setLocation(float xPos, float yPos)
    {
        centerObject();
        this.xPos = xPos;
        this.yPos = yPos;
        setPosition(xPos, yPos);
    }

    public void setSize(float width, float height)
    {
        float scale[] = ObjectSizeHandler.scaleConstant();
        width = width * scale[0];
        height = height * scale[0];

        Vector2f size = new Vector2f(width, height);
        this.width = width;
        this.height = height;
        setSize(size);
        centerObject();
    }

    public void setSizeVector(Vector2f size)
    {
        this.width = size.x;
        this.height = size.y;
        setSize(size);
        centerObject();
    }

    public void draw(Window w)
    {
        w.draw(this);
    }

    public float getTextureWidth()
    {
        return getTexture().getSize().x;
    }

    public float getTextureHeight()
    {
        return getTexture().getSize().y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public float getxPos()
    {
        return xPos;
    }

    public float getyPos()
    {
        return yPos;
    }

    public float getCornerCoordinates(String corner, String type)
    {
        float cx, cy; //Center of square coordinates
        float x, y; //Coordinates of a corner point of a square
        float tempX, tempY;

        cx = getxPos();
        cy = getyPos();

        if(corner.equals("topleft"))
        {
            x = getxPos() - getWidth()/2;
            y = getyPos() - getHeight()/2;
        }

        else if(corner.equals("topright"))
        {
            x = getxPos() + getWidth()/2;
            y = getyPos() - getHeight()/2;
        }

        else if(corner.equals("bottomleft"))
        {
            x = getxPos() - getWidth()/2;
            y = getyPos() + getHeight()/2;
        }

        else if(corner.equals("bottomright"))
        {
            x = getxPos() + getWidth()/2;
            y = getyPos() + getHeight()/2;
        }

        else
        {
            x = 0;
            y = 0;
        }

        tempX = x - cx;
        tempY = y - cy;

        float rotatedX = (tempX * (float)Math.cos(Math.toRadians(objectDirection))) - (tempY * (float)Math.sin(Math.toRadians(objectDirection)));
        float rotatedY = (tempX * (float)Math.sin(Math.toRadians(objectDirection))) + (tempY * (float)Math.cos(Math.toRadians(objectDirection)));

        x = rotatedX + cx;
        y = rotatedY + cy;

        if(type.equals("x"))
        {
            return x;
        }

        else if(type.equals("y"))
        {
            return y;
        }

        else
        {
            return 0;
        }
    }

    /**
     * Returns an array holding the four lines of an object.
     * @return Line2D[top, bottom, left, right]
     */
    public Line2D[] getObjectBounds()
    {
        float x1, y1, x2, y2, x3, y3, x4, y4;

        x1 = this.getCornerCoordinates("topleft", "x");
        y1 = this.getCornerCoordinates("topleft", "y") * -1;
        x2 = this.getCornerCoordinates("topright", "x");
        y2 = this.getCornerCoordinates("topright", "y") * -1;
        x3 = this.getCornerCoordinates("bottomleft", "x");
        y3 = this.getCornerCoordinates("bottomleft", "y") * -1;
        x4 = this.getCornerCoordinates("bottomright", "x");
        y4 = this.getCornerCoordinates("bottomright", "y") * -1;

        //Lines of tank hull
        Line2D top = new Line2D.Float(x1, y1, x2, y2);
        Line2D bottom = new Line2D.Float(x3, y3, x4, y4);
        Line2D left = new Line2D.Float(x1, y1, x3, y3);
        Line2D right = new Line2D.Float(x2, y2, x4, y4);

        Line2D linesArray[] = new Line2D[4];
        linesArray[0] = top;
        linesArray[1] = bottom;
        linesArray[2] = left;
        linesArray[3] = right;

        return linesArray;
    }

    public boolean contains(float x, float y)
    {
        float topLeftCorner_x = getCornerCoordinates("topleft", "x");
        float topLeftCorner_y = getCornerCoordinates("topleft", "y");
        float bottomRightCorner_x = getCornerCoordinates("bottomright", "x");
        float bottomRightCorner_y = getCornerCoordinates("bottomright", "y");

        if(x >= topLeftCorner_x && x <= bottomRightCorner_x)
        {
            if(y <= bottomRightCorner_y && y >= topLeftCorner_y)
            {
                return true;
            }
        }
        return false;
    }

    public boolean intersect(RotatingObject object)
    {
        Line2D thisObject[] = getObjectBounds();
        Line2D thisTop = thisObject[0];
        Line2D thisBottom = thisObject[1];
        Line2D thisLeft = thisObject[2];
        Line2D thisRight = thisObject[3];

        Line2D thatObject[] = object.getObjectBounds();
        Line2D thatTop = thatObject[0];
        Line2D thatBottom = thatObject[1];
        Line2D thatLeft = thatObject[2];
        Line2D thatRight = thatObject[3];

        if (thisTop.intersectsLine(thatTop) || thisRight.intersectsLine(thatTop) || thisLeft.intersectsLine(thatTop) || thisBottom.intersectsLine(thatTop) ||
                thisTop.intersectsLine(thatRight) || thisRight.intersectsLine(thatRight) || thisLeft.intersectsLine(thatRight) || thisBottom.intersectsLine(thatRight) ||
                thisTop.intersectsLine(thatLeft) || thisRight.intersectsLine(thatLeft) || thisLeft.intersectsLine(thatLeft) || thisBottom.intersectsLine(thatLeft) ||
                thisTop.intersectsLine(thatBottom) || thisRight.intersectsLine(thatBottom) || thisLeft.intersectsLine(thatBottom) || thisBottom.intersectsLine(thatBottom))
        {
            return true;
        }
        else{
            return false;
        }
    }

    public String getTexturePath() { return texture; }
    public float getObjectDirection() { return objectDirection; }
}

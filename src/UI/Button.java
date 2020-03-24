package UI;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;

public class Button extends RotatingObject {

    protected Window window;

    private float xPos, yPos, width, height;
    private float xScale, yScale;
    private Texture buttonTexture;

    public Button(Window window, float x, float y, float width, float height)
    {
        this.window = window;
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setFillColor(Color.RED);
        setLocation(x, y);
    }

    public boolean contains(float x, float y)
    {
        float topLeftCorner_x = getCornerCoordinates("topleft", "x");
        float topLeftCorner_y = getCornerCoordinates("topleft", "y");
        float bottomRightCorner_x = getCornerCoordinates("bottomright", "x");
        float bottomRightCorner_y = getCornerCoordinates("bottomright", "y");

        if(x >= topLeftCorner_x && x <= bottomRightCorner_x)
        {
            if(y >= bottomRightCorner_y && y <= topLeftCorner_y)
            {
                return true;
            }
        }

        return false;
    }

    public void update()
    {
        draw(this.window);
    }


}

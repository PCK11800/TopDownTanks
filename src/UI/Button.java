package UI;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class Button extends RotatingObject {

    protected Window window;

    private float xPos, yPos, width, height;
    private float xScale, yScale;
    private Texture buttonTexture;
    private Color defaultColor = Color.GREEN;

    public Button(Window window, float x, float y, float width, float height)
    {
        this.window = window;
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setCenterLocation(x, y);
        setFillColor(Color.GREEN);
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

    private void handleHover()
    {
        Vector2i mousePos = Mouse.getPosition(window);
        System.out.println(mousePos.x + ", " + mousePos.y);
        if(contains(mousePos.x, mousePos.y)) {
            setFillColor(Color.RED);
        }
        else{
            setFillColor(Color.GREEN);
        }
    }

    public void update()
    {
        handleHover();
        draw(this.window);
    }
}

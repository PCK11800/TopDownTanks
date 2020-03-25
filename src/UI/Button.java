package UI;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class Button extends RotatingObject
{
    private float xPos, yPos, width, height;
    private float xScale, yScale;
    private Texture buttonTexture;
    private Color buttonColor = Color.GREEN; //Default color
    private Color outlineColor = Color.GREEN; //Default color
    private Text buttonText;

    public Button(float x, float y, float width, float height)
    {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setCenterLocation(x, y);
    }

    public void setColor(Color color)
    {
        this.buttonColor = color;
        setFillColor(color);
    }

    public void setOutline(Color color, int width)
    {
        this.outlineColor = color;
        setOutlineColor(color);
        setOutlineThickness(width);
    }

    private void handleHover(Window window)
    {
        Vector2i mousePos = Mouse.getPosition(window);
        if(contains(mousePos.x, mousePos.y)) {
            setFillColor(Color.RED);
        }
        else{
            setFillColor(buttonColor);
        }
    }

    public void addText(String text, float x, float y, int size, String font, Color color)
    {
        buttonText = new Text();
        buttonText.setPosition(xPos + x, yPos + y);
        buttonText.setFont(new Fonts(font));
        buttonText.setCharacterSize(size);
        buttonText.setColor(color);
        buttonText.setString(text);
    }

    public void update(Window window)
    {
        handleHover(window);
        draw(window);
        window.draw(buttonText);
    }
}

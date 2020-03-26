package UI.Components;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class Button extends RotatingObject
{
    private float xPos, yPos, width, height;
    private float textX, textY;
    private float panelX, panelY;
    private Texture buttonTexture;
    private Color buttonColor = Color.GREEN; //Default color
    private Color outlineColor = Color.GREEN; //Default color
    private Text buttonText;
    private Clock buttonPressClock = new Clock();
    private Runnable buttonAction;

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

    private void handleButtonPress(Window window)
    {
        if(buttonPressClock.getElapsedTime().asMilliseconds() >= 100)
        {
            Vector2i mousePos = Mouse.getPosition(window);
            if(contains(mousePos.x, mousePos.y)) {
                if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
                    buttonAction.run();
                    buttonPressClock.restart();
                }
            }
        }
    }

    public void setPressed(Runnable run)
    {
        buttonAction = run;
    }

    public void addText(String text, float x, float y, int size, String font, Color color)
    {
        buttonText = new Text();
        textX = x;
        textY = y;
        buttonText.setPosition(xPos + textX, yPos + textY);
        buttonText.setFont(new Fonts(font));
        buttonText.setCharacterSize(size);
        buttonText.setColor(color);
        buttonText.setString(text);
    }

    public void setPanelLocation(float x, float y)
    {
        panelX = x;
        panelY = y;
        xPos = xPos + x;
        yPos = yPos + y;
        setCenterLocation(xPos, yPos);
        buttonText.setPosition(xPos + textX, yPos + textY);
    }

    public void update(Window window)
    {
        handleHover(window);
        handleButtonPress(window);
        draw(window);
        window.draw(buttonText);
    }
}

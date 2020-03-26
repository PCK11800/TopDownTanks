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
    private float textX, textY;
    private float panelX, panelY;
    private Texture buttonTexture;
    private Color buttonColor = Color.GREEN; //Default color
    private Color buttonColorHover = Color.GREEN; //Default color
    private Color buttonColorClicked = Color.GREEN; //Default color
    private Color outlineColor = Color.GREEN; //Default color
    private Text buttonText = new Text();
    private Clock buttonPressClock = new Clock();
    private Runnable buttonAction;
    private boolean isVisible = false; //Default false

    public void setAllColor(Color color1, Color color2, Color color3)
    {
        this.buttonColor = color1;
        setFillColor(color1);

        this.buttonColorHover = color2;
        this.buttonColorClicked = color3;
    }

    public void setColor(Color color)
    {
        this.buttonColor = color;
        setFillColor(color);
    }

    public void setHoverColor(Color color)
    {
        this.buttonColorHover = color;
    }

    public void setClickedColor(Color color)
    {
        this.buttonColorClicked = color;
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
            setFillColor(buttonColorHover);
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
                    if(buttonAction != null){
                        buttonAction.run();
                    }
                    setFillColor(buttonColorClicked);
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
        setLocation(xPos, yPos);
        buttonText.setPosition((xPos - width/2) + textX, yPos + textY);
    }

    public void setVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public void update(Window window)
    {
        if(isVisible)
        {
            handleHover(window);
            handleButtonPress(window);
            draw(window);
            window.draw(buttonText);
        }
    }
}

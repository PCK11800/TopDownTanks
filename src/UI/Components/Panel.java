package UI.Components;

import ObjectComponents.RotatingObject;
import Window.Window;
import org.jsfml.graphics.Color;

import java.util.ArrayList;

public class Panel {

    protected Window window;
    private float x, y;
    private float width, height;
    private ArrayList<Button> panelButtons = new ArrayList<>();
    private RotatingObject outline = new RotatingObject();
    private boolean isVisible = false; //Default false

    public Panel(Window window)
    {
        this.window = window;
    }

    public void setLocation(float x, float y)
    {
        this.x = x;
        this.y = y;
        outline.setLocation(x, y);
    }

    public void setSize(float width, float height)
    {
        Color transparent = new Color(0, 0, 0);
        this.width = width;
        this.height = height;
        outline.setSize(width, height);
        outline.setFillColor(transparent);
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public void add(Button button)
    {
        button.setPanelLocation(x, y);
        panelButtons.add(button);
    }

    public void addOutline(Color color, int thickness)
    {
        outline.setOutlineColor(color);
        outline.setOutlineThickness(thickness);
    }

    public void setVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public void update()
    {
        if(isVisible){
            window.draw(outline);
            for(Button button : panelButtons)
            {
                button.update(window);
            }
        }
    }
}

package Window;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Window extends RenderWindow {

    private int screenWidth, screenHeight, frameRate, windowStyle;
    private String windowName;
    private Color backgroundColor;

    public Window(int screenWidth, int screenHeight, int frameRate, String windowName, int windowStyle)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.frameRate = frameRate;
        this.windowName = windowName;
        this.windowStyle = windowStyle;

        openWindow();
        setFramerateLimit(this.frameRate);
    }

    public void openWindow()
    {
        create(new VideoMode(screenWidth, screenHeight), windowName, windowStyle);
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void frameStart()
    {
        clear(backgroundColor);
    }

    public void frameEnd()
    {
        display();
        for (Event event : pollEvents())
        {
            switch(event.type)
            {
                case CLOSED:
                    close();
                    break;
            }
        }
    }

    public float[] getDimensions()
    {
        float[] dimensions = {screenWidth, screenHeight};
        return dimensions;
    }
}

package ObjectComponents;

import UI.Screens.LevelContainer;
import Window.Window;
import org.jsfml.graphics.Color;

public class MapObject extends RotatingObject{

    protected LevelContainer levelContainer;
    private Window window;

    public MapObject(LevelContainer levelContainer, float x, float y, float width, float height, float angle)
    {
        this.window = levelContainer.getWindow();
        setLocation(x, y);
        setSize(width, height);
        setFillColor(Color.WHITE);
        rotateObject(angle);
    }

    public void update()
    {
        draw(window);
    }
}

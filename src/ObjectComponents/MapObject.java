package ObjectComponents;

import UI.Screens.LevelContainer;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

public class MapObject extends RotatingObject{

    protected LevelContainer levelContainer;
    private Window window;

    public MapObject(LevelContainer levelContainer, float x, float y, float width, float height, float angle)
    {
        this.window = levelContainer.getWindow();
        setLocation(x, y);
        Vector2f sizeVect = new Vector2f(width, height);
        setSizeVector(sizeVect);
        setFillColor(Color.WHITE);
        setOutlineColor(Color.RED);
        setOutlineThickness(1);
        rotateObject(angle);
    }

    public void update()
    {
        draw(window);
    }
}

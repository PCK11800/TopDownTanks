package UI;

import Window.Window;
import org.jsfml.graphics.Color;

public class UITest {

    Panel panel;

    public UITest(Window window)
    {
        panel = new Panel(window);
        panel.add(new Button(100,100, 50, 50));
    }

    public void update()
    {
        panel.update();
    }

}

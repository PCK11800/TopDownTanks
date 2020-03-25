package UI;

import Window.Window;
import org.jsfml.graphics.Color;

public class UITest {

    Panel panel;

    public UITest(Window window)
    {
        Button testButton = new Button(100, 100, 50, 50);
        testButton.addText("Test", 0, 0, 20, Fonts.MONTSERRAT, Color.WHITE);
        testButton.setOutline(Color.BLUE, 5);
        panel = new Panel(window);
        panel.add(testButton);
    }

    public void update()
    {
        panel.update();
    }

}

package UI;

import UI.Components.Screen;
import UI.Screens.UITest;
import Window.Window;

import java.util.ArrayList;

public class ScreenHandler {

    private ArrayList<Screen> screenList = new ArrayList<>();
    protected Window window;

    public ScreenHandler(Window window)
    {
        this.window = window;
        iniHandler(window);
    }

    private void iniHandler(Window window)
    {
        screenList.add(new UITest(window));
    }

    public void update()
    {
        for(Screen screen : screenList)
        {
            screen.update();
        }
    }
}

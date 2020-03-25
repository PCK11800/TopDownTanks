package UI;

import Window.Window;

import java.util.ArrayList;
import java.util.Objects;

public class Panel {

    protected Window window;
    private ArrayList<Button> panelButtons = new ArrayList<>();

    public Panel(Window window)
    {
        this.window = window;
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public void add(Button button)
    {
        panelButtons.add(button);
    }

    public void update()
    {
        for(Button button : panelButtons)
        {
            button.update(window);
        }
    }
}

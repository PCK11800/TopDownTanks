package UI.Components;

import Window.Window;

import java.util.ArrayList;

public class Screen {

    protected Window window;
    protected ArrayList<Panel> panelList = new ArrayList<>();
    protected String name;

    public Screen(Window window, String name)
    {
        this.window = window;
        this.name = name;
    }

    public void add(Panel panel)
    {
        panelList.add(panel);
    }

    public String getName()
    {
        return name;
    }

    public void update()
    {
        for(Panel panel : panelList)
        {
            panel.update();
        }
    }
}

package UI.Components;

import Window.Window;

import java.util.ArrayList;

public class Screen {

    protected Window window;
    protected ArrayList<Panel> panelList = new ArrayList<>();

    public Screen(Window window){
        this.window = window;
    }

    public void add(Panel panel)
    {
        panelList.add(panel);
    }

    public void update()
    {
        for(Panel panel : panelList)
        {
            panel.update();
        }
    }
}

package UI.Screens;

import UI.Components.Button;
import UI.Components.Fonts;
import UI.Components.Panel;
import UI.Components.Screen;
import Window.Window;
import org.jsfml.graphics.Color;

public class PauseScreen extends Screen {

    Panel panel;
    Button button;

    public PauseScreen(Window window)
    {
        super(window, "Pause");
        inPauseScreen();
    }

    private void inPauseScreen()
    {
        panel = new Panel(window);
        panel.setVisible(true);
        panel.setLocation(200, 100);
        panel.setSize(100, 100);
        panel.addOutline(Color.BLUE, 1);

        button = new Button();
        button.setVisible(true);
        button.setSize(50, 50);
        button.setLocation(10, 10);
        button.setColor(Color.GREEN);
        button.setHoverColor(Color.RED);
        button.setClickedColor(Color.WHITE);
        button.addText("Hello world", 0, 0, 10, Fonts.MONTSERRAT, Color.YELLOW);
        button.setPressed(() ->
                window.close());

        panel.add(button);
        add(panel);
    }
}

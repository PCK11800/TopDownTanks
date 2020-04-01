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

        button = new Button();
        button.setVisible(true);
        button.setSize(200, 50);
        button.setLocation(10, 10);
        button.setColor(Color.WHITE);
        button.setHoverColor(Color.RED);
        button.setClickedColor(Color.WHITE);
        button.addText("EXIT GAME", 0, 0, 20, Fonts.MONTSERRAT, Color.BLACK);
        button.setPressed(() ->
                window.close());

        panel.add(button);
        add(panel);
    }
}

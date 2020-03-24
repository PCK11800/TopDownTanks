package UI;

import Window.Window;

public class UITest {

    Button redButton;

    public UITest(Window window)
    {
        redButton = new Button(window ,100, 100, 50, 50);
    }

    public void update()
    {
        redButton.update();
    }

}

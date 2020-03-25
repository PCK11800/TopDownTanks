package UI;

import Window.Window;
import org.jsfml.graphics.Color;

public class UITest {

    Panel panel;

    /*
        testButton.setPressed(new Runnable(){
            public void run(){
                System.out.println("Hello world!");
            }
        });

        AND

        testButton.setPressed(() -> System.out.println("Hello world!"));

        are the same.
     */

    public UITest(Window window)
    {
        panel = new Panel(window);
        panel.setSize(400, 400);
        panel.setLocation(100, 100);
        panel.addOutline(Color.BLUE, 1);

        Button testButton = new Button(50, 50, 50, 50);
        testButton.setPressed(() -> System.out.println("Hello world!"));
        testButton.addText("Test", -20, -10, 20, Fonts.MONTSERRAT, Color.WHITE);
        testButton.setOutline(Color.BLUE, 5);
        panel.add(testButton);
    }

    public void update()
    {
        panel.update();
    }

}

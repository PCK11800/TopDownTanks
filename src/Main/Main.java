package Main;

import UI.UITest;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;

import java.io.File;

public class Main {

    private Window window;
    private Clock frameClock = new Clock();

    UITest test;

    private void createWindow(int width, int height, int frameRate, String name, int windowType)
    {
        window = new Window(width, height, frameRate, name, windowType);
        window.setBackgroundColor(Color.WHITE);
    }

    private void initialize()
    {
        createWindow(1000, 1000, 120,"TopDownTanks", Window.DEFAULT);

        test = new UITest(window);
    }

    private void loop()
    {
        while(window.isOpen())
        {
            window.frameStart();
            //All update goes between frameStart and frameEnd
            test.update();
            window.frameEnd();
        }
    }

    public static void main(String[] args)
    {
        switch (OSChecker.getOS())
        {
            case LINUX:
                File lib = new File("src/Main/" + System.mapLibraryName("fixXInitThreads"));
                System.load(lib.getAbsolutePath());
                break;
        }

        Main main = new Main();
        main.initialize();
        main.loop();
    }
}

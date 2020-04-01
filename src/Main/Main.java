package Main;

import UI.ScreenHandler;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;
import org.jsfml.window.WindowStyle;

import java.awt.*;
import java.io.File;

public class Main {

    private Window window;
    private Clock frameClock = new Clock();

    ScreenHandler test;

    private void createWindow(int width, int height, int frameRate, String name, int windowType)
    {
        window = new Window(width, height, frameRate, name, windowType);
        window.setBackgroundColor(Color.BLACK);
    }

    private void initialize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        createWindow((int)screenSize.getWidth(), (int)screenSize.getHeight(), 120,"TopDownTanks", WindowStyle.FULLSCREEN);

        test = new ScreenHandler(window);
    }

    private void loop()
    {
        while(window.isOpen())
        {
            if(frameClock.getElapsedTime().asMilliseconds() >= 8.333333){
                window.frameStart();
                frameClock.restart();
                //All update goes between frameStart and frameEnd
                test.update();
                window.frameEnd();
            }
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

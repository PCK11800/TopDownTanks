package Main;

import Objects.Tank;
import UI.ScreenHandler;
import UI.Screens.UITest;
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
    Tank tank;

    private void createWindow(int width, int height, int frameRate, String name, int windowType)
    {
        window = new Window(width, height, frameRate, name, windowType);
        window.setBackgroundColor(Color.BLACK);
    }

    private void initialize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        createWindow((int)screenSize.getWidth(), (int)screenSize.getHeight(), 120,"TopDownTanks", WindowStyle.DEFAULT);

        test = new ScreenHandler(window);

        tank = new Tank();
        tank.setWindow(window);
        tank.setSize(100, 100);
        tank.setTankColor(Color.GREEN, Color.GREEN, true);
        tank.setLocation(100, 100);
        tank.setVelocity(4);
        tank.setTurningDistance(2);
        tank.setPlayerControlled();
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
                tank.update();
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

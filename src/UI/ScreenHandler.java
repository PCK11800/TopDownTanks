package UI;

import UI.Components.Screen;
import UI.Screens.LevelContainer;
import UI.Screens.PauseScreen;
import Window.Window;
import org.jsfml.system.Clock;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;

public class ScreenHandler {

    private ArrayList<Screen> screenList = new ArrayList<>();
    private LevelContainer levelContainer = new LevelContainer();
    protected Window window;
    private Clock pauseClock = new Clock();
    private boolean inGame = true; //Set this to true - becuz no main menu yet

    public ScreenHandler(Window window)
    {
        this.window = window;
        iniScreens(window);
        iniGame(window);
    }

    private void iniScreens(Window window)
    {
        screenList.add(new PauseScreen(window));
    }
    private void iniGame(Window window)
    {
        levelContainer.initialize(window);
    }

    private void handlePause()
    {
        if(Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
        {
            if(pauseClock.getElapsedTime().asMilliseconds() >= 100){
                if(inGame){ inGame = false; }
                else{ inGame = true; }
                pauseClock.restart();
            }
        }
        if(inGame == false)
        {
            for(Screen screen : screenList)
            {
                if(screen.getName().equals("Pause")){
                    screen.update();
                }
            }
        }
    }

    public void update()
    {
        handlePause();
        if(inGame){
            levelContainer.update();
        }
    }
}

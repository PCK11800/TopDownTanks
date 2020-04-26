package Objects;

import ObjectComponents.TankComponents.Hull;
import ObjectComponents.TankComponents.Shell;
import ObjectComponents.TankComponents.Turret;
import UI.Screens.LevelContainer;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

public class Tank {

    protected LevelContainer levelContainer;
    protected Hull hull;
    protected Turret turret;
    private boolean isPlayerControlled = false; //Default false

    private float xPos, yPos;
    private Color hullColor, turretColor, shellColor;

    private String shellTexture;
    private float shellSpeed, rateOfFire;
    private Clock fireClock = new Clock();

    public Tank()
    {
        this.hull = new Hull();
        this.turret = new Turret();
        turret.setHull(hull);
    }

    public void setSize(float width, float height)
    {
        width = width / 100;
        height = height / 100;
        hull.setSize(width * 53, height * 75);
        turret.setSize(width * 53, height * 75);
    }

    public void setTankColor(Color hullColor, Color turretColor)
    {
        this.hullColor = hullColor;
        this.turretColor = turretColor;
        hull.setFillColor(hullColor);
        turret.setFillColor(turretColor);
    }

    public void setOutline()
    {
        hull.setOutlineColor(Color.WHITE);
        turret.setOutlineColor(Color.WHITE);
        hull.setOutlineThickness(1);
        turret.setOutlineThickness(1);
    }

    public void setHullTexture(String texturePath)
    {
        hull.setObjectTexture(texturePath);
    }

    public void setTurretTexture(String texturePath)
    {
        turret.setObjectTexture(texturePath);
    }

    public void setLevelContainer(LevelContainer levelContainer)
    {
        this.levelContainer = levelContainer;
        hull.setWindow(levelContainer.getWindow());
        turret.setWindow(levelContainer.getWindow());
        hull.setMapObjects(levelContainer.getMapObjects());
    }

    public void setLocation(float x, float y)
    {
        xPos = x;
        yPos = y;
        hull.setLocation(x, y);
        turret.setTurretPosition();
    }

    public void setShellParameters(String shellTexture, float speed)
    {
        this.shellTexture = shellTexture;
        shellSpeed = speed;
    }

    public void setRateOfFire(float rateOfFire)
    {
        this.rateOfFire = rateOfFire;
    }

    private Shell createShell()
    {
        Shell shell = new Shell(levelContainer, turret, shellSpeed, 2);
        shell.setObjectTexture(shellTexture);
        shell.setSize(turret.getWidth()/10 , turret.getHeight()/5);
        return shell;
    }

    public void shoot()
    {
        levelContainer.getActiveShells().add(createShell());
    }

    private void playerControl()
    {
        turret.setTurretDirection_MouseControlled();
        if(Keyboard.isKeyPressed(Keyboard.Key.W)) { hull.move("forward"); }
        if(Keyboard.isKeyPressed(Keyboard.Key.A)) { hull.move("left"); }
        if(Keyboard.isKeyPressed(Keyboard.Key.S)) { hull.move("backward"); }
        if(Keyboard.isKeyPressed(Keyboard.Key.D)) { hull.move("right"); }

        if(fireClock.getElapsedTime().asMilliseconds() >= rateOfFire)
        {
            if(Mouse.isButtonPressed(Mouse.Button.LEFT))
            {
                shoot();
                fireClock.restart();
            }
        }
    }

    public void rotateTurret(float turretDirection)
    {
        turret.rotateObject(turretDirection);
    }

    public void move(String movement)
    {
        hull.move(movement);
    }

    public void setTurningDistance(float turningDistance)
    {
        hull.setTurningDistance(turningDistance);
    }

    public void setVelocity(float velocity)
    {
        hull.setVelocity(velocity);
    }

    public void setPlayerControlled()
    {
        isPlayerControlled = true;
    }

    public void update()
    {
        hull.updateMapObjectList(levelContainer.getMapObjects());
        if(isPlayerControlled) { playerControl(); }
        hull.update();
        turret.update();
    }

}

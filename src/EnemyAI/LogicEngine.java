package EnemyAI;

import Objects.Tank;

public class LogicEngine {

    private Tank player;
    private Tank ai;
    private int i = 0;

    public LogicEngine(Tank player, Tank ai)
    {
        this.player = player;
        this.ai = ai;
    }

    public void moveTest()
    {
        ai.rotateTurret(i);
        ai.move("backward");
        i++;
    }

    public void update()
    {
        moveTest();
        ai.update();
    }
}

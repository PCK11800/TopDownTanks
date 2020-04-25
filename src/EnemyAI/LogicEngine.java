package EnemyAI;

import Objects.Tank;

public class LogicEngine {

    private Tank player;
    private Tank ai;

    public LogicEngine(Tank player, Tank ai)
    {
        this.player = player;
        this.ai = ai;
    }

    public void moveTest()
    {
        ai.move("backward");
    }

    public void update()
    {
        moveTest();
        ai.update();
    }
}

package EnemyAI;

import ObjectComponents.PathTile;
import Objects.Tank;
import org.jsfml.graphics.Color;

import java.util.ArrayList;

public class LogicEngine {

    private Tank player;
    private Tank ai;
    private ArrayList<PathTile> pathTiles;
    private int i = 0;
    private boolean doneMoving, doneTurning = false;
    private ArrayList<PathTile> targetTileList = new ArrayList<>();

    public LogicEngine(Tank player, Tank ai, ArrayList<PathTile> pathTiles)
    {
        this.player = player;
        this.ai = ai;
        this.pathTiles = pathTiles;
    }

    private void moveTest()
    {
        moveToTile(pathTiles.get(5000));
        moveToTile(pathTiles.get(1000));
    }

    private void moveToTile(PathTile targetTile)
    {
        targetTileList.add(targetTile);
    }


    private void moveToTileUpdate()
    {
        if(targetTileList.size() >= 1)
        {
            PathTile currentTargetTile = targetTileList.get(0);

            float current_xPos = ai.getxPos();
            float current_yPos = ai.getyPos();

            float target_xPos = currentTargetTile.getxPos();
            float target_yPos = currentTargetTile.getyPos();

            float direction = 90 -(float) Math.toDegrees(Math.atan2((current_yPos - target_yPos), target_xPos - current_xPos));

            if(direction < 0) {
                direction = 360 + direction;
            }

            if(direction > 360){
                direction = direction - 360;
            }

            if(!doneTurning){ turn(direction); }
            if(!doneMoving && doneTurning) { move(currentTargetTile); }

            if(doneMoving && doneTurning){
                targetTileList.remove(0);
                targetTileList.trimToSize();

                doneTurning = false;
                doneMoving = false;
            }
        }
    }

    private void turn(float direction)
    {
        if(!((ai.getHullDirection() >= direction - 1) && (ai.getHullDirection() <= direction + 1)))
        {
            ai.move("left");
        }
        else
        {
            ai.setHullDirection(direction);
            doneTurning = true;
        }
    }

    private void move(PathTile currentTargetTile)
    {
        if(!currentTargetTile.contains(ai.getxPos(), ai.getyPos())){
            ai.move("forward");
        }
        else{
            ai.setLocation(currentTargetTile.getxPos(), currentTargetTile.getyPos());
            doneMoving = true;
        }
    }

    public void update()
    {
        //moveTest();
        moveToTileUpdate();
        ai.update();
    }
}

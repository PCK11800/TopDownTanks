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

    public LogicEngine(Tank player, Tank ai, ArrayList<PathTile> pathTiles)
    {
        this.player = player;
        this.ai = ai;
        this.pathTiles = pathTiles;
    }

    public void moveTest()
    {
        ai.rotateTurret(i);
        ai.move("backward");
        i++;
    }

    public void moveToTile(PathTile targetTile)
    {
        float current_xPos = ai.getxPos();
        float current_yPos = ai.getyPos();

        float target_xPos = targetTile.getxPos();
        float target_yPos = targetTile.getyPos();

        float direction = 90 -(float) Math.toDegrees(Math.atan2((current_yPos - target_yPos), target_xPos - current_xPos));

        if(direction < 0) {
            direction = 360 + direction;
        }

        if(direction > 360){
            direction = direction - 360;
        }


        //Turn
        if(!((ai.getHullDirection() >= direction - 1) && (ai.getHullDirection() <= direction + 1)))
        {
            ai.move("left");
        }
        else{
            ai.setHullDirection(direction);

            if(current_xPos != target_xPos && current_yPos != target_yPos){
                ai.move("forward");
            }
        }



        System.out.println(direction + ", " + ai.getHullDirection());
        System.out.println(current_xPos + ", " + current_yPos);
        System.out.println(target_xPos + ", " + target_yPos);
    }

    public void update()
    {
        //moveTest();
        moveToTile(pathTiles.get(10));
        pathTiles.get(10).setFillColor(Color.RED);
        ai.update();
    }
}

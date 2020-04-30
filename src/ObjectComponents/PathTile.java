package ObjectComponents;

import UI.Screens.LevelContainer;
import Window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

public class PathTile extends RotatingObject{

    protected LevelContainer levelContainer;
    private Window window;

    public float costFromStart;
    public float estimatedCostToGoal;
    public PathTile pathParent;
    public ArrayList<PathTile> neighbors = new ArrayList<>();
    public String uniqueID;
    public boolean valid = true;

    public PathTile(LevelContainer levelContainer, float x, float y, float width, float height)
    {
        this.window = levelContainer.getWindow();
        setLocation(x, y);
        Vector2f sizeVect = new Vector2f(width, height);
        setSizeVector(sizeVect);
        setFillColor(Color.GREEN);
        setOutlineColor(Color.WHITE);
        setOutlineThickness(1);
        uniqueID = UUID.randomUUID().toString();
    }

    public float getCost()
    {
        return costFromStart + estimatedCostToGoal;
    }

    public int compareTo(PathTile otherTile)
    {
        float thisValue = this.getCost();
        float otherValue = otherTile.getCost();

        float v = thisValue - otherValue;

        if(v > 0){ return 1; }
        if(v < 0) { return -1; }
        else {
            return 0;
        }
    }

    public float getCost(PathTile tile){
        float thisTileCost = getCost();
        float thatTileCost = tile.getCost();

        return Math.abs(thatTileCost - thisTileCost);
    }

    public float getEstimatedCost(PathTile tile){
        float thisTileCost = estimatedCostToGoal;
        float thatTileCost = tile.estimatedCostToGoal;

        return Math.abs(thatTileCost - thisTileCost);
    }

    public ArrayList<PathTile> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(PathTile neighbor) {
        neighbors.add(neighbor);
    }

    public void update(){
        draw(window);
    }
}

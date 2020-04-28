package EnemyAI;

import ObjectComponents.PathTile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AStarSearch {

    public static class PriorityList extends LinkedList {

        public void add(PathTile tile)
        {
            for(int i = 0; i < size(); i++)
            {
                if(tile.compareTo((PathTile)get(i)) <= 0)
                {
                    add(i, tile);
                    return;
                }
            }
            addLast(tile);
        }
    }

    protected List constructPath(PathTile tile)
    {
        LinkedList path = new LinkedList();
        while(tile.pathParent != null){
            path.addFirst(tile);
            tile = tile.pathParent;
        }
        return path;
    }

    public List findPath(PathTile startTile, PathTile goalTile){
        PriorityList openList = new PriorityList();
        LinkedList closedList = new LinkedList();

        startTile.costFromStart = 0;
        startTile.estimatedCostToGoal = startTile.getEstimatedCost(goalTile);
        startTile.pathParent = null;
        openList.add(startTile);

        while(!openList.isEmpty()){
            PathTile tile = (PathTile)openList.removeFirst();
            if(tile == goalTile) {
                return constructPath(goalTile);
            }

            ArrayList<PathTile> neighbors = tile.getNeighbors();
            for(int i = 0; i < neighbors.size(); i++)
            {
                PathTile neighborTile = neighbors.get(i);
                boolean isOpen = openList.contains(neighborTile);
                boolean isClosed = closedList.contains(neighborTile);
                float costFromStart = tile.costFromStart + tile.getCost(neighborTile);

                if((!isOpen && !isClosed) || costFromStart < neighborTile.costFromStart)
                {
                    neighborTile.pathParent = tile;
                    neighborTile.costFromStart = costFromStart;
                    neighborTile.getEstimatedCost(goalTile);
                    if(isClosed) {
                        closedList.remove(neighborTile);
                    }
                    if(!isOpen) {
                        openList.add(neighborTile);
                    }
                }
            }
            closedList.add(tile);
        }

        return null;
    }
}

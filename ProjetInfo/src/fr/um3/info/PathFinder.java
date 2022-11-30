package fr.um3.info;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    Panel gp;
    Node[][] nodes;
    List<Node> openList = new ArrayList<>();
    public List<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    boolean print =true;

    public PathFinder(Panel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {

        nodes = new Node[Panel.NUMBER_COLS_ROWS][Panel.NUMBER_COLS_ROWS];
        int col = 0;
        int row = 0;
        while (col < Panel.NUMBER_COLS_ROWS && row < Panel.NUMBER_COLS_ROWS) {

            nodes[row][col] = new Node(row, col);
            col++;
            if (col == Panel.NUMBER_COLS_ROWS) {
                col = 0;
                row++;
            }

        }
    }

    public void resetNodes() {

        int col = 0;
        int row = 0;
        while (col < Panel.NUMBER_COLS_ROWS && row < Panel.NUMBER_COLS_ROWS) {

            nodes[row][col].open = false;
            nodes[row][col].checked = false;
            nodes[row][col].solid = false;
            col++;
            if (col == Panel.NUMBER_COLS_ROWS) {
                col = 0;
                row++;
            }

        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public synchronized void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        startNode = nodes[startRow][startCol];
        currentNode = startNode;
        goalNode = nodes[goalRow][goalCol];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        while (col < Panel.NUMBER_COLS_ROWS && row < Panel.NUMBER_COLS_ROWS) {

            nodes[row][col].solid = gp.entites[row][col].collision;

            setCost(nodes[row][col]);
            col++;
            if (col == Panel.NUMBER_COLS_ROWS) {
                col = 0;
                row++;
            }

        }

    }

    private void setCost(Node node) {

        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        //node Fcost
        node.fCost = node.gCost + node.hCost;

    }

    public synchronized boolean search() {

        while (!goalReached && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open up node
            if (row - 1 >= 0) {
                openNode(nodes[row - 1][col]);
            }
            //open left node
            if (col - 1 >= 0) {
                openNode(nodes[row][col - 1]);
            }

            //open down node
            if (row + 1 < Panel.NUMBER_COLS_ROWS) {
                openNode(nodes[row + 1][col]);
            }

            //open right node
            if (col + 1 < Panel.NUMBER_COLS_ROWS) {
                openNode(nodes[row][col + 1]);
            }

            //Find the best node
            int bestNodeIndex = 0;
            int bestNodefcost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefcost) {
                    bestNodeIndex = i;
                    bestNodefcost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefcost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }


                }
            }

            // if there is no node in the openlist ,end loop
            if(openList.size()==0){
                break;
            }

            currentNode=openList.get(bestNodeIndex);

            if(currentNode==goalNode){
                goalReached=true;
                trackThePath();
            }
            step++;



        }
        return goalReached;
    }

    private void trackThePath() {
        Node current=goalNode;
        while(current!=startNode){
            //always adding to the first slot so that the last added node is in the [0]
            pathList.add(0,current);
            current=current.parent;


        }
    }

    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);

        }
    }

}

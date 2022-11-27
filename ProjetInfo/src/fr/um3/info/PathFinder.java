package fr.um3.info;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    Panel gp;
    Node [][]nodes;
    List<Node> openList =new ArrayList<>();
    public List<Node> pathList =new ArrayList<>();
    Node startNode,goalNode,currentNode;
    boolean goalReached=false;
    int step=0;

    public PathFinder(Panel gp){
        this.gp=gp;
    }
    public void instantiateNodes(){

        nodes=new Node[Panel.NUMBER_COLS_ROWS][Panel.NUMBER_COLS_ROWS];
        int col=0;
        int row=0;
        while(col<Panel.NUMBER_COLS_ROWS && row<Panel.NUMBER_COLS_ROWS){

            nodes[row][col]=new Node(row,col);
            col++;
            if(col==Panel.NUMBER_COLS_ROWS){
                col=0;
                row++;
            }

        }
    }

    public void resetNodes(){

        int col=0;
        int row=0;
        while(col<Panel.NUMBER_COLS_ROWS && row<Panel.NUMBER_COLS_ROWS){

            nodes[row][col].open=false;
            nodes[row][col].checked=false;
            nodes[row][col].solid=false;
            col++;
            if(col==Panel.NUMBER_COLS_ROWS){
                col=0;
                row++;
            }

        }

        openList.clear();
        pathList.clear();
        goalReached=false;
        step=0;
    }

    public void setNodes(){}

}

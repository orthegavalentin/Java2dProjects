package fr.um3.info;

public class Node {
    Node parent;
    private int col;
    private int row;
    int gCost;
    int hCost;
    int fCost;
    boolean checked;
    boolean open;
    boolean solid;

    public Node(int col,int row){
        this.row=row;
        this.col=col;
    }


}

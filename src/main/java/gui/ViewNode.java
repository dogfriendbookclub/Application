package gui;
import javafx.scene.Node;


public class ViewNode {
    Node view;
    int level;

    ViewNode(Node view, int level){
        this.view = view;
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }


    public Node getView(){
        return this.view;
    }
}

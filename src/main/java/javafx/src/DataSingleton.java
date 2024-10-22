package javafx.src;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    private String searchString;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getSearchString(){
        return searchString;
    }

    public void setSearchString(String searchString){
        this.searchString = searchString;
    }


}

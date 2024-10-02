package edu.metrostate.gui.studentlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentListController implements Initializable, StudentListViewCell.StudentDeleteClickedListener {

    @FXML
    private ListView<Student> listView;

    private ObservableList<Student> studentObservableList;

    public StudentListController()  {

        studentObservableList = FXCollections.observableArrayList();
        //add some Students
        studentObservableList.addAll(
                new Student(1,"John Doe"),
                new Student(2,"Jane Doe"),
                new Student(3, "Donte Dunigan"),
                new Student(4,"Gavin Genna"),
                new Student(5,"Darin Dear"),
                new Student(6,"Pura Petty"),
                new Student(7,"Herma Hines")
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(studentObservableList);
        listView.setCellFactory(studentListView -> new StudentListViewCell(this));
    }

    @FXML
    public void handleClick(MouseEvent event) {
        Student student = listView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student Selected");
        alert.setHeaderText(null);
        alert.setContentText(student.name());
        alert.showAndWait();
    }

    @Override
    public void onStudentDeleteClicked(Student student) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Delete Student");
        alert.setHeaderText("Are you sure you want to delete " + student.name());
        alert.showAndWait()
             .filter(response -> response == ButtonType.OK)
             .ifPresent(response -> studentObservableList.remove(student));
    }
}
package edu.metrostate.gui.studentlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StudentListViewCell extends ListCell<Student> {

    public interface StudentDeleteClickedListener {
        public void onStudentDeleteClicked(Student student);
    }

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button button;

    private FXMLLoader mLLoader;

    private StudentDeleteClickedListener deleteClickedListener;

    public StudentListViewCell(StudentDeleteClickedListener deleteClickedListener) {
        this.deleteClickedListener = deleteClickedListener;
    }

    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);
        setBackground(null);
        if(empty || student == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/edu/metrostate/gui/fxml/studentlist/ListCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(String.valueOf(student.id()));
            label2.setText(student.name());
            button.setOnAction(actionEvent -> {
                if (deleteClickedListener != null) {
                    deleteClickedListener.onStudentDeleteClicked(student);
                }
            });

            setText(null);
            setGraphic(gridPane);
        }

    }
}

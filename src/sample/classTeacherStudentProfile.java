package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static sample.bluePrint.studentInformationForClassTeacher;

public class classTeacherStudentProfile {
    TableView<StudentProfiles> classTeacherStudentProfileTable;
    TextField nameIn, priceIn, quantityIn;

    public GridPane studentInformation (String classSection)throws Exception
    {
        String [] classSec = classSection.split("_");
        int clas=Integer.parseInt(classSec[1]);
        String section = classSec[classSec.length-1];
        //Roll Column
        TableColumn<StudentProfiles, Integer> sRoll=new TableColumn<>("Roll");
        sRoll.setMaxWidth(220);
        sRoll.setCellValueFactory(new PropertyValueFactory<StudentProfiles, Integer>("roll"));
        sRoll.setStyle("-fx-alignment: CENTER;");

        //Name Column
        TableColumn<StudentProfiles, String> sName=new TableColumn<>("Name");
        sName.setMaxWidth(220);
        sName.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("name"));

        //Class Column
        TableColumn<StudentProfiles, Integer> sClass=new TableColumn<>("Class");
        sClass.setMaxWidth(220);
        sClass.setCellValueFactory(new PropertyValueFactory<StudentProfiles, Integer>("clas"));

        //Section Column
        TableColumn<StudentProfiles, String> sSection=new TableColumn<>("Section");
        sSection.setMaxWidth(220);
        sSection.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("section"));
        sSection.setStyle("-fx-alignment: CENTER;");

        //Father's Name Column
        TableColumn<StudentProfiles, String> sFName=new TableColumn<>("Father's Name");
        sFName.setMaxWidth(220);
        sFName.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("fName"));

        //Mother's Name Column
        TableColumn<StudentProfiles, String> sMName=new TableColumn<>("Mother's Name");
        sMName.setMaxWidth(220);
        sMName.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("mName"));

        //Group Column
        TableColumn<StudentProfiles, String> sGroup=new TableColumn<>("Group");
        sGroup.setMaxWidth(220);
        sGroup.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("group"));

        //Birth Date Column
        TableColumn<StudentProfiles, String> sBirthDate=new TableColumn<>("Birth Date");
        sBirthDate.setMaxWidth(220);
        sBirthDate.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("birthDate"));

        //Address Column
        TableColumn<StudentProfiles, String> sAddress=new TableColumn<>("Address");
        sAddress.setMaxWidth(220);
        sAddress.setCellValueFactory(new PropertyValueFactory<StudentProfiles, String>("address"));

        classTeacherStudentProfileTable=new TableView<>(getStudentInformation(clas, section));

        classTeacherStudentProfileTable.getColumns().addAll(sRoll, sName, sClass, sSection, sFName, sMName, sGroup, sBirthDate, sAddress);

        GridPane gridPane=new GridPane();
        gridPane.getChildren().addAll(classTeacherStudentProfileTable);

        return gridPane;
    }

    private ObservableList<StudentProfiles> getStudentInformation(int clas, String section)
    {
        ObservableList<StudentProfiles> std_info= FXCollections.observableArrayList();
        String [][] studentInformation=studentInformationForClassTeacher(clas, section);

        for(int i=0; i<studentInformation.length; i++) {

            int roll=Integer.parseInt(studentInformation[i][2]);
            int clas1=Integer.parseInt(studentInformation[i][5]);

            std_info.add(new StudentProfiles(roll, studentInformation[i][1], clas1, studentInformation[i][6],
                    studentInformation[i][3], studentInformation[i][4], studentInformation[i][7], studentInformation[i][8],
                    studentInformation[i][9]));
        }
        /*std_info.add(new genericSubject(1, "Mohibul Hasan Choudhury", 20, 66, 28));*/

        return std_info;
    }
}

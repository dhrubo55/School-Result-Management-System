package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.bluePrint.*;

/**
 * Created by Murshid on 7/14/2017.
 */
public class subjectTeacherTableView {

    private static TableView<genericSubject> subjectTeacherViewTable;
    private static GridPane gridPane;
    private static GridPane gp;
    private static int [] studentResult;
    private static  int subjective, objective, practical, monthly, subTotal, total, roll;
    private  static String CSS;

    public static GridPane run (String classSectionSubject)throws Exception
    {
        CSS=classSectionSubject;
        String [] string=classSectionSubject.split("_");
        String  section, subject;
        int clas=Integer.parseInt(string[1]);
        section=string[3];
        subject=string[4];



        //Roll Column
        TableColumn<genericSubject, Integer> sRoll=new TableColumn<>("roll");
        sRoll.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("roll"));
        sRoll.setStyle("-fx-alignment: CENTER;");

        //Name Column
        TableColumn<genericSubject, String> sName=new TableColumn<>("Name");
        sName.setMaxWidth(220);
        sName.setCellValueFactory(new PropertyValueFactory<genericSubject,String>("name"));

        //Section Column
        TableColumn<genericSubject, String> sSection=new TableColumn<>("Section");
        sSection.setMaxWidth(220);
        sSection.setCellValueFactory(new PropertyValueFactory<genericSubject,String>("section"));

        //Mid Subjective Column
        TableColumn<genericSubject, Integer> sMidSubjective=new TableColumn<>("Subjective");
//        sMidSubjective.setMaxWidth(220);
        sMidSubjective.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midSubjective"));

        //Mid Objective Column
        TableColumn<genericSubject, Integer> sMidObjective=new TableColumn<>("Objective");
//        sMidObjective.setMaxWidth(220);
        sMidObjective.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midObjective"));

        //Mid Practical Column
        TableColumn<genericSubject, Integer> sMidPractical=new TableColumn<>("Practical");
//        sMidPractical.setMaxWidth(220);
        sMidPractical.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midPractical"));

        //Mid SubTotal Column
        TableColumn<genericSubject, Integer> sMidSubTotal=new TableColumn<>("Sub Total");
//        sMidSubTotal.setMaxWidth(220);
        sMidSubTotal.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midSubTotal"));

        //Mid ME Column
        TableColumn<genericSubject, Integer> sMidME=new TableColumn<>("ME");
        sMidME.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midMonthly"));

        //Mid Total Column
        TableColumn<genericSubject, Integer> sMidTotal=new TableColumn<>("Total");
//        sMidTotal.setMaxWidth(220);
        sMidTotal.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("midTotal"));

        //Final Subjective Column
        TableColumn<genericSubject, Integer> sFinalSubjective=new TableColumn<>("Subjective");
        sFinalSubjective.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalSubjective"));

        //Final Objective Column
        TableColumn<genericSubject, Integer> sFinalObjective=new TableColumn<>("Objective");
        sFinalObjective.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalObjective"));

        //Final Practical Column
        TableColumn<genericSubject, Integer> sFinalPractical=new TableColumn<>("Practical");
        sFinalPractical.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalPractical"));

        //Final Sub Total Column
        TableColumn<genericSubject, Integer> sFinalSubTotal=new TableColumn<>("Sub Total");
        sFinalSubTotal.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalSubTotal"));

        //Final Monthly Column
        TableColumn<genericSubject, Integer> sFinalME=new TableColumn<>("ME");
        sFinalME.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalMonthly"));

        //Final Total Column
        TableColumn<genericSubject, Integer> sFinalTotal=new TableColumn<>("Total");
        sFinalTotal.setCellValueFactory(new PropertyValueFactory<genericSubject,Integer>("finalTotal"));

        /*TableColumn mid=new TableColumn("Mid Term");
        TableColumn finalExam=new TableColumn("Final");

        mid.getColumns().addAll(sMidSubjective, sMidObjective, sMidPractical, sMidSubTotal, sMidME, sMidTotal);
        finalExam.getColumns().addAll(sFinalSubjective, sFinalObjective,sFinalPractical, sFinalSubTotal, sFinalME, sFinalSubTotal);*/


        subjectTeacherViewTable=new TableView<>(getStudentInformation(classSectionSubject));
        GridPane.setConstraints(subjectTeacherViewTable,0,0);

        gp=new GridPane();
        gp.setPadding(new Insets(50, 50, 50, 50));
        gp.setVgap(8);
        gp.setHgap(12);
        GridPane.setConstraints(gp, 0,1);



        /*//Name Label
        Label nameLabel = new Label("Name ");
        gp.setConstraints(nameLabel, 0, 0);*/

        /*//Show Name Label
        Label showNameLabel = new Label("Name ");
        gp.setConstraints(showNameLabel, 1, 0);
        showNameLabel.setAlignment(Pos.BASELINE_LEFT);*/

        //Section Label
        Label sectionLabel = new Label("Section :   "+section);
        gp.setConstraints(sectionLabel, 2, 0);

        //Roll Label
        Label rollLabel = new Label("Roll ");
        gp.setConstraints(rollLabel, 4, 0);

        //Roll Input
        TextField rollIn=new TextField();
        rollIn.setPromptText("Enter Roll Number");
        gp.setConstraints(rollIn, 6, 0);

        //Mid Term Label
        Label midLabel = new Label("Mid Term ");
        gp.setConstraints(midLabel, 0, 1);

        //Subjective Label
        Label midSubjectiveLabel = new Label("Subjective ");
        gp.setConstraints(midSubjectiveLabel, 0, 2);

        //Mid Objective Label
        Label midObjectiveLabel = new Label("Objective ");
        gp.setConstraints(midObjectiveLabel, 2, 2);

        //Mid Practical Label
        Label midPracticalLabel = new Label("Practical ");
        gp.setConstraints(midPracticalLabel, 4, 2);

        //Mid ME Label
        Label midMELabel = new Label("ME ");
        gp.setConstraints(midMELabel, 6, 2);

        //Mid Subjective input
        TextField midSubjectiveIn = new TextField();
        midSubjectiveIn.setPromptText("Subjective Mark");
        gp.setConstraints(midSubjectiveIn, 0, 3);

        //Mid Objective input
        TextField midObjectiveIn = new TextField();
        midObjectiveIn.setPromptText("Objective Mark");
        gp.setConstraints(midObjectiveIn, 2, 3);

        //Mid Practical input
        TextField midPracticalIn = new TextField();
        midPracticalIn.setPromptText("Practical Mark");
        gp.setConstraints(midPracticalIn, 4, 3);

        //Mid ME input
        TextField midMEIn = new TextField();
        midMEIn.setPromptText("ME Mark");
        gp.setConstraints(midMEIn, 6, 3);

        //Mid Save Button
        Button midSave=new Button("SAVE");
        gp.setConstraints(midSave, 8, 3);

        //Final Label
        Label finalLabel=new Label("Final");
        gp.setConstraints(finalLabel, 0, 4);

        //Final Subjective Label
        Label finalSubjectiveLabel = new Label("Subjective ");
        gp.setConstraints(finalSubjectiveLabel, 0, 5);

        //Final Objective Label
        Label finalObjectiveLabel = new Label("Objective ");
        gp.setConstraints(finalObjectiveLabel, 2, 5);

        //Final Practical Label
        Label finalPracticalLabel = new Label("Practical ");
        gp.setConstraints(finalPracticalLabel, 4, 5);

        //Final ME Label
        Label finalMELabel = new Label("ME ");
        gp.setConstraints(finalMELabel, 6, 5);

        //Final Subjective input
        TextField finalSubjectiveIn = new TextField();
        finalSubjectiveIn.setPromptText("Subjective Mark");
        gp.setConstraints(finalSubjectiveIn, 0, 6);

        //Final Objective input
        TextField finalObjectiveIn = new TextField();
        finalObjectiveIn.setPromptText("Objective Mark");
        gp.setConstraints(finalObjectiveIn, 2, 6);

        //Practical input
        TextField finalPracticalIn = new TextField();
        finalPracticalIn.setPromptText("Practical Mark");
        gp.setConstraints(finalPracticalIn, 4, 6);

        //Final ME input
        TextField finalMEIn = new TextField();
        finalMEIn.setPromptText("ME Mark");
        gp.setConstraints(finalMEIn, 6, 6);

        //Final Save Button
        Button finalSave=new Button("SAVE");
        gp.setConstraints(finalSave, 8, 6);


        double [] subSettings=subjectSettings(clas,subject);
        final double subjecPercent=subSettings[0], objectivePercent=subSettings[1], practicalPercent=subSettings[2],
                monthlyPercent=subSettings[4], subTotalPercent=subSettings[3];
        subjective=0; objective=0; practical=0; monthly=0;

        //Edit Button
        Button edit=new Button("EDIT");
        gp.setConstraints(edit, 8,0);
        edit.setStyle("-fx-base: #e96e6e;");
        edit.setOnAction(e->{
            if(isStudentExistSubjectTable(clas, section, subject, Integer.parseInt(rollIn.getText())))
            {
                edit.setStyle("-fx-base: #a8d877;");
                studentResult=studentSubjectResultDetails(clas, section, subject, Integer.parseInt(rollIn.getText()));
                if(studentResult!=null){
                    midSubjectiveIn.setText(""+studentResult[0]);
                    midObjectiveIn.setText(""+studentResult[1]);
                    midPracticalIn.setText(""+studentResult[2]);
                    midMEIn.setText(""+studentResult[3]);
                    finalSubjectiveIn.setText(""+studentResult[6]);
                    finalObjectiveIn.setText(""+studentResult[7]);
                    finalPracticalIn.setText(""+studentResult[8]);
                    finalMEIn.setText(""+studentResult[9]);



                    gp.getChildren().addAll(midSubjectiveLabel, midObjectiveLabel, midPracticalLabel, midMELabel);
                    gp.getChildren().addAll(midSubjectiveIn, midObjectiveIn, midPracticalIn, midMEIn, midSave, finalLabel);
                    gp.getChildren().addAll(finalSubjectiveLabel, finalObjectiveLabel, finalPracticalLabel, finalMELabel);
                    gp.getChildren().addAll(finalSubjectiveIn, finalObjectiveIn, finalPracticalIn, finalMEIn, finalSave);
                }
            }
            else
                alart("You have entered a roll number which don't belong to this subject.");
        });

        //Mid Save Button set on action
        midSave.setOnAction(e->{
            if(subjecPercent>0)
                subjective=Integer.parseInt(midSubjectiveIn.getText());
            if(objectivePercent>0)
                objective=Integer.parseInt(midObjectiveIn.getText());
            if(practicalPercent>0)
                practical=Integer.parseInt(midPracticalIn.getText());
            subTotal=(int)((subjective+objective+practical)*subTotalPercent);
            monthly=Integer.parseInt(midMEIn.getText());
            total=subTotal+monthly;
            roll=Integer.parseInt(rollIn.getText());
            if(updateStudentResultMid(clas, section, subject, roll,subjective, objective, practical, monthly, subTotal, total)){
                gp.getChildren().removeAll(midSubjectiveLabel, midObjectiveLabel, midPracticalLabel, midMELabel);
                gp.getChildren().removeAll(midSubjectiveIn, midObjectiveIn, midPracticalIn, midMEIn, midSave, finalLabel);
                gp.getChildren().removeAll(finalSubjectiveLabel, finalObjectiveLabel, finalPracticalLabel, finalMELabel);
                gp.getChildren().removeAll(finalSubjectiveIn, finalObjectiveIn, finalPracticalIn, finalMEIn, finalSave);
                edit.setStyle("-fx-base: #e96e6e;");
            }
        });

        //Mid Save Button set on action
        finalSave.setOnAction(e->{
            if(subjecPercent>0)
                subjective=Integer.parseInt(finalSubjectiveIn.getText());
            if(objectivePercent>0)
                objective=Integer.parseInt(finalObjectiveIn.getText());
            if(practicalPercent>0)
                practical=Integer.parseInt(finalPracticalIn.getText());
            subTotal=(int)((subjective+objective+practical)*subTotalPercent);
            monthly=Integer.parseInt(finalMEIn.getText());
            total=subTotal+monthly;
            roll=Integer.parseInt(rollIn.getText());
            if(updateStudentResultFinal(clas, section, subject, roll,subjective, objective, practical, monthly, subTotal, total)){
                gp.getChildren().removeAll(midSubjectiveLabel, midObjectiveLabel, midPracticalLabel, midMELabel);
                gp.getChildren().removeAll(midSubjectiveIn, midObjectiveIn, midPracticalIn, midMEIn, midSave, finalLabel);
                gp.getChildren().removeAll(finalSubjectiveLabel, finalObjectiveLabel, finalPracticalLabel, finalMELabel);
                gp.getChildren().removeAll(finalSubjectiveIn, finalObjectiveIn, finalPracticalIn, finalMEIn, finalSave);
                edit.setStyle("-fx-base: #e96e6e;");
            }
        });

        gp.getChildren().addAll(rollLabel, sectionLabel, midLabel, rollIn, edit);


        subjectTeacherViewTable.getColumns().addAll(sRoll, sName, sSection);
        subjectTeacherViewTable.getColumns().addAll(sMidSubjective, sMidObjective, sMidPractical, sMidSubTotal, sMidME, sMidTotal);
        subjectTeacherViewTable.getColumns().addAll(sFinalSubjective, sFinalObjective,sFinalPractical, sFinalSubTotal, sFinalME, sFinalTotal);


        gridPane=new GridPane();
        gridPane.getChildren().addAll(subjectTeacherViewTable,gp);

        return gridPane;
    }

    protected static void tableViewClose(){
        subjectTeacherViewTable=new TableView<>();
    }


    private static ObservableList<genericSubject> getStudentInformation(String classSectionSubject)
    {
        ObservableList<genericSubject> subjectResult= FXCollections.observableArrayList();
        String [][] studentResult=getSubjectResult(classSectionSubject);

        int roll, midSubjective, midObjective, midPractical, midMonthly, midSubTotal, midTotal, fSubjective, fObjective,
                fPractical, fMonthly, fSubTotal, fTotal;

        for(int i=0; i<studentResult.length; i++) {

            roll=Integer.parseInt(studentResult[i][0]);
            midSubjective=Integer.parseInt(studentResult[i][3]);
            midObjective=Integer.parseInt(studentResult[i][4]);
            midPractical=Integer.parseInt(studentResult[i][5]);
            midMonthly=Integer.parseInt(studentResult[i][6]);
            midSubTotal=Integer.parseInt(studentResult[i][7]);
            midTotal=Integer.parseInt(studentResult[i][8]);
            fSubjective=Integer.parseInt(studentResult[i][9]);
            fObjective=Integer.parseInt(studentResult[i][10]);
            fPractical=Integer.parseInt(studentResult[i][11]);
            fMonthly=Integer.parseInt(studentResult[i][12]);
            fSubTotal=Integer.parseInt(studentResult[i][13]);
            fTotal=Integer.parseInt(studentResult[i][14]);



            subjectResult.add(new genericSubject(roll, studentResult[i][1],studentResult[i][2], midSubjective, midObjective, midPractical, midMonthly,
                    midSubTotal, midTotal, fSubjective, fObjective, fPractical, fMonthly, fSubTotal, fTotal));
        }


//        std_info.add(new genericSubject(2, "Fazle Rabbi Riyon", 19, 65, 27));

        return subjectResult;
    }
    private static void alart( String message)
    {
        Stage window=new Stage();

        //Block event for other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ALART");
        window.setMinWidth(300);

        Label label=new Label();
        label.setText(message);
        Button button=new Button("Click to Close");
        button.setOnAction(event -> window.close());

        VBox layout=new VBox(20);
        layout.getChildren().addAll(label, button);
        layout.setStyle("-fx-background-color: FUCHSIA");
        layout.setAlignment(Pos.CENTER);

        //Display window & wait for it to closed before returning
        Scene scene=new Scene(layout, 500,200);
        window.setScene(scene);
        window.showAndWait();
    }
}

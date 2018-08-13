package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.bluePrint.*;
/*
import static sample.bluePrint.getClassAndSection;
import static sample.bluePrint.newUser;
*/

/**
 * Created by Murshid on 7/11/2017.
 */
public class classTeacherGeneric {

    private static Stage window;
    private static BorderPane bp;
    private static GridPane center;
    private static String groupName, string;
    private static int roll;

    public static void  subjectTeacher(String name)
    {

        //set a scene
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(name+" as CLASS TEACHER");

        //Build a HBox for top
        HBox top=new HBox();
        //create Button Home, HLogOut, HSettings
        Button HHome=new Button("Home");
        Region reg=new Region();
        Button HLogOut=new Button("Log Out");
        Button HSettings=new Button("Settings");
        //Add all to HBox
        top.getChildren().addAll(HHome, reg, HLogOut, HSettings);

        //set size for the buttons
        HHome.setMinSize(100,25);
        reg.setPrefSize(680,25);
        HLogOut.setMinSize(100,25);
        HSettings.setMinSize(100,25);
        top.setPadding(new Insets(10,10,10,10));
        top.setSpacing(5);

        //Set color for HLogOut & HSettings Button
        HLogOut.setBackground(new Background(new BackgroundFill(
                Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        HSettings.setBackground(new Background(new BackgroundFill(
                Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));


        //Set an event for HHome, HLogOut, HSettings Buttons
        HHome.setOnAction(e-> {
            home.homePage(name);
            window.close();
        });
        HLogOut.setOnAction(e->{
            logIn.logInPage();
            window.close();
        });
        HSettings.setOnAction(e->
        {
            settings.settingsPage(name);
        });

        //VBox layout for left of the boarderPane
        VBox left=new VBox();

        Button LStudenProfiles=new Button("Student Profiles");
        Button LAddStudent=new Button("Add student");
        Button LUpdateStudent=new Button("Update Student");
        Button LResultSheet=new Button("Result Sheet");
        Button LShowSubjectTeacher=new Button("Show Subject Teachers");

        //Add all Button on VBox
        left.getChildren().addAll(LStudenProfiles, LAddStudent, LUpdateStudent, LResultSheet, LShowSubjectTeacher);


        //Set size for all the Button in VBox
        LStudenProfiles.setMaxSize(140,20);
        LAddStudent.setMaxSize(140,20);
        LUpdateStudent.setMaxSize(140,20);
        LResultSheet.setMaxSize(140,20);
        LShowSubjectTeacher.setMaxSize(140,20);
        left.setPadding(new Insets(10,10,10,10));
        left.setSpacing(5);

        //set event for the Buttons
        LAddStudent.setOnAction(e->
        {
            string=getClassAndSection(name);
            if(string!=null)
            {
                addStudentClicked(string);
            }
            else{
                alart("You are not assigned as a class teacher to any class. Contact with admin.");
            }

        });
        LStudenProfiles.setOnAction(e->{
            string=getClassAndSection(name);
            if(string!=null)
            {
                studentProfilesClicked(string);
            }
            else{
                alart("Error Happen");
            }
        });
        LResultSheet.setOnAction(e->{
            string=getClassAndSection(name);
            if(string!=null)
            {
                resultSheetClicked(string);
            }
            else{
                alart("Error Happen");
            }
        });



        //HBox layout for bottom of the BoarderPane
        HBox bot=new HBox();
        Label label=new Label("@ADON,RION,MOHIBUL&EMRUL for cse310");
        bot.getChildren().addAll(label);

        //BorderPane settings
        bp=new BorderPane();
        bp.setTop(top);
        bp.setLeft(left);
//        bp.setCenter(center);
        bp.setBottom(bot);
        bp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");

        Scene sce=new Scene(bp, 1300,700);
        window.setScene(sce);
        window.show();


    }

    private static void updateStudentClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //Roll Label
        Label rollLabel = new Label("*Roll Number");
        GridPane.setConstraints(rollLabel, 0, 0);

        //Roll input
        TextField rollIn = new TextField();
        rollIn.setPromptText("Roll Number");
        GridPane.setConstraints(rollIn, 1, 0);

        //Edit Button
        Button editButton=new Button("EDIT");
        GridPane.setConstraints(editButton, 0,2);
        editButton.setOnAction(e->{

        });

        //Name Label
        Label nameLabel = new Label("*Student Name");
        GridPane.setConstraints(nameLabel, 0, 1);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText("Student Name");
        GridPane.setConstraints(nameIn, 1, 1);

        //Father's Name Label
        Label fNameLabel = new Label("Father's Name");
        GridPane.setConstraints(fNameLabel, 0, 2);

        //Father's Name input
        TextField fNameIn = new TextField();
        fNameIn.setPromptText("Father's Name");
        GridPane.setConstraints(fNameIn, 1, 2);

        //Mother's Name Label
        Label mNameLabel = new Label("Mother's Name");
        GridPane.setConstraints(mNameLabel, 0, 3);

        //Mother's Name input
        TextField mNameIn = new TextField();
        mNameIn.setPromptText("Mother's Name");
        GridPane.setConstraints(mNameIn, 1, 3);

        //Address Label
        Label addressLabel = new Label("Address");
        GridPane.setConstraints(addressLabel, 0, 4);

        //Address input
        TextField addressIn = new TextField();
        addressIn.setPromptText("Address");
        GridPane.setConstraints(addressIn, 1, 4);

        //Group Menu  label
        Label groupLabel = new Label("Group");
        GridPane.setConstraints(groupLabel, 0, 5);

        //Date label
        Label dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 0, 6);

        //Attention label
        Label attention=new Label("*This fields are mandatory ");
        GridPane.setConstraints(attention, 1, 8);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1,10);
        //set on action for save Button
        save.setOnAction(e->
                {
                    if(rollIn.getText().equals("")||nameIn.getText().equals("")||rollIn.getText().equals(null)
                            ||nameIn.getText().equals(null))
                    {
                        alart("You can't leave Roll and Student name empty.");
                    }
                    else {
                        System.out.println("roll :"+rollIn.getText()+"name :"+nameIn.getText());
                        rollIn.setPromptText("Roll Number");
                        nameIn.setPromptText("Student Name");
                        fNameIn.setPromptText("Father's Name");
                        mNameIn.setPromptText("Mother's Name");
                        addressIn.setPromptText("Address");
                    }
                }
        );

        center.getChildren().addAll(nameLabel, nameIn, fNameLabel, fNameIn, mNameLabel, mNameIn, addressLabel, addressIn);
        center.getChildren().addAll( groupLabel, dateLabel, save, rollLabel, rollIn, attention);
        bp.setCenter(center);
    }

    private static void addStudentClicked(String classSection)
    {
        String [] classSec = classSection.split("_");
        int clas=Integer.parseInt(classSec[1]);
        String section = classSec[classSec.length-1];
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //Roll Label
        Label rollLabel = new Label("*Roll Number");
        GridPane.setConstraints(rollLabel, 0, 0);

        //Roll input
        TextField rollIn = new TextField();
        rollIn.setPromptText("Roll Number");
        GridPane.setConstraints(rollIn, 1, 0);

        //Name Label
        Label nameLabel = new Label("*Student Name");
        GridPane.setConstraints(nameLabel, 0, 1);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText("Student Name");
        GridPane.setConstraints(nameIn, 1, 1);

        //Father's Name Label
        Label fNameLabel = new Label("Father's Name");
        GridPane.setConstraints(fNameLabel, 0, 2);

        //Father's Name input
        TextField fNameIn = new TextField();
        fNameIn.setPromptText("Father's Name");
        GridPane.setConstraints(fNameIn, 1, 2);

        //Mother's Name Label
        Label mNameLabel = new Label("Mother's Name");
        GridPane.setConstraints(mNameLabel, 0, 3);

        //Mother's Name input
        TextField mNameIn = new TextField();
        mNameIn.setPromptText("Mother's Name");
        GridPane.setConstraints(mNameIn, 1, 3);

        //Address Label
        Label addressLabel = new Label("Address");
        GridPane.setConstraints(addressLabel, 0, 4);

        //Address input
        TextField addressIn = new TextField();
        addressIn.setPromptText("Address");
        GridPane.setConstraints(addressIn, 1, 4);

        //Group Menu  label
        Label groupLabel = new Label("Group");
        GridPane.setConstraints(groupLabel, 0, 5);

        //Group Choice Box
        ChoiceBox group = new ChoiceBox();
        String [] str={"Science","Commerce","Arts"};

        group.getItems().addAll(str);
        GridPane.setConstraints(group, 1, 5);

        if(clas>8) {
            center.getChildren().addAll(groupLabel, group);
        }

        //Date label
        Label dateLabel = new Label("Date");
        GridPane.setConstraints(dateLabel, 0, 6);

        //Attention label
        Label attention=new Label("*This fields are mandatory ");
        GridPane.setConstraints(attention, 1, 8);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1,10);
        //set on action for save Button
        save.setOnAction(e->
                {
                    if(rollIn.getText().equals("")||nameIn.getText().equals("")||rollIn.getText().equals(null)
                            ||nameIn.getText().equals(null))
                    {
                        alart("You can't leave Roll and Student name empty.");
                    }
                    else {
                        roll=Integer.parseInt(rollIn.getText());
                        groupName=(String) group.getValue();
                        System.out.println("roll :"+rollIn.getText()+" name :"+nameIn.getText()+" Father's Name "+
                            fNameIn.getText()+" Mother's Name : "+mNameIn.getText());
                        if(addStudent(roll, nameIn.getText(), fNameIn.getText(), mNameIn.getText(),
                                addressIn.getText(), groupName, null, clas, section)) {
                            rollIn.setPromptText("Roll Number");
                            nameIn.setPromptText("Student Name");
                            fNameIn.setPromptText("Father's Name");
                            mNameIn.setPromptText("Mother's Name");
                            addressIn.setPromptText("Address");
                        }
                        else
                            alart("Unfortunately your request can't be processed at this moment.");
                    }
                }
        );

        center.getChildren().addAll(nameLabel, nameIn, fNameLabel, fNameIn, mNameLabel, mNameIn, addressLabel, addressIn);
        center.getChildren().addAll( dateLabel, save, rollLabel, rollIn, attention);
        bp.setCenter(center);
    }

    private static void studentProfilesClicked(String classSection)
    {

        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);


        try {
            center=new classTeacherStudentProfile().studentInformation(classSection);
            bp.setCenter(center);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void resultSheetClicked(String classSection)
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        String[] stri=classSection.split("_");
        int clas=Integer.parseInt(stri[1]);
        String section=stri[3];

        //Roll Label
        Label rollLabel = new Label("Roll");
        GridPane.setConstraints(rollLabel, 0, 1);

        //Roll input
        TextField rollIn = new TextField();
        rollIn.setPromptText("Roll");
        GridPane.setConstraints(rollIn, 1, 1);

        //Examination Label
        Label examinationLabel = new Label("Examination");
        GridPane.setConstraints(examinationLabel, 0, 2);

        //Examination Choice Box
        ChoiceBox examinationBox = new ChoiceBox();
        String [] str={"Half Yearly","Final Examination"};
        examinationBox.getItems().addAll(str);
        GridPane.setConstraints(examinationBox, 1, 2);

        //Show Button
        Button showButton=new Button("SHOW");
        GridPane.setConstraints(showButton, 2, 2);

        //Class Label
        Label classLabel = new Label("Class");
        GridPane.setConstraints(classLabel, 0, 3);

        //Class show label
        Label classShow = new Label();
        GridPane.setConstraints(classShow, 1, 3);

        //Section Label
        Label sectionLabel = new Label("Section");
        GridPane.setConstraints(sectionLabel, 0, 4);

        //Section show Label
        Label sectionShow = new Label();
        GridPane.setConstraints(sectionShow, 1, 4);

        //Name Label
        Label nameLabel = new Label("Name");
        GridPane.setConstraints(nameLabel, 0, 5);

        //Name show Label
        Label nameShow = new Label();
        GridPane.setConstraints(nameShow, 1, 5);

        showButton.setOnAction(e->{

            center.getChildren().addAll(classLabel,classShow,sectionLabel,sectionShow,nameLabel,nameShow);
            GridPane gp=resultSheet(clas, section, Integer.parseInt(rollIn.getText()), (String)examinationBox.getValue());
            GridPane.setConstraints(gp, 4, 6);
            center.getChildren().add(gp);

        });

//        center.setStyle("-fx-base: #e96e6e;");
        center.getChildren().addAll(rollLabel, rollIn, examinationLabel, examinationBox, showButton);
        bp.setCenter(center);



    }

    private static GridPane resultSheet(int clas, String section, int roll, String exam){
        GridPane gp=new GridPane();
        gp.setPadding(new Insets(50, 50, 50, 50));
        gp.setVgap(8);
        gp.setHgap(12);

        String studentResults[][]=null;

        //Subjects show Label
        Label subjectsShow = new Label("Subjects");
        GridPane.setConstraints(subjectsShow, 0, 0);

        //Subjective show Label
        Label subjectiveShow = new Label("Subjective");
        GridPane.setConstraints(subjectiveShow, 1, 0);

        //Objective show Label
        Label objectiveShow = new Label("Objective");
        GridPane.setConstraints(objectiveShow, 2, 0);

        //Practical show Label
        Label practicalShow = new Label("Practical");
        GridPane.setConstraints(practicalShow, 3, 0);

        //subTotal show Label
        Label subTotalShow = new Label("Sub Total");
        GridPane.setConstraints(subTotalShow, 4, 0);

        //ME show Label
        Label MEShow = new Label("ME");
        GridPane.setConstraints(MEShow, 5, 0);

        //Total show Label
        Label totalShow = new Label("Total");
        GridPane.setConstraints(totalShow, 6, 0);

        if(exam.equals("Half Yearly"))
            studentResults=showStudentResultMid(clas, section, roll);
        else if(exam.equals("Final Examination"))
            studentResults=showStudentResultFinal(clas, section, roll);

        Label [][] result =new Label[studentResults.length][7];

        for(int i=0; i<studentResults.length; i++){
            for(int j=0; j<7; j++)
            {
                result[i][j]=new Label(studentResults[i][j]);
                GridPane.setConstraints(result[i][j], j, (i+1));
                gp.getChildren().add(result[i][j]);
            }
        }
        gp.getChildren().addAll(subjectsShow, subjectiveShow, objectiveShow, practicalShow, subTotalShow, MEShow, totalShow);
        return gp;
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

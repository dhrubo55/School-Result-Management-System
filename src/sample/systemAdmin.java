package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.bluePrint.*;

/**
 * Created by Murshid on 7/17/2017.
 */
public class systemAdmin {
    private static Stage window;
    private static String uName;
    private static BorderPane bp;
    private static GridPane center;
    private static String str[] ;
    private static int c_acc, s_acc, i, j;
    private static String string, string1;
//    private static double subjective, objective, practical, subTotal, ME, total=1;


    public static void systemAdminPage(String name)
    {
        uName=name;

        System.out.println("User name: "+name+" color: "+bluePrint.getColor(name));

        //set a scene
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("System Admin Page");

        //Build a HBox for top
        HBox top=new HBox();
        //create Button HLogOut and HSettings Button
//        Button HHome=new Button("Home");
        Region reg=new Region();
        Button HLogOut=new Button("Log Out");
        Button HSettings=new Button("Settings");
        top.getChildren().addAll( reg, HLogOut, HSettings);

        //set size for the buttons
        reg.setPrefSize(680,25);
        HLogOut.setMinSize(100,25);
        HSettings.setMinSize(100,25);
        top.setPadding(new Insets(10,10,10,10));
        top.setSpacing(5);

        //Set color for HLogOut Button
        HLogOut.setBackground(new Background(new BackgroundFill(
                Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        HSettings.setBackground(new Background(new BackgroundFill(
                Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

        //Set an event for HLogOut and HSettings Buttons
        HLogOut.setOnAction((ActionEvent e) ->{
            logIn.logInPage();
            window.close();
        });
        HSettings.setOnAction(e->
        {
            settings.settingsPage(name);
        });


        //VBox layout for left of the boarderPane
        VBox left=new VBox();

        Button LCreateUser=new Button("Create User");
        Button LUpdateUser=new Button("Update User");
        Button LSubjectSettings=new Button("Subject Settings");
        Button LCreateOrRemoveClassSection=new Button("Create or Remove Class Section");
        Button LAssignClassTeacher=new Button("Assign Class Teacher");
        Button LAssignSubjectTeacher=new Button("Assign Subject Teacher");

        //Add all Button on VBox
        left.getChildren().addAll(LCreateUser, LUpdateUser, LSubjectSettings, LCreateOrRemoveClassSection, LAssignClassTeacher, LAssignSubjectTeacher);


        //Set size for all the Button in VBox
        LCreateUser.setMaxSize(200,20);
        LUpdateUser.setMaxSize(200,20);
        LSubjectSettings.setMaxSize(200,20);
        LCreateOrRemoveClassSection.setMaxSize(200,20);
        LAssignClassTeacher.setMaxSize(200,20);
        LAssignSubjectTeacher.setMaxSize(200,20);
        left.setPadding(new Insets(10,10,10,10));
        left.setSpacing(5);

        //set event for the Buttons
        LCreateUser.setOnAction(e->createUserClicked());
        LUpdateUser.setOnAction(e->updateUserClicked());
        LCreateOrRemoveClassSection.setOnAction(e->createOrRemoveClassSectionClicked());
        LAssignClassTeacher.setOnAction(e->assignClassTeacherClicked());
        LSubjectSettings.setOnAction(e-> subjectSettingsClicked());
        LAssignSubjectTeacher.setOnAction(e->assignSubjectTeacherClicked());

        /*LSubjectSettings.setOnAction(e->
        {
            if(isAllowAccess(name, 1))
            {
                System.out.println("Changing Page");
                subjectTeacherGeneric.subjectTeacher(name);
                window.close();
            }
            else
            {
                alart("Your access to Subject Teacher is invalid. Contact Admin");
            }

        });*/

        //GridPane layout for center of the BoarderPane
//        center=new GridPane();

        //VBox layout for bottom of the BoarderPane
        VBox bot=new VBox();
        Label noAccess=new Label("");
        HBox hBox=new HBox();
        Label label=new Label("@ADON,RION,MOHIBUL&EMRUL for cse310");
        hBox.getChildren().add(label);
        bot.getChildren().addAll(noAccess, hBox);


        //BorderPane settings
        bp=new BorderPane();
        bp.setTop(top);
        bp.setLeft(left);
//        bp.setCenter(center);
        bp.setBottom(bot);
        bp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");



        //Add boarderPane in window and set show
        Scene sce=new Scene(bp, 1000,700);
        window.setScene(sce);
        window.show();


    }

    private static void createUserClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);


        //Name Label
        Label nameLabel = new Label("User Name");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText("User Name");
        GridPane.setConstraints(nameIn, 1, 0);

        //Pass Word Label
        Label passLabel = new Label("Pass Word");
        GridPane.setConstraints(passLabel, 0, 1);

        //Pass Word input
        TextField passIn = new TextField();
        passIn.setPromptText("Pass Word");
        GridPane.setConstraints(passIn, 1, 1);

        //Class Teacher Label
        Label cTeacherLabel = new Label("Class Teacher");
        GridPane.setConstraints(cTeacherLabel, 0, 2);

        //Subject Teacher Label
        Label sTeacherLabel = new Label("Subject Teacher");
        GridPane.setConstraints(sTeacherLabel, 0, 3);

        //Class Teacher yes no radio button
        RadioButton cTeacherYes=new RadioButton("YES");
        GridPane.setConstraints(cTeacherYes, 1,2);

        //Class Teacher yes no radio button
        RadioButton sTeacherYes=new RadioButton("YES");
        GridPane.setConstraints(sTeacherYes, 1,3);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1,7);
        //set on action for save Button
        save.setOnAction(e->
                {
                    newUser(nameIn.getText(),passIn.getText(), cTeacherYes.isSelected(), sTeacherYes.isSelected());
                    nameIn.setPromptText("User Name");
                    passIn.setPromptText("Pass Word");
                }
        );

        center.getChildren().addAll(nameLabel, nameIn, passLabel, passIn, cTeacherLabel, sTeacherLabel, cTeacherYes, sTeacherYes, save);
        bp.setCenter(center);
    }

    private static void updateUserClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //Name Label
        Label nameLabel = new Label("User Name");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText("User Name");
        GridPane.setConstraints(nameIn, 1, 0);

        //Pass Word Label
        Label passLabel = new Label("Pass Word");
        GridPane.setConstraints(passLabel, 0, 1);

        //Pass Word input
        TextField passIn = new TextField();
        passIn.setPromptText("Pass Word");
        GridPane.setConstraints(passIn, 1, 1);

        //Color Label
        Label colorLabel = new Label("Color");
        GridPane.setConstraints(colorLabel, 0, 2);

        //Color input
        TextField colorIn = new TextField();
        GridPane.setConstraints(colorIn, 1, 2);

        //Class Teacher Label
        Label cTeacherLabel = new Label("Class Teacher");
        GridPane.setConstraints(cTeacherLabel, 0, 3);

        //Subject Teacher Label
        Label sTeacherLabel = new Label("Subject Teacher");
        GridPane.setConstraints(sTeacherLabel, 0, 4);

        //Class Teacher yes no radio button
        RadioButton cTeacherYes=new RadioButton("YES");
        GridPane.setConstraints(cTeacherYes, 1,3);

        //Class Teacher yes no radio button
        RadioButton sTeacherYes=new RadioButton("YES");
        GridPane.setConstraints(sTeacherYes, 1,4);

        //UPDATE Button
        Button update=new Button("UPDATE");
        GridPane.setConstraints(update, 1,7);
        //set on action for save Button
        update.setOnAction(e-> {
            if(cTeacherYes.isSelected())
                c_acc=1;
            else
                c_acc=0;
            if(sTeacherYes.isSelected())
                s_acc=1;
            else
                s_acc=0;
                    updateUser(nameIn.getText(),passIn.getText(), colorIn.getText(), c_acc, s_acc);
                    nameIn.setPromptText("User Name");
                }
        );


        //Edit Button
        Button editButton=new Button("EDIT");
        GridPane.setConstraints(editButton, 2,0);
        editButton.setOnAction(e->{
            System.out.println(nameIn.getText());
            str=userDetails(nameIn.getText());
            if(str!=null)
            {
                passIn.setPromptText(str[0]);
                colorIn.setPromptText(str[1]);
                if(Integer.parseInt(str[2])==1)
                    cTeacherYes.setSelected(true);
                else
                    cTeacherYes.setSelected(false);
                if(Integer.parseInt(str[3])==1)
                    sTeacherYes.setSelected(true);
                else
                    sTeacherYes.setSelected(false);

            }
            if(center.getChildren().contains(passIn)==false && str!=null && !(str.equals("")))
            {
                center.getChildren().addAll(passIn, colorIn, cTeacherYes, sTeacherYes);
            }
        });


        center.getChildren().addAll(nameLabel, nameIn, passLabel, cTeacherLabel, sTeacherLabel, update, editButton, colorLabel);
        bp.setCenter(center);
    }

    private static void createOrRemoveClassSectionClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //ADD Label
        Label addLabel = new Label(" Add");
        GridPane.setConstraints(addLabel, 1, 0);

        //Class Label
        Label classLabel = new Label("Class");
        GridPane.setConstraints(classLabel, 0, 1);

        //Class input
        TextField classIn = new TextField();
        classIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(classIn, 1, 1);

        //Section Label
        Label sectionLabel = new Label("Section");
        GridPane.setConstraints(sectionLabel, 0, 2);

        //Section input
        TextField sectionIn = new TextField();
        sectionIn.setPromptText("Section in lowercase Char");
        GridPane.setConstraints(sectionIn, 1, 2);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1,7);

        //Remove Button
        Button remove = new Button(" Remove");
        GridPane.setConstraints(remove, 6, 0);

        //Remove Class Label
        Label rClassLabel = new Label("Class");
        GridPane.setConstraints(rClassLabel, 5, 1);

        //Remove Class input
        TextField rClassIn = new TextField();
        rClassIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(rClassIn, 6, 1);

        //Remove Section Label
        Label rSectionLabel = new Label("Section");
        GridPane.setConstraints(rSectionLabel, 5, 2);

        //Remove Section input
        TextField rSectionIn = new TextField();
        rSectionIn.setPromptText("Section in lowercase Char");
        GridPane.setConstraints(rSectionIn, 6, 2);

        //Complete Button
        Button complete=new Button("COMPLETE");
        GridPane.setConstraints(complete, 6, 7);

        //Button seton action
        save.setOnAction(e->{
            string="c_"+classIn.getText()+"_s_"+sectionIn.getText();
            System.out.println(string);
            if(createClassSec(string)==false)
            {
                alart("Class section creation failed");
            }
        });

        remove.setOnAction(e->{
            if(!center.getChildren().contains(rClassLabel))
                center.getChildren().addAll(rClassLabel,rClassIn,rSectionLabel,rSectionIn,complete);
        });

        center.getChildren().addAll(addLabel, classLabel, classIn, sectionLabel, sectionIn, save, remove);
        bp.setCenter(center);
    }

    private static void assignClassTeacherClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //Assign Label
        Label assignLabel = new Label(" Assign");
        GridPane.setConstraints(assignLabel, 1, 0);

        //Class Label
        Label classLabel = new Label("Class");
        GridPane.setConstraints(classLabel, 0, 1);

        //Class input
        TextField classIn = new TextField();
        classIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(classIn, 1, 1);

        //Section Label
        Label sectionLabel = new Label("Section");
        GridPane.setConstraints(sectionLabel, 0, 2);

        //Section input
        TextField sectionIn = new TextField();
        sectionIn.setPromptText("Section in lowercase Char");
        GridPane.setConstraints(sectionIn, 1, 2);

        //Teacher Label
        Label teacherLabel = new Label("Teacher");
        GridPane.setConstraints(teacherLabel, 0, 3);

        //Teacher Choice Box
        str=classTeachersNames();
        ChoiceBox teachers=new ChoiceBox();
        teachers.getItems().addAll(str);
        GridPane.setConstraints(teachers, 1, 3);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1,7);

        //Remove Button
        Button remove = new Button(" Remove");
        GridPane.setConstraints(remove, 6, 0);

        //Remove Class Label
        Label rClassLabel = new Label("Class");
        GridPane.setConstraints(rClassLabel, 5, 1);

        //Remove Class input
        TextField rClassIn = new TextField();
        rClassIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(rClassIn, 6, 1);

        //Remove Section Label
        Label rSectionLabel = new Label("Section");
        GridPane.setConstraints(rSectionLabel, 5, 2);

        //Remove Section input
        TextField rSectionIn = new TextField();
        rSectionIn.setPromptText("Section in lowercase Char");
        GridPane.setConstraints(rSectionIn, 6, 2);

        //Complete Button
        Button complete=new Button("COMPLETE");
        GridPane.setConstraints(complete, 6, 7);

        //Button seton action
        save.setOnAction(e->{
            string="c_"+classIn.getText()+"_s_"+sectionIn.getText();

            System.out.println(string+" class teacher"+teachers.getValue());
            string1= (String) teachers.getValue();
            if(assignClassTeacher(string1, string)==false){
                alart("Teacher Assigning Process failed. Please contact admin");
            }
        });

        remove.setOnAction(e->{
            if(!center.getChildren().contains(rClassLabel))
                center.getChildren().addAll(rClassLabel,rClassIn,rSectionLabel,rSectionIn,complete);
        });

        center.getChildren().addAll(assignLabel, classLabel, classIn, sectionLabel, sectionIn, save, remove, teachers, teacherLabel);
        bp.setCenter(center);
    }

    private static void subjectSettingsClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);


        //Assign Label
        Label assignLabel = new Label(" Subject Settings");
        GridPane.setConstraints(assignLabel, 1, 0);

        //Class Label
        Label classLabel = new Label("Class");
        GridPane.setConstraints(classLabel, 0, 2);

        //Class input
        TextField classIn = new TextField();
        classIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(classIn, 1, 2);

        //Subject Label
        Label subjectLabel = new Label("Subject");
        GridPane.setConstraints(subjectLabel, 0, 3);

        //Subject input
        TextField subjectIn = new TextField();
        subjectIn.setPromptText("Input Subject in String");
        GridPane.setConstraints(subjectIn, 1, 3);

        //Validate Button
        Button valid=new Button("Valid");
        GridPane.setConstraints(valid, 2, 3);
        valid.setStyle("-fx-base: #e96e6e;");

        //Subjective Label
        Label subjectiveLabel = new Label("Subjective");
        GridPane.setConstraints(subjectiveLabel, 0, 4);

        //Subjective input
        TextField subjectiveIn = new TextField();
        subjectiveIn.setPromptText("Subjective % out of 100% in exam");
        GridPane.setConstraints(subjectiveIn, 1, 4);

        //Subjective Percentage Label
        Label subjectivePercentLabel = new Label("%");
        GridPane.setConstraints(subjectivePercentLabel, 2, 4);

        //Objective Label
        Label objectiveLabel = new Label("Objective");
        GridPane.setConstraints(objectiveLabel, 0, 5);

        //Objective input
        TextField objectiveIn = new TextField();
        objectiveIn.setPromptText("Objective % out of 100% in exam");
        GridPane.setConstraints(objectiveIn, 1, 5);

        //Objective Percentage Label
        Label objectivePercentLabel = new Label("%");
        GridPane.setConstraints(objectivePercentLabel, 2, 5);

        //Practical Label
        Label practicalLabel = new Label("Practical");
        GridPane.setConstraints(practicalLabel, 0, 6);

        //Practical input
        TextField practicalIn = new TextField();
        practicalIn.setPromptText("Practical % out of 100% in exam");
        GridPane.setConstraints(practicalIn, 1, 6);

        //Practical Percentage Label
        Label practicalPercentLabel = new Label("%");
        GridPane.setConstraints(practicalPercentLabel, 2, 6);

        //After Practical Label
        Label afterPracticalLabel = new Label("=100% which will");
        GridPane.setConstraints(afterPracticalLabel, 1, 7);

        //Convert Label
        Label convertLabel = new Label("Convert to");
        GridPane.setConstraints(convertLabel, 0, 8);

        //Convert input
        TextField convertIn = new TextField();
        convertIn.setPromptText("Input Sub Total");
        GridPane.setConstraints(convertIn, 1, 8);

        //Convert Percentage Label
        Label convertPercentLabel = new Label("% as Sub total");
        GridPane.setConstraints(convertPercentLabel, 2, 8);

        //ME Label
        Label MELabel = new Label("ME");
        GridPane.setConstraints(MELabel, 0, 9);

        //ME input
        TextField MEIn = new TextField();
        MEIn.setPromptText("Input ME Number");
        GridPane.setConstraints(MEIn, 1, 9);

        //ME Percentage Label
        Label MEPercentLabel = new Label("%");
        GridPane.setConstraints(MEPercentLabel, 2, 9);

        //=100% Label
        Label totalLabel = new Label("=100% In Term Result");
        GridPane.setConstraints(totalLabel, 1, 10);

        //Save Button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save, 1, 13);

        center.getChildren().addAll(assignLabel, classLabel, classIn, subjectLabel, subjectIn, valid);
        //Action of the buttons
        valid.setOnAction(e->{
            string="c_"+classIn.getText()+"_"+subjectIn.getText();
            if(isTableExist(string)==false){
                valid.setStyle("-fx-base: #a8d877;");
                center.getChildren().addAll(subjectiveLabel,subjectiveIn,subjectivePercentLabel,objectiveLabel,objectiveIn,objectivePercentLabel,practicalLabel,practicalIn);
                center.getChildren().addAll(practicalPercentLabel,afterPracticalLabel,convertLabel,convertIn,convertPercentLabel,MELabel,MEIn,MEPercentLabel,totalLabel,save);
            }
            else
                alart("The class and Subject you enter is exist on database or can't process data at this moment.");
        });
        save.setOnAction(e->{
            double subjective=Double.parseDouble(subjectiveIn.getText())/100;
            double objective=Double.parseDouble(objectiveIn.getText())/100;
            double practical=Double.parseDouble(practicalIn.getText())/100;
            double subTotal=Double.parseDouble(convertIn.getText())/100;
            double ME=Double.parseDouble(MEIn.getText())/100;
            double total=1;
            if(createNewClassSubject(string, subjective, objective, practical, subTotal, ME, total)){
                classIn.clear(); subjectIn.clear(); subjectiveIn.clear(); objectiveIn.clear();
                practicalIn.clear(); convertIn.clear();

                center.getChildren().removeAll(subjectiveLabel,subjectiveIn,subjectivePercentLabel,objectiveLabel,objectiveIn,objectivePercentLabel,practicalLabel,practicalIn);
                center.getChildren().removeAll(practicalPercentLabel,afterPracticalLabel,convertLabel,convertIn,convertPercentLabel,MELabel,MEIn,MEPercentLabel,totalLabel,save);
                classIn.setPromptText("Input Class in Integer");
                subjectIn.setPromptText("Input Section in String");
                subjectiveIn.setPromptText("Subjective % out of 100% in exam");
                objectiveIn.setPromptText("Objective % out of 100% in exam");
                practicalIn.setPromptText("Practical % out of 100% in exam");
                convertIn.setPromptText("Input Sub Total");

            }
            else{
                alart("Action Failed. Contact Admin");
            }
        });

        bp.setCenter(center);
    }

    private static void assignSubjectTeacherClicked()
    {
        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        //Assign Label
        Label assignLabel = new Label(" Assign Subject Teacher");
        GridPane.setConstraints(assignLabel, 1, 0);

        //Class Label
        Label classLabel = new Label("Class");
        GridPane.setConstraints(classLabel, 0, 2);

        //Class input
        TextField classIn = new TextField();
        classIn.setPromptText("Input Class in Integer");
        GridPane.setConstraints(classIn, 1, 2);

        //Section Label
        Label sectionLabel = new Label("Section");
        GridPane.setConstraints(sectionLabel, 0, 3);

        //Section input
        TextField sectionIn = new TextField();
        sectionIn.setPromptText("Input Section in String");
        GridPane.setConstraints(sectionIn, 1, 3);

        //Subject Label
        Label subjectLabel = new Label("Subject");
        GridPane.setConstraints(subjectLabel, 0, 4);

        //Subject input
        TextField subjectIn = new TextField();
        subjectIn.setPromptText("Input Subject in String");
        GridPane.setConstraints(subjectIn, 1, 4);

        //valid button
        Button valid=new Button("valid");
        GridPane.setConstraints(valid, 2, 4);
        valid.setStyle("-fx-base: #e96e6e;");

        //Teacher Label
        Label teacherLabel = new Label("Teacher");
        GridPane.setConstraints(teacherLabel, 0, 5);

        //Teacher ChoiceBox
        str=subjectTeachersNames();
        ChoiceBox teacher=new ChoiceBox();
        teacher.getItems().addAll(str);
        GridPane.setConstraints(teacher, 1,5);

        center.getChildren().addAll(assignLabel, classLabel, classIn, sectionLabel, sectionIn, subjectLabel, subjectIn, valid);

        //Save button
        Button save=new Button("SAVE");
        GridPane.setConstraints(save,1, 8);

        //Action for the buttons
        valid.setOnAction(e->{
            string= "c_"+classIn.getText()+"_"+subjectIn.getText();
            if(isTableExist(string)){
                valid.setStyle("-fx-base: #a8d877;");
                center.getChildren().addAll(teacherLabel,teacher, save);
            }
            else
                alart("Action is Invalid.");
        });
        save.setOnAction(e->{
            string= "c_"+classIn.getText()+"_s_"+sectionIn.getText()+"_"+subjectIn.getText();
            string1=""+teacher.getValue();
            if(assignSubjectTeacher(string1,string))
            {
                classIn.clear();
                classIn.setPromptText("Input Class in Integer");
                subjectIn.clear();
                subjectIn.setPromptText("Input Subject in String");
                center.getChildren().removeAll(teacherLabel,teacher, save);
            }
            else
                alart("Action Failed");
        });

        bp.setCenter(center);
    }

    public static void changeColor()
    {
        bp.setStyle("-fx-background-color: "+bluePrint.getColor(uName)+";");
    }

    public static void setClose()
    {
        window.close();
    }

    private static void alart( String messege)
    {
        Stage window=new Stage();

        //Block event for other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ALART");
        window.setMinWidth(300);

        Label label=new Label();
        label.setText(messege);
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

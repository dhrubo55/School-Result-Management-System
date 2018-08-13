package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.bluePrint.getClassSectionAndSubjects;
import static sample.subjectTeacherTableView.run;
import static sample.subjectTeacherTableView.tableViewClose;

/**
 * Created by Murshid on 7/9/2017.
 */
public class subjectTeacherGeneric {

    private static Stage window;
    private static String [] str;
    private static int i;
    private static GridPane center;
    private static BorderPane bp;

    public static void  subjectTeacher(String name)
    {

        //set a scene
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(name+" as SUBJECT TEACHER");

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
            tableViewClose();
            window.close();
        });
        HSettings.setOnAction(e->
        {
            settings.settingsPage(name);
        });

        //VBox layout for left of the BoarderPane
        VBox left=new VBox();
        str=getClassSectionAndSubjects(name);
        Button [] classSectionSubject=new Button[str.length];


        for(i =0; i<str.length; i++){
            final int ii=i;
            classSectionSubject[i]=new Button(str[i]);
            classSectionSubject[i].setMinSize(140,20);
            classSectionSubject[i].setOnAction(e->{
                if(classSectionSubjectClicked(str[ii], name)){
//                    bp.setCenter(null);
                }
                else{
                    alart("Action can't be performed.");
                }

            });
        }

        left.setPadding(new Insets(10,10,10,10));
        left.setSpacing(5);
        left.getChildren().addAll(classSectionSubject);


        /*//VBox layout for center of the BoarderPane
        center=new VBox();
        try {
            center=new subjectTeacherTableView().run();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //HBox layout for bottom of the BoarderPane
        HBox bot=new HBox();
        Label label=new Label("@ADON,RION,MOHIBUL&EMRUL for cse310");
        bot.getChildren().addAll(label);

        //BorderPane settings
        bp=new BorderPane();
        bp.setTop(top);
        bp.setLeft(left);
        center=new GridPane();
        bp.setCenter(center);
        bp.setBottom(bot);
        bp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");

        Scene sce=new Scene(bp, 1300,700);
        window.setScene(sce);
        window.show();


    }

    private static boolean classSectionSubjectClicked(String classSectionSubject, String teacherName){
        String [] CSS=classSectionSubject.split("_");
        System.out.println(teacherName+" Accessing class "+CSS[1]+" Section "+CSS[3]+" Subject "+CSS[4]);

        center=new GridPane();
        center.setPadding(new Insets(50, 50, 50, 50));
        center.setVgap(8);
        center.setHgap(12);

        try {
//            center=new subjectTeacherTableView().run(classSectionSubject);
            center=run(classSectionSubject);
            bp.setCenter(center);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            alart("Error Happen Contact Admin");
            return false;
        }


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

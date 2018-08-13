package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

import static sample.bluePrint.isAllowAccess;

/**
 * Created by Murshid on 7/9/2017.
 */
public class home {

    private static Stage window;
    private static String uName;
    private static BorderPane bp;
    //private static Scene window;

    public static void homePage(String name)
    {
        uName=name;

        System.out.println("User name: "+name+" color: "+bluePrint.getColor(name));

        //set a scene
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("HOME");

        //Build a HBox for top
        HBox top=new HBox();
        //create Button Home, HLogOut & HSettings Button
        Button HHome=new Button("Home");
        Region reg=new Region();
        Button HLogOut=new Button("Log Out");
        Button HSettings=new Button("Settings");
        top.getChildren().addAll(HHome, reg, HLogOut, HSettings);

        //set size for the buttons
        HHome.setMinSize(100,25);
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

        Button LProfile=new Button("Profile");
        //Button LBio=new Button("Bio");
        Button LClassTeacher=new Button("Class Teacher");
        Button LSubjectTeacher=new Button("Subject Teacher");
        Button LRoutine=new Button("Class Routine");
        //Add all Button on VBox
        left.getChildren().addAll(LProfile,LClassTeacher, LSubjectTeacher, LRoutine);


        //Set size for all the Button in VBox
        LProfile.setMaxSize(120,20);
//        LBio.setMaxSize(120,20);
        LClassTeacher.setMaxSize(120,20);
        LSubjectTeacher.setMaxSize(120,20);
        LRoutine.setMaxSize(120,20);
        left.setPadding(new Insets(10,10,10,10));
        left.setSpacing(5);

        //set event for the Buttons
        LClassTeacher.setOnAction(e->
        {
            if(isAllowAccess(name, 0))
            {
                System.out.println("Changing Page");
                classTeacherGeneric.subjectTeacher(name);
                window.close();
            }
            else
            {
                alart("Your access to Class Teacher is invalid. Contact Admin");
            }
        });
        LSubjectTeacher.setOnAction(e->
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

        });

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
        //bp.setCenter(center);
        bp.setBottom(bot);
        if(name.equals("ADON"))
        {
            bp.setStyle("-fx-background-image: url(\"file:e:/ew8mwTw.jpg\")");
        }
        /*else if(name.equals("MOHIBUL"))
        {
            new home().music();
            bp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");
        }*/
        else
        {
            bp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");
        }


        //Add boarderPane in window and set show
        Scene sce=new Scene(bp, 1000,700);
        window.setScene(sce);
        window.show();


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

    public void music()
    {
        final URL resource=getClass().getResource("Local Bus.mp3");
        final Media media=new Media(resource.toString());
        final MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.play();
    }


}

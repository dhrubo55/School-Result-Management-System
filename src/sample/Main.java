package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {

    Stage window;
    private Button schoolResultAutomation;

    public static void main(String[] args) throws Exception
    {


        Runtime.getRuntime().exec("E:\\Work\\Git\\school_management\\mini_server_11\\mysql_start.bat", null, new File("E:\\Work\\Git\\school_management\\mini_server_11\\"));
        Thread.sleep(3000);
        bluePrint d = new bluePrint("jdbc:mysql://localhost:3311/","root","root");
        d.createDB("result_processing_db");

//        System.out.println(args);
        launch(args);


        //d.createUserTable();
        //d.createStudentTable("std_info");
// for testing purpose comment this section.


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        schoolResultAutomation= new Button("School Result Automation");
        schoolResultAutomation.setOnAction( event -> {new logIn();
            primaryStage.close();
        });

        StackPane root=new StackPane();
        root.getChildren().add(schoolResultAutomation);


     //   Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("School Result Automation");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();
    }



}

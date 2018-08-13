package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Murshid on 7/11/2017.
 */
public class settings {
    Stage window;
    private static GridPane gp;
    private static String uName;
    public static void settingsPage(String name)
    {
        uName=name;
        Stage window=new Stage();

        //Block event for other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SETTINGS");

        //Build a HBox for top
        HBox top=new HBox();
        //create Button Home and HLogOut
//        Button HHome=new Button("Home");
        Region reg=new Region();
        Button HClose=new Button("CLOSE");
        //Add all to HBox
        top.getChildren().addAll(reg, HClose);

        //set size for the buttons
//        HHome.setMinSize(100,25);
        reg.setPrefSize(680,25);
        HClose.setMinSize(100,25);
//        HAccount.setMinSize(100,25);

        //Set color for HClose Button
        HClose.setBackground(new Background(new BackgroundFill(
                Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

        //Set an event for HHome & HClose Buttons
        HClose.setOnAction(e->
        {
            window.close();
        });

        gp = new GridPane();
        gp.setPadding(new Insets(50, 50, 50, 50));
        gp.setVgap(8);
        gp.setHgap(12);

        //Name Label
        Label nameLabel = new Label("User Name");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText(name);
        GridPane.setConstraints(nameIn, 1, 0);

        //Password label
        Label pass = new Label("Password");
        GridPane.setConstraints(pass, 0, 1);

        //Change Background Color label
        Label backgroundLabel = new Label("Background Color");
        GridPane.setConstraints(backgroundLabel, 0, 2);

        //Color Menu Bar
        Menu colorMenu=new Menu(bluePrint.getColor(name));
        MenuItem red=new MenuItem("RED");
        red.setOnAction(e->{
            bluePrint.setColor(name, "red");
            if(!name.equals("SYSTEM_ADMIN"))
            {
                home.changeColor();
                colorMenu.setText("RED");
            }
            changeColor();
        });
        MenuItem white=new MenuItem("WHITE");
        white.setOnAction(e->{
            bluePrint.setColor(name, "white");
            if(!name.equals("SYSTEM_ADMIN"))
            {
                home.changeColor();
                colorMenu.setText("WHITE");
            }
            changeColor();
        });
        MenuItem skyblue=new MenuItem("SKY BLUE");
        skyblue.setOnAction(e->{
            bluePrint.setColor(name, "skyblue");
            if(!name.equals("SYSTEM_ADMIN"))
            {
                home.changeColor();
                colorMenu.setText("SKY BLUE");
            }
            changeColor();
        });
        MenuItem yellow=new MenuItem("YELLOW");
        yellow.setOnAction(e->{
            bluePrint.setColor(name, "yellow");
            if(!name.equals("SYSTEM_ADMIN"))
            {
                home.changeColor();
                colorMenu.setText("YELLOW");
            }
            changeColor();
        });
        MenuItem green=new MenuItem("GREEN");
        green.setOnAction(e-> {
            bluePrint.setColor(name, "green");
            if(!name.equals("SYSTEM_ADMIN"))
            {
                home.changeColor();
                colorMenu.setText("RED");
            }
            changeColor();
        });
        colorMenu.getItems().addAll(red, white, skyblue, yellow, green);

        //Add colorMenu in menuBar and then menuBar to grid pane
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(colorMenu);
        menuBar.maxWidth(100);
        GridPane.setConstraints(menuBar, 1, 2);

        //Grid Pane Settings
        gp.setMinWidth(200);
        gp.getChildren().addAll(nameLabel, nameIn, pass, backgroundLabel, menuBar);
        gp.setStyle("-fx-background-color: "+bluePrint.getColor(name)+";");

        //HBox layout for bottom of the BoarderPane
        HBox bot=new HBox();
        Label label=new Label("@ADON,RION,MOHIBUL&EMRUL for cse310");
        bot.getChildren().addAll(label);



        //BorderPane settings
        BorderPane bp=new BorderPane();
        bp.setTop(top);
//        bp.setLeft(left);
        bp.setCenter(gp);
        bp.setBottom(bot);

        //Add boarderPane in window and set show
        Scene sce=new Scene(bp, 800,600);
        window.setScene(sce);
        window.show();

    }

    public static void changeColor()
    {
        gp.setStyle("-fx-background-color: "+bluePrint.getColor(uName)+";");
    }



}

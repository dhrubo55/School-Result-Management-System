package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Murshid on 7/9/2017.
 */
public class logIn {

    private static Label invalid;

    public logIn()
    {
//        setUserAndPass();
        logInPage();
    }


    public static void logInPage()
    {

        Stage window=new Stage();

        //Block event for other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("LOG IN");
        window.setMinWidth(300);

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50, 50, 50, 50));
        gp.setVgap(8);
        gp.setHgap(12);

        //Name Label
        Label name = new Label("User Name");
        GridPane.setConstraints(name, 0, 0);

        //Name input
        TextField nameIn = new TextField();
        nameIn.setPromptText("User name");
        GridPane.setConstraints(nameIn, 1, 0);

        //Password label
        Label pass = new Label("Password");
        GridPane.setConstraints(pass, 0, 1);

        PasswordField passIn = new PasswordField();
        passIn.setPromptText("Password");
        GridPane.setConstraints(passIn, 1, 1);

        //Invalid Message
        invalid=new Label("");
        GridPane.setConstraints(invalid, 0, 2);

        //Log In Button
        bluePrint b = new bluePrint("jdbc:mysql://localhost:3311/","root","root");

        Button but = new Button("LOG IN");
        but.setOnAction((ActionEvent e) -> {
            if((isValid(nameIn) && isValid(passIn)) && (b.loginInfo(nameIn.getText(), passIn.getText())) && (nameIn.getText().equals("SYSTEM_ADMIN")) ) {
                System.out.println(nameIn.getText());
                systemAdmin.systemAdminPage(nameIn.getText());
                window.close();
            }
            else if((isValid(nameIn) && isValid(passIn)) && b.loginInfo(nameIn.getText(), passIn.getText())) {
                System.out.println(nameIn.getText());
                home.homePage(nameIn.getText());
                window.close();
            }
            else {
                System.out.println("name "+nameIn.getText()+" Pass "+passIn.getText());
                invalid.setText("Invalid Username or Password");
            }
        });
        but.setOnKeyPressed(e->{
            if((isValid(nameIn) && isValid(passIn)) && (b.loginInfo(nameIn.getText(), passIn.getText())) && (nameIn.getText().equals("SYSTEM_ADMIN")) ) {
                System.out.println(nameIn.getText());
                systemAdmin.systemAdminPage(nameIn.getText());
                window.close();
            }
            else if((isValid(nameIn) && isValid(passIn)) && b.loginInfo(nameIn.getText(), passIn.getText())) {
                System.out.println(nameIn.getText());
                home.homePage(nameIn.getText());
                window.close();
            }
            else {
                System.out.println("name "+nameIn.getText()+" Pass "+passIn.getText());
                invalid.setText("Invalid Username or Password");
            }
        });
        passIn.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER) {
                if ((isValid(nameIn) && isValid(passIn)) && (b.loginInfo(nameIn.getText(), passIn.getText())) && (nameIn.getText().equals("SYSTEM_ADMIN"))) {
                    System.out.println(nameIn.getText());
                    systemAdmin.systemAdminPage(nameIn.getText());
                    window.close();
                } else if ((isValid(nameIn) && isValid(passIn)) && b.loginInfo(nameIn.getText(), passIn.getText())) {
                    System.out.println(nameIn.getText());
                    home.homePage(nameIn.getText());
                    window.close();
                } else {
                    System.out.println("name " + nameIn.getText() + " Pass " + passIn.getText());
                    invalid.setText("Invalid Username or Password");
                }
            }
        });


        GridPane.setConstraints(but, 1, 3);
        gp.setMinWidth(200);

        gp.getChildren().addAll(name, nameIn, pass, passIn, but, invalid);
        gp.setStyle("-fx-background-color: skyblue;");

        Scene sce = new Scene(gp, 500,200);
        //Display window & wait for it to closed before returning
        window.setScene(sce);

        window.show();
        //window.showAndWait();

    }

    private static boolean isValid(TextField in) {
        try{
            String str=in.getText();
            boolean boo=false;
            for(int i=0; i<str.length(); i++)
            {
                char c=str.charAt(i);
                if((int)str.charAt(i)<48||(int)str.charAt(i)>123)
                {
                    boo=false;
                    invalid.setText("Invalid Character '"+c+"' found");
                    System.out.println("Invalid Character '"+c+"' found");
                    return false;
                }
                else
                    boo=true;
            }
            return boo;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }


    }


}

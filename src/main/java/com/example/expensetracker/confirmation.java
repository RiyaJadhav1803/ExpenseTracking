package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class confirmation {
    @FXML
    private Button confirmid;

    @FXML
    private Button noid;
   private static Stage previous;
    static String mail;
    public static void getdata4(Stage previousstage) {
        previous=previousstage;
    }
    public static void getdata1(String email){
        mail = email;
    }
    @FXML
    void onConfirmClick(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
        PreparedStatement p = connection.prepareStatement("Delete FROM register where email=?");
        p.setString(1, mail);
        p.executeQuery();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("EXPENSE TRACKING SYSTEM");
//        stage.setScene(scene);
//        stage.show();
      //  previous.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Show the previous stage again
        if (previous != null) {
            previous.show();
        }
    }
    @FXML
    void onnoclick(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("samples.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("EXPENSE TRACKING SYSTEM");
//        stage.setScene(scene);
//        stage.show();
  //      previous.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Show the previous stage again
        if (previous != null) {
            previous.show();
        }
    }

}

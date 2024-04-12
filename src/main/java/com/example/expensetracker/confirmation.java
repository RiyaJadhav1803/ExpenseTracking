package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","riya");
        String query = "Delete from register WHERE email = ?";

        PreparedStatement preparedStatement1 = connection.prepareStatement(query);

        preparedStatement1.setString(1, mail);
        preparedStatement1.executeQuery();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Show the previous stage again
        if (previous != null) {
            previous.show();
        }
    }
    @FXML
    void onnoclick(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Show the previous stage again
        if (previous != null) {
            previous.show();
        }
    }

}

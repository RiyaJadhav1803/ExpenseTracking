package com.example.expensetracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.sql.*;
import java.io.IOException;

public class adminController {
    @FXML
    private PasswordField passwordid;
    @FXML
    private Label labelid;
    @FXML
    private Button submitid;

    @FXML
    private TextField userid;

    @FXML
    private Button backid;

    @FXML
    void onbackclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("entry.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onsubmitclick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin(email ,password) VALUES(?,?)");
//        preparedStatement.setString(1, userid.getText());
//        preparedStatement.setString(2, passwordid.getText());
 //       preparedStatement.executeUpdate();
        String query="Select * from admin";
        PreparedStatement p1 = connection.prepareStatement(query);
        ResultSet rs1 = p1.executeQuery();
        int flag=0;
        while (rs1.next()) {
            if (rs1.getString("email").equals(userid.getText()) && rs1.getInt("password")==Integer.parseInt(passwordid.getText())) {
                System.out.println("YOU ARE LOGGED IN SUCCESSFULLY");
                flag=1;

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("info.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 790);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("EXPENSE TRACKING SYSTEM");
                stage.setScene(scene);
                stage.show();
                break;
            }
            else {
                continue;
            }
        }
        if(flag==0)
        {
            System.out.println("INVALID CREDENTIALS................PLZ try AGAIN........");
            labelid.setText("INVALID CREDENTIALS !");
        }
    }

}

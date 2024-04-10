package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
import java.io.IOException;
import java.sql.*;


public class HelloController{
    @FXML
    private Button create_id;
    @FXML
    private Button forgot_id;
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
    private Button login_id;


    @FXML
    private TextField password_id;

    @FXML
    private TextField text_id;

    @FXML
    private Label login_label;
    @FXML
    void create_button(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 790);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("EXPENSE TRACKING SYSTEM");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void onLoginClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String email = text_id.getText();
        if (!email.contains("@gmail.com")) {
            System.out.println("Invalid email format. Please provide a Gmail address.");
            login_label.setText("Invalid email format. Please provide a Gmail address.");
            return;
        }
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","riya");
        String query = "SELECT * FROM register WHERE email = ? AND pass=?";

        PreparedStatement preparedStatement1 = connection.prepareStatement(query);

        preparedStatement1.setString(1, text_id.getText());
        preparedStatement1.setInt(2,Integer.parseInt(password_id.getText()));
        ResultSet rs1 = preparedStatement1.executeQuery();
        int flag=0;
        while (rs1.next()) {
            if (rs1.getString("email").equals(text_id.getText()) && rs1.getInt("pass")==Integer.parseInt(password_id.getText())) {
                System.out.println("YOU ARE LOGGED IN SUCCESSFULLY");
                login_label.setText("LOGGED IN SUCCESSFULLY");
                flag=1;

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("samples.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 790);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                SamplesController.getdata(text_id.getText());
                confirmation.getdata1(text_id.getText());
                Voicecontroller.getdata(text_id.getText());
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
            login_label.setText("INVALID CREDENTIALS !");

        }
    }

    @FXML
    void onForgotClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forgot.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

}
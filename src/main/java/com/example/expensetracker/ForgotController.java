package com.example.expensetracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ForgotController {

    @FXML
    private Button goback_id;
    @FXML
    private Label verify_id_label;

    @FXML
    private TextField again_email;

    @FXML
    private PasswordField newconfirm_id;

    @FXML
    private PasswordField newpass_id;

    @FXML
    private Button submit_id;

    @FXML
    void OnSubmitClick(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        int flag = 0;
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
        if (newpass_id.getText().equals(newconfirm_id.getText())) {
            PreparedStatement preparedStatement1 = connection.prepareStatement("Update register set pass=? where email=?");
            PreparedStatement preparedStatement2 = connection.prepareStatement("Update register set confirm=? where email=?");
            preparedStatement1.setInt(1, Integer.parseInt(newpass_id.getText()));
            preparedStatement1.setString(2, again_email.getText());
            preparedStatement2.setInt(1, Integer.parseInt(newconfirm_id.getText()));
            preparedStatement2.setString(2, again_email.getText());
            preparedStatement1.executeQuery();
            preparedStatement2.executeQuery();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM register");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                System.out.println("Password set Successfully");
                verify_id_label.setText("Password set Successfully!");
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("pass"));
                System.out.println(rs.getString("confirm"));
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Password and confirm password must be same");
            verify_id_label.setText("Password not set Successfully. Try Again");
        }
    }
    @FXML
    void onGoBackClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

}

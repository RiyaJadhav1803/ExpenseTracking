package com.example.expensetracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class CreateController implements Initializable {
    @FXML
    private TextField contact_id;

    @FXML
    private Button Goback_id;

    @FXML
    private Label verify_id;

    @FXML
    private TextField email_id;

    @FXML
    private TextField name_id;

    @FXML
    private PasswordField confirm_id;
    @FXML
    private PasswordField pass_id;

    @FXML
    private Spinner<Integer> age_id;

    @FXML
    private Button submit_id;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinnerValueFactory.setValue(20);
        age_id.setValueFactory(spinnerValueFactory);
        age_id.setEditable(true);
    }
    @FXML
    void onSubmitButtonClick(ActionEvent event) throws Exception {
        if (pass_id.getText().equals(confirm_id.getText())) {
            try {
                System.out.println("Not yet");
                System.out.println("Not entered");
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO register(name, age, email, pass, confirm,contact,date1) VALUES(?, ? , ? , ? , ?, ?,sysdate)");
                preparedStatement.setString(1, name_id.getText());
                preparedStatement.setInt(2, age_id.getValue());
                preparedStatement.setString(3, email_id.getText());
                preparedStatement.setString(4, pass_id.getText());
                preparedStatement.setString(5, confirm_id.getText());
                preparedStatement.setString(6, contact_id.getText());
                preparedStatement.executeUpdate();
                PreparedStatement p = connection.prepareStatement("SELECT * FROM register");
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    System.out.println("NAME: " + rs.getString("name"));
                    System.out.println("age: " + rs.getString("age"));
                    System.out.println("email: " + rs.getString("email"));
                    System.out.println("pass : " + rs.getString("pass"));
                    System.out.println("confirm:  " + rs.getString("confirm"));
                    System.out.println("contact : "+rs.getString("contact"));
                    System.out.println("Date : "+rs.getString("date1"));
                    System.out.println("\n");
                }
                verify_id.setText("Account Created Successfully!");
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("PASSWORD DOESN'T MATCHES");
            verify_id.setText("Password & Confirm Password must be same!");
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
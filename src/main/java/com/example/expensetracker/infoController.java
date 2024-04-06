package com.example.expensetracker;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class infoController {

    @FXML
    private TableColumn<Book1,String> c1id;

    @FXML
    private TableColumn<Book1,String> c2id;

    @FXML
    private TableColumn<Book1,String> c3id;

    @FXML
    private TableView<Book1> tableid;
    @FXML
    private TableColumn<Book1,String > dateid;

//    @FXML
//    private TableView<Book2> t2id;

    @FXML
    private Button backid;
    @FXML
    void onbackclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();

    }
        @FXML
        void initialize() throws ClassNotFoundException, SQLException {
            c1id.setCellValueFactory(data -> data.getValue().nameprop());
            c2id.setCellValueFactory(data -> data.getValue().emailprop());
            dateid.setCellValueFactory(data->data.getValue().dateprop());
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
            PreparedStatement p = connection.prepareStatement("SELECT name,email,contact,date1 FROM register");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                    System.out.println("name: " + rs.getString("name"));
                    String n = rs.getString("name");
                    System.out.println("email: " + rs.getString("email"));
                    String  e = rs.getString("email");
                   System.out.println("date"+rs.getDate("date1"));
                   String  d1= String.valueOf(rs.getDate("date1"));
                    tableid.getItems().addAll(
                            new Book1(n,e,d1)
                    );
                    System.out.println("\n");
                }
        }
}
class Book1 {
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty d1;

    public Book1(String name, String email,String d1) {
        this.email = new SimpleStringProperty(email);
        this.name = new SimpleStringProperty(name);
        this.d1 = new SimpleStringProperty(d1);
    }

    public SimpleStringProperty nameprop() {
        return name;
    }

    public SimpleStringProperty emailprop() {
        return email;
    }
    public SimpleStringProperty dateprop() {
        return d1;
    }
}
package com.example.expensetracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Voicecontroller {
    @FXML
    private Button backid;
//    @FXML
//    private TableView<Books> t2id;

    @FXML
    private TableColumn<Book,String> dateid;
    @FXML
    private TextField textid;

    @FXML
    private Button totalid;
    @FXML
    private TableView<Book > table;
    @FXML
    private TableColumn<Book , Integer> c1id;

    @FXML
    private TableColumn<Book ,String> c2id;

    @FXML
    private TextField manualid;

    @FXML
    private Button okid;
    @FXML
    private TextField endid;
    @FXML
    private TextField startid;
    @FXML
    private TextField categoryid;

    @FXML
    private TextField voiceid;
    @FXML
    void onbackclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("samples.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();

    }
    static String mail=null;
public static void getdata(String email)
{
    mail=email;
}
    @FXML
    private Label labelid;
    int sum=0;
    @FXML
    void onokclick(ActionEvent event) throws ClassNotFoundException, SQLException {
        String text = manualid.getText();
        System.out.println(text);
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
       // String array[] = text.split(" ");
        int flag=0;
        int actualnum=0;
        String array=categoryid.getText();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if(c=='1' || c=='2'||c=='0'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'){
                    actualnum=(actualnum*10)+Integer.parseInt(String.valueOf(c));
                }
                else if(c=='+' || c=='-' ) {
                  labelid.setText("Invalid Number format");
                  flag=1;
                }
            }
            if(flag==0) {
                labelid.setText(" ");
                System.out.println(actualnum);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO manual(email,Cost,Category,Date2 ) VALUES(?,?,?,sysdate)");
                preparedStatement.setString(1, mail);
                preparedStatement.setInt(2, actualnum);
                preparedStatement.setString(3, array);
                preparedStatement.executeUpdate();
                PreparedStatement p = connection.prepareStatement("SELECT * FROM manual");
                ResultSet rs = p.executeQuery();

                String query = null;
                while (rs.next()) {
                    query = rs.getString("date2");
                    System.out.println(query);
                }
                sum = sum + actualnum;
                System.out.println("Inserted");
                Book newBook = new Book(actualnum, array, query);
                table.getItems().add(newBook);
               // textid.setText(String.valueOf(sum));
            }
    }
    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        c1id.setCellValueFactory(data -> data.getValue().titleProperty().asObject());
        c2id.setCellValueFactory(data -> data.getValue().authorProperty());
        dateid.setCellValueFactory(data -> data.getValue().dateprop());

        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
        PreparedStatement p = connection.prepareStatement("Select * from manual");
         ResultSet rs= p.executeQuery();
            while (rs.next()) {
                if (mail.equals(rs.getString("email"))) {
                    System.out.println("email: " + rs.getString("email"));
                    String e = rs.getString("email");
                    System.out.println("Cost: " + rs.getInt("Cost"));
                    int c = rs.getInt("Cost");
                    System.out.println("Category: " + rs.getString("Category"));
                    String Cat = rs.getString("Category");
                    System.out.println("date" + rs.getDate("date2"));
                    String d1 = String.valueOf(rs.getDate("date2"));
                    table.getItems().addAll(
                            new Book(c, Cat, d1)
                    );
//                sum=sum+c;
                    System.out.println("\n");
                }
            }
        }
    @FXML
    void ontotalclick(ActionEvent event) {
        String startDateText = startid.getText(); // Assuming textFieldStartDate is the text field for the start date
        String endDateText = endid.getText(); // Assuming textFieldEndDate is the text field for the end date

        // Convert start date and end date to java.util.Date objects
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        java.util.Date startDate;
        java.util.Date endDate;
        try {
            startDate = format.parse(startDateText);
            endDate = format.parse(endDateText);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parse exception
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya")) {
            // Prepare SQL statement to select rows from the table where date_column falls within the date range
            String sql = "SELECT * FROM manual WHERE TO_CHAR(Date2, 'DD-MON-YY') BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the start date and end date to the prepared statement
                preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
                System.out.println("in");
                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Sum up the values of the 'spent' column from the retrieved rows
                    int totalSpent = 0;
                    System.out.println("inin");
                    while (resultSet.next()) {
                        if(mail.equals(resultSet.getString("email"))) {
                            String category = resultSet.getString("Category");
                            int cost = resultSet.getInt("Cost");
                            System.out.println("Category: " + category); // Print the category for debugging
                            if (category.equals("Cashback")) {
                                totalSpent -= cost; // Subtracting cost for Cashback
                            } else {
                                totalSpent += cost; // Adding cost for other categories
                            }
                        }
                    }

                    // Display the total spent
                    System.out.println("Total spent from " + startDateText + " to " + endDateText + ": " + totalSpent);
                    textid.setText(String.valueOf(totalSpent));
                    // Update your UI with the total spent if needed
                    // textid.setText(String.valueOf(totalSpent));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
    @FXML
    void onLogoutClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }
}
 class Book {
    private  SimpleIntegerProperty cost;
    private  SimpleStringProperty cat;
     private  SimpleStringProperty d1;

    public Book(int cost, String cat,String d1) {
        this.cost = new SimpleIntegerProperty(cost);
        this.cat = new SimpleStringProperty(cat);
        this.d1 = new SimpleStringProperty(d1);
    }
    public SimpleIntegerProperty titleProperty() {
        return cost;
    }

    public SimpleStringProperty authorProperty() {
        return cat;
    }
     public SimpleStringProperty dateprop() {
         return d1;
     }
}
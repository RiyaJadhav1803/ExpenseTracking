package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.*;

public class SamplesController extends HelloController {
    @FXML
    private Button profile_id;
    @FXML
    private Text amt1;
    @FXML
    private Text cat1;
    @FXML
    private Text per1;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Label l1_id;
    @FXML
    private Label l2_id;
    @FXML
    private Label l3_id;
    @FXML
    private Label l4_id;
    @FXML
    private Label l5_id;
    @FXML
    private Label l6_id;
    @FXML
    private Label l7_id;
    @FXML
    private Label l8_id;
    @FXML
    private Label l9_id;
    @FXML
    private Button logoutid;
    @FXML
    private Button voice_id;
    @FXML
    private Label act_age_id;
    @FXML
    private Label act_contact_id;
    @FXML
    private Label act_email_id;
    @FXML
    private Label act_name_id;
    @FXML
    private Label age_id;
    @FXML
    private RadioButton prof_id;
    @FXML
    private TextField salary_id;
    @FXML
    private RadioButton stud_id;
    @FXML
    private RadioButton studpprof_id;
    @FXML
    private TextField t2;
    @FXML
    private TextField t3;
    @FXML
    private TextField t4;
    @FXML
    private TextField t5;
    @FXML
    private TextField t6;
    @FXML
    private TextField t7;
    @FXML
    private TextField t8;
    @FXML
    private TextField t9;
    @FXML
    private TextField t1;
    @FXML
    private ToggleGroup Type;
    @FXML
    private Label contact_id;
    @FXML
    private Label email_id;
    @FXML
    private Button submit_id;
    @FXML
    private Label name_id;
    @FXML
    private Label left_id;
    @FXML
    private Label rs_id;
    @FXML
    private Button total_id;
    @FXML
    private Button backid;

    @FXML
    private Button deleteid;
    private Stage previousStage;
    @FXML
    void ondeleteclick(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("confirmation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 477, 271);
        Stage stage=new Stage();
        confirmation.getdata4(previousStage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onLogoutclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onbackclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    static String mail;
    @FXML
    void onProfileClick(ActionEvent event) throws ClassNotFoundException, SQLException {
          name_id.setText("Name :");
          contact_id.setText("Contact :");
          email_id.setText("E-mail Id :");
          age_id.setText("Age :");
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
        PreparedStatement p = connection.prepareStatement("SELECT * FROM register where email=?");
        p.setString(1, mail);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            System.out.println("NAME: " + rs.getString("name"));
            act_name_id.setText(rs.getString("name"));
            System.out.println("Email: " + rs.getString("email"));
            act_email_id.setText(rs.getString("email"));
            System.out.println("Contact: " + rs.getLong("contact"));
            act_contact_id.setText(String.valueOf(rs.getLong("contact")));
            System.out.println("Age : " + rs.getInt("age"));
            act_age_id.setText(String.valueOf(rs.getInt("age")));
        }
    }
    public static void getdata(String email){
        mail = email;
    }

    @FXML
    void onSubmitClick(ActionEvent event) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "riya");
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sample(category , s_no,p_no,sppp_no ) VALUES(?,?,?,?)");
//        preparedStatement.setString(1, "ChildEducation");
//        preparedStatement.setInt(2,0);
//        preparedStatement.setInt(3,32);
//        preparedStatement.setInt(4,0);
        salary_id.getText();
        String query=null;
        int flag=0;
        if (stud_id.isSelected()) {
            query="Select category,s_no from sample where s_no!=0";
            flag=1;
        } else if (prof_id.isSelected()) {
            query="Select category,p_no from sample where p_no!=0";
            flag=2;
        } else if(studpprof_id.isSelected()) {
            query="Select category,sppp_no from sample where sppp_no!=0";
            flag=3;
        }
        PreparedStatement p1 = connection.prepareStatement(query);
        ResultSet rs1 = p1.executeQuery();
        int labelid=1;
        int b_no=1;
        while (rs1.next()) {
            Label ll = (Label) getClass().getDeclaredField("l" + labelid + "_id").get(this);
            Button bb = (Button) getClass().getDeclaredField("b" + b_no).get(this);
            ll.setText(rs1.getString("category"));
            if(flag==1) {
                bb.setText(rs1.getString("s_no"));
            }
            else if (flag==2) {
                bb.setText(rs1.getString("p_no"));
            }
            else if(flag==3) {
                bb.setText(rs1.getString("sppp_no"));
            }
            labelid++;
            b_no++;
        }
   b1.setVisible(true);
   b2.setVisible(true);
   b3.setVisible(true);
   b4.setVisible(true);
   b5.setVisible(true);
   b6.setVisible(true);
   b7.setVisible(true);
   b8.setVisible(true);
   b9.setVisible(true);
        t1.setVisible(true);
        t2.setVisible(true);
        t3.setVisible(true);
        t4.setVisible(true);
        t5.setVisible(true);
        t6.setVisible(true);
        t7.setVisible(true);
        t8.setVisible(true);
        t9.setVisible(true);
   amt1.setVisible(true);
   per1.setVisible(true);
   cat1.setVisible(true);
   total_id.setVisible(true);
        connection.close();
    }
    @FXML
    void onVoiceClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Voicerecognition.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 790);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("EXPENSE TRACKING SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void b1click(ActionEvent event) {
       int sal= Integer.parseInt(salary_id.getText());
       int b= Integer.parseInt(b1.getText());
       int num= (int) (sal*(0.01*b));
       t1.setText(String.valueOf(num));
    }
    @FXML
    void b2click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b2.getText());
        int num= (int) (sal*(0.01*b));
        t2.setText(String.valueOf(num));
    }
    @FXML
    void b3click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b3.getText());
        int num= (int) (sal*(0.01*b));
        t3.setText(String.valueOf(num));
    }
    @FXML
    void b4click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b4.getText());
        int num= (int) (sal*(0.01*b));
        t4.setText(String.valueOf(num));
    }
    @FXML
    void b5click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b5.getText());
        int num= (int) (sal*(0.01*b));
        t5.setText(String.valueOf(num));
    }
    @FXML
    void b6click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b6.getText());
        int num= (int) (sal*(0.01*b));
        t6.setText(String.valueOf(num));
    }
    @FXML
    void b7click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b7.getText());
        int num= (int) (sal*(0.01*b));
        t7.setText(String.valueOf(num));
    }
    @FXML
    void b8click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b8.getText());
        int num= (int) (sal*(0.01*b));
        t8.setText(String.valueOf(num));
    }
    @FXML
    void b9click(ActionEvent event) {
        int sal= Integer.parseInt(salary_id.getText());
        int b= Integer.parseInt(b9.getText());
        int num= (int) (sal*(0.01*b));
        t9.setText(String.valueOf(num));
    }
    @FXML
    void totalclick(ActionEvent event) throws NoSuchFieldException, IllegalAccessException {
        int total = 0;
        for (int i = 1; i <= 9; i++) {
            TextField t = (TextField) getClass().getDeclaredField("t" + i).get(this);
            if (!t.getText().isEmpty()) {
                total += Integer.parseInt(t.getText());
            }
        }
        rs_id.setText(String.valueOf(total)+"Rs");
        try {
            int salary = Integer.parseInt(salary_id.getText());
            int leftmoney = salary - total;
            left_id.setText("You are left with " + leftmoney + " Rs in balance");
        } catch (NumberFormatException e) {
            // Handle the case where salary is not a valid integer
            left_id.setText("Invalid salary input");
        }
    }
}

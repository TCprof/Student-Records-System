package sample;
/**
 * @date 28/1/2019
 * @version 3.0
 * @author Tom
 */
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class Controller {
    public TextField textPassword;
    public TextField textEmail;
    public Label textLabel;
    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionClass.getConnection();

    public TextField getTextPassword() {
        return textPassword;
    }

    /**
     * login first connects to the database, then tries to find the inputted email and password
     *if successful it loads home.fxml
     */
    public void login(){
        try{
            stmt = conn.createStatement();
            String userEmail = textEmail.getText();
            String userPass = textPassword.getText();
            String sql = "SELECT * FROM admin WHERE mail= '"+userEmail+"' && password = '"+ userPass+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                String foundType = rs.getString("password");
                System.out.println(foundType);
            }

            if(userEmail.equals(rs.getString("mail")) && userPass.equals(rs.getString("password"))){
                System.out.println("test1");
                JOptionPane.showMessageDialog(null, "Login Successful");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Home");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Login failed");
                dispose();
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        ConnectionClass connectionClass=new ConnectionClass();

    }
    //cancel button
    public void cancel(ActionEvent actionEvent){
        System.exit(0);

    }

}

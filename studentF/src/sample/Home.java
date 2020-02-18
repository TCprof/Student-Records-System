package sample;
/**
 * @date 30/1/2019
 * @version 2.0
 * @author Tom
 */

import java.awt.*;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.*;
import java.sql.*;

public class Home {
    public Label label;
    public  TableView<Info> tableStudent;
    public  TableColumn<Info,String> columnName;
    public  TableColumn<Info,String> columnAddress;
    public TextField findName;
    public TextField findAddress;
    public PreparedStatement stmt = null;
    public Button button;

    /**
     * button() adds data to the tableView.
     *It connects to the database and executes an SQL statement on it in order
     * to refresh the view.
     */
    public void button(){
        columnName.setCellValueFactory(new PropertyValueFactory<Info,String>("SName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Info,String>("Address"));
        ObservableList<Info> data = FXCollections.observableArrayList();
        try {
            Connection conn = ConnectionClass.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM info");
            while(rs.next()) {
                data.add(new Info(rs.getString("SName"), rs.getString("Address")));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }

    /**
     * delete() deletes from the database where the student name is the same as the one entered in the
     * text box.
     */
    public void delete(){
        Connection conn = ConnectionClass.getConnection();
        String qry = "delete from info where SName = (?)";
        String name = findName.getText();
        try{
            stmt = conn.prepareStatement(qry);
            stmt.setString(1,name);
            int i = stmt.executeUpdate();
            if(i == 1){
                System.out.println("Data deleted");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * insert() adds the name and address to the relevant table.
     */
    public void insert(){
        Connection conn = ConnectionClass.getConnection();
        String qry = ("insert into info (SName,Address) values(?,?)");
        String name = findName.getText();
        String address = findAddress.getText();
        try{
            stmt = conn.prepareStatement(qry);
            stmt.setString(1,name);
            stmt.setString(2,address);
            int i = stmt.executeUpdate();
            if(i == 1){
                System.out.println("Data inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

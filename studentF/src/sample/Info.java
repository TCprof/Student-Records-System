package sample;
/**
 * @date 30/1/2019
 * @version 1.0
 * @author Tom
 */

import javafx.beans.property.SimpleStringProperty;

public class Info {
   public SimpleStringProperty SName;
   public SimpleStringProperty address;

    /**
     * Needed for the tableview in the home page.
     * @param name The name of the student.
     * @param address The address of the student.
     */
    public Info(String name, String address){
        this.SName = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
    }
    public String getSName(){
        return SName.get();
    }
    public String getAddress(){
        return address.get();
    }

    public void setSName(String n){
        this.SName = new SimpleStringProperty(n);
    }
    public void setAddress(String a){
        this.address = new SimpleStringProperty(a);
    }

}

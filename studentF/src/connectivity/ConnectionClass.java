package connectivity;
/**
 * @date 28/1/2019
 * @version 1.0
 * @author Tom
 */
import java.sql.DriverManager;
import java.sql.Connection;
public class ConnectionClass {
    public Connection connection;

    /**
     *
     * @return connection Needed to get info from database
     */
    public static Connection getConnection(){
        String dbName="student";
        String userName="root";
        //omitted for security reasons
        String password="";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

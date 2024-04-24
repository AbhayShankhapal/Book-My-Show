import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management", "root", "Nashik@123");
            s = c.createStatement();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}

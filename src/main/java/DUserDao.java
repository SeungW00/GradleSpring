import java.sql.*;


/**
 * Created by Administrator on 2017-06-05.
 */
public class DUserDao extends UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spring","root","");
        return connection;

    }
}

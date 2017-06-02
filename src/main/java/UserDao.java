import java.sql.*;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDao {

    public User get(String id) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spring","root","");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ? ");
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user =new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;

    }

    public String add(User user) throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spring","root","");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) VALUES (?,?,?)");
        preparedStatement.setString(1,user.getId());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.executeUpdate();

        String id = user.getId();
        preparedStatement.close();
        connection.close();

        return id;

    }
}

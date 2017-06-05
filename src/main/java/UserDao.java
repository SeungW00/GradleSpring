import java.sql.*;

/**
 * Created by Administrator on 2017-05-30.
 */
public abstract class UserDao {

    public User get(int id) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ? ");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user =new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;

    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException ;

    public int add(User user) throws SQLException,ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name,password) VALUES (?,?)");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();

        int id = getLastInsertId(connection);
        preparedStatement.close();
        connection.close();

        return id;

    }

    private int getLastInsertId(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id() ");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        int id = resultSet.getInt(1);
        resultSet.close();
        return id;
    }
}


import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDao {

    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserDao() {
    }


    public User get(int id) throws SQLException, ClassNotFoundException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from users where id = ? ");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


        return user;

    }


    public int add(User user) throws SQLException,ClassNotFoundException{
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            int id = 0;
            try {
                connection = dataSource.getConnection();
                preparedStatement = connection.prepareStatement("insert into users(name,password) VALUES (?,?)");
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.executeUpdate();
                id = getLastInsertId(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            if (preparedStatement != null)
                try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (connection != null)
                try {

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }



        return id;

    }

    private int getLastInsertId(Connection connection) throws SQLException {

        ResultSet resultSet = null;
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id() ");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try{
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            if (connection != null)
                try {

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }


    }

}

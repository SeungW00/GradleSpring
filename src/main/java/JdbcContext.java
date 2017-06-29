import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public User jdbcContextWithStatementStrategyForGet(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

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




    public int jdbcContextWithStatementStrategyForInsert(StatementStrategy statementStrategy) {
        int id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

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
    public void jdbcContextWithStatementStrategyForDelete(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
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

}

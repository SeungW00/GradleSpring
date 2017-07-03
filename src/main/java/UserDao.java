
import java.sql.*;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDao {


    private JdbcContext jdbcContext;


    public UserDao() {
    }


    public int add(User user) throws SQLException, ClassNotFoundException {
        String query = "insert into users(name,password) VALUES (?,?)";
        Object[] params = new Object[]{user.getName(),user.getPassword()};
        StatementStrategy statementStrategy = update(query, params);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);

    }

    private StatementStrategy update(String query, Object[] params) {
        return connection -> {

                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(query);
                for (int i=1;i<=params.length;i++) {
                    preparedStatement.setObject(i,params[i-1]);
                }
                return preparedStatement;
            };
    }

    public User get(int id) throws SQLException, ClassNotFoundException {
        String query = "select * from users where id = ? ";
        Object[] params = new Object[]{id};
        StatementStrategy statementStrategy = update(query, params);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);

    }


    public void delete(int id) {
        String query = "delete from users where id = ?";
        Object[] params = new Object[]{id};
        StatementStrategy statementStrategy = update(query, params);
        jdbcContext.jdbcContextWithStatementStrategyForDelete(statementStrategy);


    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}

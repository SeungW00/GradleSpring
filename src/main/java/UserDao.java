
import java.sql.*;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDao {


    private JdbcContext jdbcContext;


    public UserDao() {
    }

    public int add(User user) throws SQLException,ClassNotFoundException{
        StatementStrategy statementStrategy = new addUserStatmentStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);

    }
    public User get(int id) throws SQLException, ClassNotFoundException{
        StatementStrategy statementStrategy = new GetUserStatementStarategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);

    }




    public void delete(int id) {
        StatementStrategy statementStrategy = new deleteStatmentStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForDelete(statementStrategy);


    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public class GetUserStatementStarategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("select * from users where id = ? ");
        preparedStatement.setInt(1,(int)object);
        return preparedStatement;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public class addUserStatmentStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement("insert into users(name,password) VALUES (?,?)");
        User user = (User)object;
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        return preparedStatement;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public class addUserStatmentStrategy implements StatementStrategy {
    private User user;
    public addUserStatmentStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("insert into users(name,password) VALUES (?,?)");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        return preparedStatement;
    }
}

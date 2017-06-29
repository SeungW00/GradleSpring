import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public class deleteStatmentStrategy implements StatementStrategy {
    private int id;


    public deleteStatmentStrategy(int id) {
        this.id=id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from users where id = ?");
        preparedStatement.setInt(1,id);
        return preparedStatement;

    }
}

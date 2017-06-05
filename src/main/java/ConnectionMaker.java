import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-05.
 */
public interface  ConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}

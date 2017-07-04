package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-29.
 */
public interface StatementStrategy {
    public PreparedStatement makeStatement(Connection connection) throws SQLException;


}

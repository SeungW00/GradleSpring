package dao;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017-07-04.
 */
public interface UserDao {
    int add(User user) throws SQLException, ClassNotFoundException;
    User get(int id) throws SQLException, ClassNotFoundException;
    void delete(int id) throws SQLException,ClassNotFoundException;
    void deleteAll();
    int getCount();


}

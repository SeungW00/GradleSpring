package dao;

import domain.User;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017-07-04.
 */
public interface UserDao {
    void add(User user) throws SQLException, ClassNotFoundException;
    void update(User user) throws SQLException, ClassNotFoundException;
    User get(String id) throws SQLException, ClassNotFoundException;
    void delete(String  id) throws SQLException,ClassNotFoundException;
    void deleteAll();
    int getCount();



}

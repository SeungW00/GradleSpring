
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.awt.*;
import java.sql.*;

import static java.lang.Math.toIntExact;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDao {


    private JdbcTemplate jdbcTemplate;


    public UserDao() {
    }


    public int add(User user) throws SQLException, ClassNotFoundException {
        String sql = "insert into users(name,password) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{

           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            return preparedStatement;
        },keyHolder);

        return keyHolder.getKey().intValue();
    }


    public User get(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from users where id = ? ";
        Object[] args = new Object[]{id};
        User user1 = null;
        user1 = jdbcTemplate.queryForObject(sql,args,(resultSet,i) ->{
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));


            return user;

        });
        return user1;

    }


    public void delete(int id) throws ClassNotFoundException,SQLException{
        String sql = "delete from users where id = ?";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sql,args);


    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}

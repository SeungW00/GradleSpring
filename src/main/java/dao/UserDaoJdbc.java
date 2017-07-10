package dao;

import domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.sql.*;
import java.util.List;

/**
 * Created by Administrator on 2017-05-30.
 */
public class UserDaoJdbc implements UserDao{


    private JdbcTemplate jdbcTemplate;
    private domain.Level level;

    private RowMapper<User> userRowMapper = (resultSet, rowNum)->{
        return getUser(resultSet);
    };

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setLevel(domain.Level.valueOf(resultSet.getInt("Level")));
        user.setLogin(resultSet.getInt("Login"));
        user.setRecommend(resultSet.getInt("Recommend"));
        return user;
    }


    public UserDaoJdbc() {
    }


    public void add(User user) throws SQLException, ClassNotFoundException {
      /*  String sql = "insert into users(id,name,password,Level,Login,Recommend) VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{

           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setInt(4, user.getLevel().intValue());
            preparedStatement.setInt(5,user.getLogin());
            preparedStatement.setInt(6,user.getRecommend());
            return preparedStatement;

        },keyHolder);*/
        this.jdbcTemplate.update(
                "insert into users(id, name, password, level, login, recommend) " +
                        "values(?,?,?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(),
                user.getLevel().intValue(), user.getLogin(), user.getRecommend());


    }

    @Override
    public void update(User user) throws SQLException, ClassNotFoundException {
        jdbcTemplate.update("update users set name = ?, password =?, level=?, login=?, recommend =? where id = ?"
                ,user.getName(),user.getPassword(),user.getLevel().intValue(),user.getLogin(),user.getRecommend(),user.getId());


    }


    public User get(String id) throws SQLException, ClassNotFoundException {
        /*
        String sql = "select * from users where id = ? ";
        Object[] args = new Object[]{id};
        User user1 = null;

            user1 = jdbcTemplate.queryForObject(sql,args,(resultSet,i) ->{
                return getUser(resultSet);

            });


        return user1;
*/
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[] {id}, this.userRowMapper);

    }


    public void delete(String id) throws SQLException,ClassNotFoundException{
        String sql = "delete from users where id = ?";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sql,args);


    }



    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from users;");
    }


    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from users where password = ?",new Object[]{"!234"},Integer.class);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select *from users order by id ",userRowMapper);
    }
}

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017-05-30.
 */
        public class UserDaoTest {
            @Test
            public void get() throws SQLException, ClassNotFoundException{
                UserDao userDao =new UserDao();
                String id = "hsw";
                String name = "SeungWoo";
                String password = "1234";

        User user =userDao.get(id);
        assertEquals(id,user.getId());
        assertEquals(name,user.getName());
        assertEquals(password,user.getPassword());

    }
    @Test
    public void add() throws SQLException, ClassNotFoundException{
            UserDao userDao = new UserDao();
        String id = "hsw1111";
        String name = "SeungWoo1";
        String password = "1234";

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setPassword(password);

        userDao.add(user);
        User resultUser = userDao.get(id);
        assertEquals(id,resultUser.getId());
        assertEquals(name,resultUser.getName());
        assertEquals(password,resultUser.getPassword());


    }
}

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
}

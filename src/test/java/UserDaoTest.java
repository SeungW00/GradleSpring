
import dao.User;
import dao.UserDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


/**
 * Created by Administrator on 2017-05-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/daoFactory.xml")
        public class UserDaoTest {

        @Autowired
        private UserDao userDao;


        @Before
        public void setup(){

      //     ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
         //   userDao = context.getBean("userDao", UserDaoJdbc.class);
        }

            @Test
            public void get() throws SQLException, ClassNotFoundException {

                int id = 91;
                String name = "SeungWoo";
                String password = "1234";

        User user =userDao.get(id);
        assertEquals(id,user.getId());
        assertEquals(name,user.getName());
        assertEquals(password,user.getPassword());

    }
    @Test
    public void add() throws SQLException, ClassNotFoundException{


        String name = "SeungWoo";
        String password = "1234";

            User user = new User();
            user.setName(name);
            user.setPassword(password);

        int id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertEquals(id,resultUser.getId());
        assertEquals(name,resultUser.getName());
        assertEquals(password,resultUser.getPassword());


    }
    @Test
    public void delete() throws SQLException, ClassNotFoundException {

        String name = "SeungWoo";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        int id = userDao.add(user);
        userDao.delete(id);

        assertNull(userDao.get(id));
    }

    @Test
    public void deleteAll() throws SQLException,ClassNotFoundException{
        userDao.deleteAll();
        assertThat(userDao.getCount(),is(0));
    }





}


import domain.Level;
import domain.User;
import dao.UserDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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


        private User user1;
        private Level level;


    @Autowired
        private UserDao userDao;


        @Before
        public void setup(){

      //     ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
         //   userDao = context.getBean("userDao", UserDaoJdbc.class);

          user1 = new User("hsw2021","SeungWoo","123",Level.GOLD,1,3);

        }

    @Test(expected = EmptyResultDataAccessException.class)
    public void get() throws SQLException, ClassNotFoundException {

        User user =userDao.get(user1.getId());
        assertEquals(user1.getId(),user.getId());
        assertEquals(user1.getName(),user.getName());
        assertEquals(user1.getPassword(),user.getPassword());
        assertEquals(user1.getLevel(),user.getLevel());
        assertEquals(user1.getLogin(),user.getLogin());
        assertEquals(user1.getRecommend(),user.getRecommend());


    }
    @Test
    public void add() throws SQLException, ClassNotFoundException{


        userDao.add(user1);
        User resultUser = userDao.get(user1.getId());
        assertEquals(user1.getId(),resultUser.getId());
        assertEquals(user1.getName(),resultUser.getName());
        assertEquals(user1.getPassword(),resultUser.getPassword());
        assertEquals(user1.getLevel(),resultUser.getLevel());
        assertEquals(user1.getLogin(),resultUser.getLogin());
        assertEquals(user1.getRecommend(),resultUser.getRecommend());

    }
    @Test(expected = EmptyResultDataAccessException.class)
    public void delete() throws SQLException, ClassNotFoundException {


        userDao.delete(user1.getId());

        assertNull(userDao.get(user1.getId()));
    }

    @Test
    public void deleteAll() throws SQLException,ClassNotFoundException{
        userDao.deleteAll();
        assertThat(userDao.getCount(),is(0));
    }

    @Test(expected = DataAccessException.class)
    public void duplciatekey(){

    }


    @Test
    public void update() throws SQLException,ClassNotFoundException{



    }




}

import dao.UserDao;
import domain.Level;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static service.UserService.MIN_RECOMMEND_FOR_GOLD;


/**
 * Created by Administrator on 2017-07-06.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/daoFactory.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "qwesas", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
                new User("joytouch", "qwesd", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
                new User("erwins", "qwesda", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1),
                new User("madnite1", "qwesd", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
                new User("green", "qweasdd", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
        );
    }

    @Test
    public void bean() throws SQLException, ClassNotFoundException {


        userService.upgradeLevels();
        assertThat(this.userService,is(notNullValue()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();

        User userWithLevel = users.get(3);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);


        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevel.getLevel(),is(userWithLevel.getLevel()));
        assertThat(userWithoutLevel.getLevel(),is(Level.BASIC));

    }



}

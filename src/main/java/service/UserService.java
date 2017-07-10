package service;

import dao.UserDao;
import domain.Level;
import domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017-07-06.
 */
public class UserService {

    UserDao userDao;
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels() throws SQLException, ClassNotFoundException {
          List<User> users = userDao.getAll();
        for(User user : users){
            if(canUpgradeLevel(user)){
                upgradeLevel(user);
            }
        }
    }

    private void upgradeLevel(User user) throws SQLException, ClassNotFoundException {
        user.upgradeLevel();
        userDao.update(user);
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        switch (currentLevel){
            case BASIC:return (user.getLogin()>=MIN_LOGCOUNT_FOR_SILVER)    ;
            case SILVER:return (user.getRecommend()>=MIN_RECOMMEND_FOR_GOLD);
            case GOLD:return false;
            default:throw new IllegalArgumentException("Unknown Level" + currentLevel);
        }
            }

    public void add(User user) throws SQLException, ClassNotFoundException {
        if (user.getLevel()==null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);

    }
}

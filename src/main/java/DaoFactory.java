/**
 * Created by Administrator on 2017-06-10.
 */
public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private DConnectionMaker getConnectionMaker() {
        return new DConnectionMaker();
    }
}

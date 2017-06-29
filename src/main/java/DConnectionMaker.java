import com.mysql.jdbc.Driver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;



/**
 * Created by Administrator on 2017-06-05.
 */

public class DConnectionMaker extends SimpleDriverDataSource{
    public DConnectionMaker(){
        this.setDriverClass(Driver.class);
        this.setUrl("jdbc:mysql://localhost/spring");
        this.setUsername("root");
        this.setPassword("");
    }
}
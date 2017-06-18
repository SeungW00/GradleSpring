import com.mysql.jdbc.Driver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017-06-05.
 */
public class NConnectionMaker extends SimpleDriverDataSource{

  public NConnectionMaker(){
      this.setDriverClass(Driver.class);
      this.setUrl("jdbc:mysql://localhost/spring");
      this.setUsername("root");
      this.setPassword("");
  }
}

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class MysqlTest extends TestCase {

	@Test
	public void testDataSource() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("数据源：" + ac.getBean("dataSource"));

		DataSource da = (DataSource) ac.getBean("dataSource");
		Connection con = da.getConnection();
		System.out.println("数据库连接：" + con);
	}
}

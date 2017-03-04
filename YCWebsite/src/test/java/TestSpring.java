

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class TestSpring extends TestCase {

	
	public void testSpring2() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds=(DataSource) ac.getBean("dataSource");
		
		Connection c=ds.getConnection();
		System.out.println(c);
	}
	
	
}

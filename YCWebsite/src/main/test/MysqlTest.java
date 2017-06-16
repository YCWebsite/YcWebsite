import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.News;
import com.yc.bean.Projects;
import com.yc.biz.NewsBiz;
import com.yc.biz.ProjectsBiz;

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
	
	@Test
	public void testfindAllJobDetails() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ProjectsBiz pb=(ProjectsBiz) ac.getBean("projectsBizImpl");
		List<Projects> list=pb.selectAllProjects(null);
		System.out.println(list.size());
	}
	
	@Test
	public void testfindAllNews() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		NewsBiz nb=(NewsBiz) ac.getBean("newsBizImpl");
		List<News> list=nb.selectAllNews(null);
		System.out.println(list.size());
	}
}

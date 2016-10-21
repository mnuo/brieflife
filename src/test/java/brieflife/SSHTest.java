/**
 * SSHTest.java created at 2016年10月21日 下午1:08:47
 */
package brieflife;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author saxon
 */
public class SSHTest {
	private ApplicationContext acx = null;
	@Test
	public void testDataSource() throws Exception{
		acx = new ClassPathXmlApplicationContext("config/spring.xml");
		System.out.println("数据源:"+acx);
		
		DataSource datasource = acx.getBean(DataSource.class);
		System.out.println("打开数据连接: " + datasource.getConnection().toString());
		
		SessionFactory sessionFactory = acx.getBean(SessionFactory.class);
		System.out.println("SessionFactory: " + sessionFactory);
	}
}

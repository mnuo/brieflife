/**
 * SSHTest.java created at 2016年10月21日 下午1:08:47
 */
package brieflife;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.entity.BlTextMessage;
import com.mnuo.brieflife.service.BaseMessageService;

/**
 * @author saxon
 */
public class SSHTest {
	private ApplicationContext acx = null;
	@Test
	public void testDataSource() throws Exception{
		acx = new ClassPathXmlApplicationContext("config/spring.xml");
		BaseMessageService baseMessageService = acx.getBean(BaseMessageService.class);
		BlTextMessage textMessage = new BlTextMessage();
    	textMessage.setFromUserName("fff");
    	textMessage.setMsgId("fff");
    	textMessage.setMsgType("fff");
    	textMessage.setContent("fff");
    	textMessage.setCreateTime(new Date());
    	BlPosition position = new BlPosition();
    	position.setId(1);
    	textMessage.setBlPosition(position);
    	baseMessageService.save(textMessage);

	}
}

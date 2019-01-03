import com.xxx.demo.Application;
import com.xxx.demo.models.mongdb.notification.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MongoDBTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        Notification no = new Notification();
//        no.setToken("4698619a70a97c1bbf177b31775fa3a22dd1ff27ca948d8d1cf1c21353a39f82");
        no.setTitle("我是标题");
        no.setSubtitle("我是子标题");
        no.setBody("我是内容");
        mongoTemplate.insert(no);
    }

}

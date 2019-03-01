//import com.xxx.demo.Application;
//import com.xxx.demo.repository.redistemplate.RedisRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
//public class RedisTest {
//    @Autowired
//    RedisRepository redisRepository;
//
//    @Test
//    public void test() {
//
//        // redisRepository.set("testIntent","test");
//
//        String token = null;
//        List<String> list = new ArrayList<>();
//        list.add("15000000001");
//        list.add("17755306716");
//        list.add("15900798629");
//
//        for (String phoneNum : list) {
//            token = String.valueOf(redisRepository.get(phoneNum));
//            System.out.println(token);
//
//        }
//    }
//}

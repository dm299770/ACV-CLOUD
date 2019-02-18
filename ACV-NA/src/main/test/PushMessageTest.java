import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PushMessageTest {

    @Test
    public void test(){
        //System.out.println(XingeApp.pushTokenIos(2200320892L, "b03fd5eb7016da05516a52554a171b4c", "测试", "4698619a70a97c1bbf177b31775fa3a22dd1ff27ca948d8d1cf1c21353a39f82", XingeApp.IOSENV_DEV));
//        XingeApp xinge = new XingeApp(2200320892L, "b03fd5eb7016da05516a52554a171b4c");
        XingeApp xinge = new XingeApp(2200325348L, "034ce5157bef26852e0c7b4182babcee");
        MessageIOS mess = new MessageIOS();
        mess.setExpireTime(86400);
        mess.setAlert("ios test");
        mess.setBadge(1);
        mess.setSound("beep.wav");
        Map<String,Object> custom = new HashMap<String,Object>();
        custom.put("key","value");
        mess.setCustom(custom);
        //表示一个允许推送的时间闭区
        TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);

        mess.addAcceptTime(acceptTime);
        JSONObject obj = new JSONObject();
        JSONObject aps = new JSONObject();
        JSONObject alert = new JSONObject();
        alert.put("title","系统");
        alert.put("body","尊敬的用户您好，英菲尼迪系统检测扫您的爱车在2108-11-01油量报警，请对您的爱车及时进行检查");
        aps.put("sound", "beep.wav");
        aps.put("alert", alert);
        aps.put("badge", 1);
        aps.put("content-available", 1);
        aps.put("mutable-content",1);
        obj.put("xg_media_resources","https://img5.duitang.com/uploads/item/201411/07/20141107164412_v284V.jpeg");
        obj.put("aps", aps);
        mess.setRaw(obj.toString());
        //messageIOS.setCustom(new HashMap<String,Object>());
        System.out.println( xinge.pushSingleDevice("3ee9abf89c9134f3ff57c3b006dcbb38c34fb514b08a0125e69c9328cde8a90c", mess,XingeApp.IOSENV_DEV).toString());

    }

}



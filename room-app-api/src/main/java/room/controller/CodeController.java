package room.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class CodeController {
    //短信平台相关参数
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "105715";
    private String appSecret = "1edd3f88-c76f-49e2-98e3-58532a773dd8";

    public boolean getCode(String memPhone, HttpSession httpSession){
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            String result = client.send(memPhone, "您的验证码为:" + code + "，该码有效期为2分钟，该码只能使用一次!");
            System.out.println("result:"+result);
            json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return  false;
            }

            httpSession.setAttribute("code",code);
            httpSession.setAttribute("createTime",System.currentTimeMillis());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


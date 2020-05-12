package room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import room.common.utils.SendEmail;

@RestController
 public class test {

    @GetMapping("/send")
    public static void send(){
        String subject = "test";
        String text = "验证码";
        String email = "kurie_21@163.com";
        SendEmail sendEmail = new SendEmail();
        sendEmail.SendSimpleEmail(subject,text,email);
     }
}

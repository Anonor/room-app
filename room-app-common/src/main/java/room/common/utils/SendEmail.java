package room.common.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;

//发送邮件工具类
public class SendEmail {
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("kurie_21@foxmail.com");
        mailSender.setPassword("aksmijdkgagkjjhc");
        return mailSender;
    }
    //一个简单的邮件,只有text信息
    /**
     * @Description:
     * @Param: [subject:标题, text:内容, email:接收人的邮件地址]
     * @return: void
     * @Author: Kurie21
     * @Date: 2020/5/11
     */
    public void SendSimpleEmail(String subject, String code, String email) {

        JavaMailSenderImpl javaMailSender = JavaMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject); //邮件的主题
        mailMessage.setText(code);
        mailMessage.setTo(email); //发送给谁
        mailMessage.setFrom(Objects.requireNonNull(javaMailSender.getUsername())); //谁发送的
        javaMailSender.send(mailMessage);
    }
}


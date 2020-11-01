package com.lxp.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    @Autowired
    private JavaMailSender mailSender;

    //发送人固定
    @Value("${spring.mail.username}")
    private String from;

    // 实现一个方法调用，需要告诉方法你发给谁，主题是什么，内容是什么
    // 所以主要是要构建MimeMessage，在SpringBoot中给我们MimeMessageHelper这边帮助类帮助我们进行构建这个对象
    public void sendMail(String to, String subject, String content){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e){
            logger.error("发送邮件失败：" + e.getMessage());
        }
    }
}

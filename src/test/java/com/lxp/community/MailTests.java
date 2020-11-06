package com.lxp.community;

import com.lxp.community.util.MailClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    @Autowired
    private MailClient mailClient;

    // 主动的调用thymeleaf模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail(){
        mailClient.sendMail("npuxpli@mail.nwpu.edu.cn", "TEST","Welcome");
    }

    // 你发送html模板的话，需要利用thymeleaf模板，我在下面给了一个demo，
    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username", "sunday");
        // 给模板传参数，要和demo里面申明的一样
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        // 得到内容，把context传到demo里面
        mailClient.sendMail("npuxpli@mail.nwpu.edu.cn", "HTML",content);
    }
}

package com.lxp.community;

import com.lxp.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以✨赌博，可以嫖✨娼，可以吸毒，可以开票，哈哈哈哈";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }

}

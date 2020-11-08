package com.lxp.community.config;

import com.lxp.community.quartz.AlphaJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

// 配置 -> 数据库 -> 调用
@Configuration
public class QuartzConfig {

    // BeanFactory -> 容器的顶层接口
    // FactoryBean可简化Bean的实例化过程
    // 1 Spring通过FactoryBean封装了（某些）Bean的实例化过程
    // 2 将FactoryBean装配到Spring容器里
    // 3 将FactoryBean注入给其他Bean
    // 4 该Bean得到的是FactoryBean所管理的对象实例（JobDetail）

    // 配置JobDetail
    //@Bean
    public JobDetailFactoryBean alphaJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);
        factoryBean.setName("alphaJob");
        factoryBean.setGroup("alphaJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    // 配置Trigger（SimpleTriggerFactoryBean，CronTriggerFactoryBean）
    //@Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);
        factoryBean.setName("alphaTrigger");
        factoryBean.setGroup("alphaTriggerGroup");
        factoryBean.setRepeatInterval(3000);
        // 存job的状态
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }
}

package com.benmei.sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;


public class MyApplicationPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationPreparedEvent.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext ctx = event.getApplicationContext();
        Environment environment = ctx.getEnvironment();
        String[] profiles = environment.getActiveProfiles();
        String prot = environment.getProperty("server.port");
        String path = environment.getProperty("server.context-path");
        String ip = environment.getProperty("server.address");
        logger.info("\n-------------------------------------\n Current Profile:{}. Server run at: http://{}:{}{}\n-------------------------------------\n", profiles[0], ip, prot,path);
    }


}
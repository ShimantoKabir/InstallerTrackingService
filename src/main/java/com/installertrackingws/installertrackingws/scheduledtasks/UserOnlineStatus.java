package com.installertrackingws.installertrackingws.scheduledtasks;

import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Component
public class UserOnlineStatus {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Scheduled(fixedRate = 5000)
    public void updateUserOnlineStatus() throws ParseException {
        UserUtl userUtility = new UserUtl();
        userUtility.updateUserOnlineStatus(entityManagerFactory);
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(10);
        return scheduler;
    }

}

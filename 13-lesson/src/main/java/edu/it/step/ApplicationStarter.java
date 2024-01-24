package edu.it.step;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        User user = context.getBean(User.class);
        log.info(user.toString());

        context.close();
    }
}

package edu.it.step;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        User user = context.getBean(User.class);
        user.setFirstName("John");
        user.setFirstName("Wick");
        user.setAge(54);

        user.getAddress().setStreet("Secret street");

        log.info(user.getFirstName());

        context.close();
    }
}

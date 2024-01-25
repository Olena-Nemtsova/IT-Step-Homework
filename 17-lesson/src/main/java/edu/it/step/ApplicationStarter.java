package edu.it.step;

import edu.it.step.exception.NotFoundException;
import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserStorage userStorage = context.getBean(UserStorage.class);
        try {
            log.info(userStorage.getUserNameById(1));
            log.info(userStorage.getUserNameById(-1));

        } catch (NotFoundException e) {
            log.warning(e.getMessage());
        }

        context.close();
    }
}

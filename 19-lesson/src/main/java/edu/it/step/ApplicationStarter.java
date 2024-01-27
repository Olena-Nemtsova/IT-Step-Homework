package edu.it.step;

import edu.it.step.service.PresidentConsoleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        PresidentConsoleService presidentConsoleService = context.getBean(PresidentConsoleService.class);
        presidentConsoleService.start();

        context.close();
    }
}

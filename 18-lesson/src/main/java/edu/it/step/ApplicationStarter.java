package edu.it.step;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        ContactConsoleService contactConsoleService = context.getBean(ContactConsoleService.class);
        contactConsoleService.startConsole();

        context.close();
    }
}

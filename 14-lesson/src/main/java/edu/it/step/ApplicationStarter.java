package edu.it.step;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class ApplicationStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Player player = context.getBean(Player.class);
        log.info(player.toString());

        context.close();
    }
}

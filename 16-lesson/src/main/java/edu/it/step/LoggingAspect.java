package edu.it.step;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* *.*(..)) && (args(String) || args(Integer))")
    public void logMethodCall(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        String formattedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(new Date());

        StringBuilder log = new StringBuilder();
        log.append(formattedDate)
                .append(" ")
                .append(method)
                .append("(");

        for (Object arg : args) {
            log.append(arg).append(", ");
        }
        if (args.length > 0) {
            log.delete(log.length() - 2, log.length());
        }
        log.append(")");

        writeLogToFile(log.toString());
    }

    private void writeLogToFile(String logMessage) {
        try (FileWriter writer = new FileWriter("16-lesson/src/main/resources/method_calls.log", true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

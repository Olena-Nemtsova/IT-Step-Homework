package edu.it.step;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(pointcut = "execution(String *..*.*(..)) && args(Integer)", returning = "result")
    public void logMethodCall(JoinPoint joinPoint, String result) {
        logMethod(joinPoint, result, null);
    }

    @AfterThrowing(pointcut = "execution(String *..*.*(..)) && args(Integer)", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        logMethod(joinPoint, null, exception);
    }

    private void logMethod(JoinPoint joinPoint, String result, Exception exception) {
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        String formattedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(new Date());

        StringBuilder log = new StringBuilder();
        log.append("Time of call method: ")
                .append(formattedDate).append("\n")
                .append("Name of method: ")
                .append(method).append("\n")
                .append("Argument of method: ")
                .append(args[0]).append("\n");

        if (result != null) {
            log.append("Return value of method: ").append(result).append("\n");
        }

        if (exception != null) {
            log.append("Message of exception: ").append(exception.getMessage()).append("\n");
        }
        log.append("\n");

        writeLogToFile(log.toString());
    }

    private void writeLogToFile(String logMessage) {
        try (FileWriter writer = new FileWriter("17-lesson/src/main/resources/method_calls.log", true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

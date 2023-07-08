package edu.it.step.task2;

import java.util.concurrent.atomic.AtomicReference;

public class Outer {
    public void outerMethod() {
        //final String[] localField = {"localField"};
        //StringBuilder localField = new StringBuilder("localField");
        AtomicReference<String> localField = new AtomicReference<>("localField");
        class Local {
            private void localMethod() {
                //System.out.println(localField[0]);
                System.out.println(localField);

                // String newValue = "new value";
                // localField.setLength(newValue.length());
                // localField.replace(0, newValue.length(), newValue);

                localField.set("new value");
            }
        }
        Local local = new Local();
        local.localMethod();

        System.out.println(localField);
        //System.out.println(localField[0]);

        /*
            локальні змінні методу втрачаються як тільки метод завершується,
            але навіть після завершення методу, локальний внутрішній об'єкт все ще може бути у хіпі
            і метод, що використовує локальну змінну може бути викликаний після виконання методу,
            в якому був створений локальний внутрішній клас,
            отже компілятор копіює локальну змінну, що використовується в ньому, в хіп,
            тому вона не підлягає зміні
        */
    }
}


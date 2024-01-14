package edu.it.step;

import java.util.UUID;

public class ApplicationStarter {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        User user = User.builder()
                .id(1)
                .uuid(String.valueOf(UUID.randomUUID()))
                .name("Bob White")
                .email("some@gmail.com")
                .password("so-secure")
                .role(new Role(1, "USER"))
                .build();

        print(user);

        System.out.println(user.equals(copy(user)));
        System.out.println(user.equals(difficultCopy(user)));
    }

    public static void print(Object obj) {
        Class<?> clazz = obj.getClass();

        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            AllowPrint allowPrint = field.getAnnotation(AllowPrint.class);

            if (allowPrint != null && allowPrint.value()) {
                field.setAccessible(true);

                try {
                    System.out.println(field.getName() + ": " + field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object copy(Object original) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = original.getClass();
        Object copy = clazz.newInstance();

        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                field.set(copy, field.get(original));
            }
        }

        return copy;
    }

    public static Object difficultCopy(Object original) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = original.getClass();
        Object copy = clazz.newInstance();

        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                field.set(copy, field.get(original));
            } else {
                Object originalFieldValue = field.get(original);

                if (originalFieldValue != null) {
                    Object nestedCopy = difficultCopy(originalFieldValue);
                    field.set(copy, nestedCopy);
                }
            }
        }
        return copy;
    }
}

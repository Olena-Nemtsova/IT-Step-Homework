package edu.it.step;

import java.util.*;

public class UserArrayList implements Iterable<User> {
    private User[] arr = new User[0];

    public User get(int index) {
        return arr[index];
    }

    public int size() {
        return arr.length;
    }

    public void add(User user) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = user;
    }

    public void addToStart(User user) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        System.arraycopy(arr, 0, arr, 1, arr.length - 1);
        arr[0] = user;
    }

    public void delete() {
        arr = Arrays.copyOf(arr, arr.length - 1);
    }

    public void deleteFromStart() {
        arr = Arrays.copyOfRange(arr, 1, arr.length);
    }

    public void delete(int index) {
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        delete();
    }

    @Override
    public Iterator<User> iterator() {
        return new Iterator<>() {
            int position;

            @Override
            public boolean hasNext() {
                return position < arr.length;
            }

            @Override
            public User next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[position++];
            }
        };
    }
}

package edu.it.step;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements MyList<T> {
    private T[] arr;

    public MyListImpl() {
        @SuppressWarnings("unchecked") T[] array = (T[]) new Object[0];

        arr = array;
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public void add(T item) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = item;
    }

    @Override
    public void addToStart(T item) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        System.arraycopy(arr, 0, arr, 1, arr.length - 1);
        arr[0] = item;
    }

    @Override
    public void delete() {
        arr = Arrays.copyOf(arr, arr.length - 1);
    }

    @Override
    public void delete(int index) {
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        delete();
    }

    @Override
    public void deleteFromStart() {
        arr = Arrays.copyOfRange(arr, 1, arr.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int position;

            @Override
            public boolean hasNext() {
                return position < arr.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[position++];
            }
        };
    }
}

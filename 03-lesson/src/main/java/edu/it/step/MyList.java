package edu.it.step;

public interface MyList<T> extends Iterable<T> {
    T get(int index);

    int size();

    void add(T item);

    void addToStart(T item);

    void delete();

    void delete(int index);

    void deleteFromStart();

}

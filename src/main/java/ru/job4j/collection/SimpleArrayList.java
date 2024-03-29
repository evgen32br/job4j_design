package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public SimpleArrayList(int capacity) {
        if (capacity == 0) {
            this.container = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            this.container = (T[]) new Object[capacity];
        }
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            extension();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    private void extension() {
        container = Arrays.copyOf(container, size * 2);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            final int expectedCount = modCount;
            int cursor;

            @Override
            public boolean hasNext() {
                if (expectedCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}

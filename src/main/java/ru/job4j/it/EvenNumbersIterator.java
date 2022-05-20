package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;
    private int evenIndex;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                evenIndex = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (hasNext()) {
            index = evenIndex + 1;
            return data[evenIndex];
        }
        throw new NoSuchElementException();
    }
}

package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void addFirst() {
        ForwardLinked<Integer> forward = new ForwardLinked<>();
        forward.addFirst(1);
        forward.addFirst(2);
        forward.addFirst(3);
        forward.addFirst(4);
        Iterator<Integer> it = forward.iterator();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    void addFirstAndAddLast() {
        ForwardLinked<Integer> forward = new ForwardLinked<>();
        forward.addFirst(1);
        forward.add(1);
        forward.addFirst(2);
        forward.addFirst(3);
        forward.add(3);
        forward.addFirst(4);
        Iterator<Integer> it = forward.iterator();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(3);
    }
}
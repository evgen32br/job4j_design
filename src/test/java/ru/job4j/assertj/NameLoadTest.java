package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void parseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void validateNotSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("one  two"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: one  two does not contain the symbol \"=\"");
    }

    @Test
    void validateNotKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("= one two"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: = one two does not contain a key");
    }

    @Test
    void validateNotValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("two ="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: two = does not contain a value");
    }
}

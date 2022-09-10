package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkListElementThree() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> arr = simpleConvert.toList("zero", "two", "four", "six", "eight", "ten");
        assertThat(arr).hasSize(6)
                .element(3)
                .isNotNull()
                .isNotEqualTo("four")
                .isEqualTo("six");
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> arr = simpleConvert.toList("zero", "two", "four", "eight", "ten", null);
        assertThat(arr).hasSize(6)
                .startsWith("zero", "two")
                .containsNull()
                .containsAnyOf("two", "ten", null)
                .first().isEqualTo("zero")
                .satisfies(e -> {
                    assertThat(e).endsWith("o");
                    assertThat(e).startsWith("z");
                    assertThat(e).hasLineCount(1);
                    assertThat(e).hasSize(4);
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> arr = simpleConvert.toSet("zero", "two", "four", "eleven", "zero", "two", null);
        assertThat(arr).hasSize(5)
                .containsExactlyInAnyOrder("zero", "two", "four", null, "eleven")
                .containsNull()
                .doesNotContain("eight")
                .containsSequence("zero", null);
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> arr = simpleConvert.toMap("zero", "two", "four", "zero", "two");
        assertThat(arr).hasSize(3)
                .containsKey("four")
                .doesNotContainKey("eleven")
                .containsValue(1)
                .doesNotContainValue(8)
                .containsEntry("zero", 0)
                .containsEntry("four", 2);
    }
}
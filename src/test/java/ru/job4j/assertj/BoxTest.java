package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(2, 10);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVerticesPositive() {
        Box box = new Box(4, 5);
        assertThat(box.getNumberOfVertices()).isPositive();
    }

    @Test
    void getNumberOfVerticesNegative() {
        Box box = new Box(3, 5);
        assertThat(box.getNumberOfVertices()).isNegative();
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(4, 5);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void getNumberOfVerticesMinusOne() {
        Box box = new Box(2, 5);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 20);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getAreaSqrt() {
        Box box = new Box(4, 5);
        assertThat(box.getArea())
                .isCloseTo(43.3d, withPrecision(0.01d));
    }

    @Test
    void getAreaPi() {
        Box box = new Box(0, 5);
        assertThat(box.getArea())
                .isCloseTo(314.1d, withPrecision(0.1d));
    }

    @Test
    void getAreaCube() {
        Box box = new Box(6, 5);
        assertThat(box.getArea())
                .isCloseTo(150d, withPrecision(0.1d));
    }

    @Test
    void getAreaDefault() {
        Box box = new Box(3, 5);
        assertThat(box.getArea()).isEqualTo(0);

    }
}
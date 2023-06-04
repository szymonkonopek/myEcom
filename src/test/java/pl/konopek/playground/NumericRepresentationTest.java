package pl.konopek.playground;

import org.junit.jupiter.api.Test;

public class NumericRepresentationTest {

    @Test
    void lestCheckDouble() {
        double a = 1.101;
        double b = -2.109;
        double c = a - b;
        System.out.println(b-a);
    }

    @Test
    void lestCheckFloats() {
        float a = 0.003f;
        float b = 0.001f;
        float c = b - a;
        System.out.println(c);
    }
}

package de.re.utility;

import java.util.Random;

public final class Maths {
    private Maths(){
    }

    public static final double INFINITY = Float.POSITIVE_INFINITY;

    public static final double PI = 3.1415926535897932385;

    public static float degreesToRadians(float degrees) {
        return (float) (degrees * PI / 180.0f);
    }

    public static float randomFloat() {
        return new Random().nextFloat();
    }

    public static float randomFloat(float min, float max) {
        return min + (max-min) * randomFloat();
    }

    public static float clamp(float x, float min, float max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}

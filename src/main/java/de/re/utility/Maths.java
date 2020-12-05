package de.re.utility;

public final class Maths {
    private Maths(){
    }

    public static final double INFINITY = Float.POSITIVE_INFINITY;

    public static final double PI = 3.1415926535897932385;

    public static float degreesToRadians(float degrees) {
        return (float) (degrees * PI / 180.0f);
    }
}

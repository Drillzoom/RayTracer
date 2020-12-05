package de.re.utility;

import de.re.common.Vec3;

import java.io.IOException;
import java.io.Writer;

public final class Vectors {
    private Vectors() {
    }

    // TODO: 05.12.2020 Implementation correct?
    public static void out(Writer out, Vec3 v) throws IOException {
        out.write(v.e[0] + " " + v.e[1] + " " + v.e[2]);
    }

    public static Vec3 add(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] + v.e[0], u.e[1] + v.e[1], u.e[2] + v.e[2]);
    }

    public static Vec3 sub(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] - v.e[0], u.e[1] - v.e[1], u.e[2] - v.e[2]);
    }

    public static Vec3 mul(Vec3 u, Vec3 v) {
        return new Vec3(u.e[0] * v.e[0], u.e[1] * v.e[1], u.e[2] * v.e[2]);
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 mul(Vec3 v, float t) {
        return v.mul(t);
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 div(Vec3 v, float t) {
        return v.mul(1/t);
    }

    public static float dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.e[0]
             + u.e[1] * v.e[1]
             + u.e[2] * v.e[2];
    }

    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(
                u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]
        );
    }

    // TODO: 05.12.2020 Implementation correct?
    public static Vec3 unitVector(Vec3 v) {
        return v.div(v.length());
    }

    // TODO: 05.12.2020 This class or Vec3 class?
    public Vec3 negate(Vec3 v) {
        return new Vec3(-v.e[0], -v.e[1], -v.e[2]);
    }
}

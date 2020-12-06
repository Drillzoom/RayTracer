package de.re.common;

public class Vec3 {
    public float[] e = {0.0f, 0.0f, 0.0f};

    public Vec3() {
    }

    public Vec3(float e) {
        this.e = new float[]{e, e, e};
    }

    public Vec3(Vec3 v) {
        e = new float[]{v.e[0], v.e[1], v.e[2]};
    }

    public Vec3(float e0, float e1, float e2) {
        e = new float[]{e0, e1, e2};
    }

    public float x() {
        return e[0];
    }

    public float y() {
        return e[1];
    }

    public float z() {
        return e[2];
    }

    public float get(int i) {
        return e[i];
    }

    public Vec3 add(Vec3 v) {
        e[0] += v.e[0];
        e[1] += v.e[1];
        e[2] += v.e[2];
        return this;
    }

    public Vec3 sub(Vec3 v) {
        e[0] -= v.e[0];
        e[1] -= v.e[1];
        e[2] -= v.e[2];
        return this;
    }

    public Vec3 mul(float t) {
        e[0] *= t;
        e[1] *= t;
        e[2] *= t;
        return this;
    }

    public Vec3 div(float t) {
        return this.mul(1/t);
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public float lengthSquared() {
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }
}

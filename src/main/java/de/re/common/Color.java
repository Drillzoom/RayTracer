package de.re.common;

public class Color extends Vec3 {
    public Color() {
    }

    public Color(float c) {
        super(c);
    }

    public Color(Vec3 v) {
        super(v);
    }

    public Color(Color c) {
        super(c);
    }

    public Color(float r, float g, float b) {
        super(r, g, b);
    }

    public float r() {
        return e[0];
    }

    public float g() {
        return e[1];
    }

    public float b() {
        return e[2];
    }

    public void overwrite(Color c) {
        this.e[0] = c.e[0];
        this.e[1] = c.e[1];
        this.e[2] = c.e[2];
    }
}

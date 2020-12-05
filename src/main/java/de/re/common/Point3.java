package de.re.common;

public class Point3 extends Vec3 {
    public Point3() {
    }

    public Point3(Vec3 v) {
        super(v);
    }

    public Point3(Point3 p) {
        super(p);
    }

    public Point3(float x, float y, float z) {
        super(x, y, z);
    }
}

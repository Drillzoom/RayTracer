package de.re.model;

import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.utility.Vectors;

public class Camera {
    private Point3 origin;

    private Point3 lowerLeftCorner;

    private Vec3 horizontal;

    private Vec3 vertical;

    public Camera() {
        float aspectRatio = 16.0f / 9.0f;
        float viewportHeight = 2.0f;
        float viewportWidth = aspectRatio * viewportHeight;
        float focalLength = 1.0f;

        origin = new Point3(0.0f, 0.0f, 0.0f);
        horizontal = new Vec3(viewportWidth, 0.0f, 0.0f);
        vertical = new Vec3(0.0f, viewportHeight, 0.0f);

        Vec3 sub1 = Vectors.sub(origin, Vectors.div(horizontal, 2.0f));
        Vec3 sub2 = Vectors.sub(sub1, Vectors.div(vertical, 2.0f));
        lowerLeftCorner = new Point3(Vectors.sub(sub2, new Vec3(0.0f, 0.0f, focalLength)));
    }

    public Ray getRay(float u, float v) {
        Vec3 add1 = Vectors.add(lowerLeftCorner, Vectors.mul(horizontal, u));
        Vec3 add2 = Vectors.add(add1, Vectors.mul(vertical, v));
        return new Ray(origin, Vectors.sub(add2, origin));
    }
}

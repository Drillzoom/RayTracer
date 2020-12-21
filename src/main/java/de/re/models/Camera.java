package de.re.models;

import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.utility.Maths;
import de.re.utility.Vectors;

public class Camera {
    private Point3 origin;

    private Point3 lowerLeftCorner;

    private Vec3 horizontal;

    private Vec3 vertical;

    public Camera(Point3 lookFrom, Point3 lookAt, Vec3 vUp, float fov, float aspectRatio) {
        float theta = Maths.degreesToRadians(fov);
        float h = (float) Math.tan(theta/2.0f);
        float viewportHeight = 2.0f * h;
        float viewportWidth = aspectRatio * viewportHeight;

        Vec3 w = Vectors.unitVector(Vectors.sub(lookFrom, lookAt));
        Vec3 u = Vectors.unitVector(Vectors.cross(vUp, w));
        Vec3 v = Vectors.cross(w, u);

        origin = lookFrom;
        horizontal = u.mul(viewportWidth);
        vertical = v.mul(viewportHeight);
        Vec3 sub1 = Vectors.sub(origin, Vectors.div(horizontal, 2.0f));
        Vec3 sub2 = Vectors.sub(sub1, Vectors.div(vertical, 2.0f));
        lowerLeftCorner = new Point3(Vectors.sub(sub2, w));
    }

    public Ray getRay(float s, float t) {
        Vec3 add1 = Vectors.add(lowerLeftCorner, Vectors.mul(horizontal, s));
        Vec3 add2 = Vectors.add(add1, Vectors.mul(vertical, t));
        return new Ray(origin, Vectors.sub(add2, origin));
    }
}

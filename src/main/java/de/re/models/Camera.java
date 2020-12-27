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

    private Vec3 u, v, w;

    protected float lensRadius;

    public Camera(Point3 lookFrom, Point3 lookAt, Vec3 vUp, float fov, float aspectRatio, float aperture, float focusDist) {
        float theta = Maths.degreesToRadians(fov);
        float h = (float) Math.tan(theta/2.0f);
        float viewportHeight = 2.0f * h;
        float viewportWidth = aspectRatio * viewportHeight;

        w = Vectors.unitVector(Vectors.sub(lookFrom, lookAt));
        u = Vectors.unitVector(Vectors.cross(vUp, w));
        v = Vectors.cross(w, u);

        origin = lookFrom;
        horizontal = Vectors.mul(u, focusDist * viewportWidth);
        vertical = Vectors.mul(v, focusDist * viewportHeight);
        Vec3 sub1 = Vectors.sub(origin, Vectors.div(horizontal, 2.0f));
        Vec3 sub2 = Vectors.sub(sub1, Vectors.div(vertical, 2.0f));
        lowerLeftCorner = new Point3(Vectors.sub(sub2, Vectors.mul(w, focusDist)));

        lensRadius = aperture / 2.0f;
    }

    public Ray getRay(float s, float t) {
        Vec3 rd = Vectors.randomInUnitDisk().mul(lensRadius);
        Vec3 mul1 = Vectors.mul(u, rd.x());
        Vec3 mul2 = Vectors.mul(v, rd.y());
        Vec3 offset = Vectors.add(mul1, mul2);

        Vec3 add1 = Vectors.add(lowerLeftCorner, Vectors.mul(horizontal, s));
        Vec3 add2 = Vectors.add(add1, Vectors.mul(vertical, t));
        Vec3 sub1 = Vectors.sub(add2, origin);
        return new Ray(new Point3(Vectors.add(origin, offset)), Vectors.sub(sub1, offset));
    }
}

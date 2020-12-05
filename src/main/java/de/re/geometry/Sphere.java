package de.re.geometry;

import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.models.Ray;
import de.re.utility.Vectors;

public class Sphere implements Hittable {
    public Point3 center;

    public float radius;

    public Sphere(Point3 center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray r, float tMin, float tMax, HitRecord rec) {
        Vec3 oc = Vectors.sub(r.origin, center);
        float a = r.direction.lengthSquared();
        float halfB = Vectors.dot(oc, r.direction);
        float c = oc.lengthSquared() - radius*radius;

        float discriminant = halfB*halfB - a*c;
        if (discriminant < 0) {
            return false;
        }
        float sqrtd = (float) Math.sqrt(discriminant);

        // Nearest root that lies in the acceptable range
        float root = (-halfB - sqrtd) / a;
        if (root < tMin || tMax < root) {
            root = (-halfB + sqrtd) / a;
            if (root < tMin || tMax < root) {
                return false;
            }
        }

        rec.t = root;
        rec.point = r.at(rec.t);
        Vec3 outwardNormal = Vectors.sub(rec.point, center).div(radius);
        rec.setFaceNormal(r, outwardNormal);

        return true;
    }
}

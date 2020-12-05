package de.re.model;

import de.re.common.Point3;
import de.re.common.Vec3;

public class Ray {
    public Point3 origin;

    public Vec3 direction;

    public Ray() {
    }

    public Ray(Point3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Point3 at(float t) {
        return (Point3) origin.add(direction.mul(t));
    }
}

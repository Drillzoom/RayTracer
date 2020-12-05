package de.re.model;

public interface Hittable {
    boolean hit(Ray r, float tMin, float tMax, HitRecord rec);
}

package de.re.geometry;

import de.re.models.Ray;

public interface Hittable {
    boolean hit(Ray r, float tMin, float tMax, HitRecord rec);
}

package de.re.materials;

import de.re.common.Color;
import de.re.geometry.HitRecord;
import de.re.models.Ray;

public interface Material {
    boolean scatter(Ray rIn, HitRecord rec, Color attenuation, Ray scattered);
}

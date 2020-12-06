package de.re.materials;

import de.re.common.Color;
import de.re.common.Vec3;
import de.re.geometry.HitRecord;
import de.re.models.Ray;
import de.re.utility.Vectors;

public class Lambertian implements Material {
    public Color albedo;

    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Color attenuation, Ray scattered) {
        Vec3 scatterDirection = Vectors.add(rec.normal, Vectors.randomUnitVector());

        if (scatterDirection.nearZero()) {
            scatterDirection = rec.normal;
        }

        scattered.overwrite(new Ray(rec.point, scatterDirection));
        attenuation.overwrite(albedo);
        return true;
    }
}

package de.re.materials;

import de.re.common.Color;
import de.re.common.Vec3;
import de.re.geometry.HitRecord;
import de.re.models.Ray;
import de.re.utility.Vectors;

public class Metal implements Material {
    public Color albedo;

    public float fuzz;

    public Metal(Color albedo, float fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz < 1.0f ? fuzz : 1.0f;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Color attenuation, Ray scattered) {
        Vec3 reflected = Vectors.reflect(Vectors.unitVector(rIn.direction), rec.normal);
        scattered.overwrite(new Ray(rec.point, reflected.add(Vectors.randomInUnitSphere().mul(fuzz))));
        attenuation.overwrite(albedo);
        return Vectors.dot(scattered.direction, rec.normal) > 0;
    }
}

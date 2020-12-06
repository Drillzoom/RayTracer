package de.re.materials;

import de.re.common.Color;
import de.re.common.Vec3;
import de.re.geometry.HitRecord;
import de.re.models.Ray;
import de.re.utility.Vectors;

public class Metal implements Material {
    public Color albedo;

    public Metal(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Color attenuation, Ray scattered) {
        Vec3 reflected = Vectors.reflect(Vectors.unitVector(rIn.direction), rec.normal);
        scattered.overwrite(new Ray(rec.point, reflected));
        attenuation.overwrite(albedo);
        return Vectors.dot(scattered.direction, rec.normal) > 0;
    }
}

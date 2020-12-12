package de.re.materials;

import de.re.common.Color;
import de.re.common.Vec3;
import de.re.geometry.HitRecord;
import de.re.models.Ray;
import de.re.utility.Maths;
import de.re.utility.Vectors;

public class Dielectric implements Material {
    private float ir; // Index of refraction (typically air = 1.0, glass = 1.3–1.7, diamond = 2.4)

    public Dielectric(float ir) {
        this.ir = ir;
    }

    @Override
    public boolean scatter(Ray rIn, HitRecord rec, Color attenuation, Ray scattered) {
        attenuation.overwrite(new Color(1.0f));
        float refractionRatio = rec.frontFace ? (1.0f/ir) : ir;

        Vec3 unitDirection = Vectors.unitVector(rIn.direction);
        float cosTheta = Math.min(Vectors.dot(Vectors.negate(unitDirection), rec.normal), 1.0f);
        float sinTheta = (float) Math.sqrt(1.0f - cosTheta*cosTheta);

        boolean cannotRefract = refractionRatio * sinTheta > 1.0f;
        Vec3 direction;

        if (cannotRefract || reflectance(cosTheta, refractionRatio) > Maths.randomFloat()) {
            direction = Vectors.reflect(unitDirection, rec.normal);
        } else {
            direction = Vectors.refract(unitDirection, rec.normal, refractionRatio);
        }

        scattered.overwrite(new Ray(rec.point, direction));
        return true;
    }

    private static float reflectance(float cosine, float refRatio) {
        // Schlick's approximation for reflectance
        float r0 = (1.0f-refRatio) / (1.0f+refRatio);
        r0 = r0*r0;
        return (float) (r0 + (1.0f-r0) * Math.pow((1.0f - cosine), 5.0f));
    }
}

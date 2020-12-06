package de.re.utility;

import de.re.common.Color;
import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.geometry.HitRecord;
import de.re.geometry.Hittable;
import de.re.models.Ray;

import java.io.IOException;
import java.io.Writer;

public final class Colors {
    private Colors() {

    }

    public static void writeColor(Writer out, Color pixelColor, int samplesPerPixel) throws IOException {
        float r = pixelColor.r();
        float g = pixelColor.g();
        float b = pixelColor.b();

        // Divide the color by the number of samples & gamma-correct for gamma=2.0
        float scale = 1.0f / samplesPerPixel;
        r = (float) Math.sqrt(scale * r);
        g = (float) Math.sqrt(scale * g);
        b = (float) Math.sqrt(scale * b);

        int ir = (int) (256 * Maths.clamp(r, 0.0f, 0.999f));
        int ig = (int) (256 * Maths.clamp(g, 0.0f, 0.999f));
        int ib = (int) (256 * Maths.clamp(b, 0.0f, 0.999f));

        // Write translated [0, 255] value of each color component
        out.write(ir + " " + ig + " " + ib + "\n");
    }

    public static Color rayColor(Ray r, Hittable world, int depth) {
        HitRecord rec = new HitRecord();

        // If we've exceeded the ray bounce limit, no more light is returned
        if (depth <= 0) {
            return new Color();
        }

        if (world.hit(r, 0.001f, (float) Maths.INFINITY, rec)) {
            Ray scattered = new Ray();
            Color attenuation = new Color();
            if (rec.material.scatter(r, rec, attenuation, scattered)) {
                return new Color(Vectors.mul(attenuation, rayColor(scattered, world, depth-1)));
            }

            return new Color();
        }

        Vec3 unitDirection = Vectors.unitVector(r.direction);
        float t = 0.5f * (unitDirection.y() + 1.0f);
        Color c1 = (Color) new Color(1.0f).mul(1.0f - t);
        Color c2 = (Color) new Color(0.5f, 0.7f, 1.0f).mul(t);
        return (Color) c1.add(c2);
    }
}

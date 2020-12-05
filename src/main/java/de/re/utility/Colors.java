package de.re.utility;

import de.re.common.Color;
import de.re.common.Vec3;
import de.re.model.HitRecord;
import de.re.model.Hittable;
import de.re.model.Ray;

import java.io.IOException;
import java.io.Writer;

public final class Colors {
    private Colors() {

    }

    public static void writeColor(Writer out, Color pixelColor, int samplesPerPixel) throws IOException {
        float r = pixelColor.r();
        float g = pixelColor.g();
        float b = pixelColor.b();

        // Divide the color by the number of samples
        float scale = 1.0f / samplesPerPixel;
        r *= scale;
        g *= scale;
        b *= scale;

        int ir = (int) (256 * Maths.clamp(r, 0.0f, 0.999f));
        int ig = (int) (256 * Maths.clamp(g, 0.0f, 0.999f));
        int ib = (int) (256 * Maths.clamp(b, 0.0f, 0.999f));

        // Write translated [0, 255] value of each color component
        out.write(ir + " " + ig + " " + ib + "\n");
    }

    public static Color rayColor(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        if (world.hit(r, 0, (float) Maths.INFINITY, rec)) {
            return new Color(Vectors.add(rec.normal, new Color(1.0f, 1.0f, 1.0f)).mul(0.5f));
        }

        Vec3 unitDirection = Vectors.unitVector(r.direction);
        float t = 0.5f * (unitDirection.y() + 1.0f);
        Color c1 = (Color) new Color(1.0f, 1.0f, 1.0f).mul(1.0f - t);
        Color c2 = (Color) new Color(0.5f, 0.7f, 1.0f).mul(t);
        return (Color) c1.add(c2);
    }
}

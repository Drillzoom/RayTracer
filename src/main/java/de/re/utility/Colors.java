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

    public static void writeColor(Writer out, Color pixelColor) throws IOException {
        int r = (int) (255.999f * pixelColor.r());
        int g = (int) (255.999f * pixelColor.g());
        int b = (int) (255.999f * pixelColor.b());

        out.write(r + " " + g + " " + b + "\n");
    }

    public static Color rayColor(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        if (world.hit(r, 0, (float) Maths.INFINITY, rec)) {
            Vec3 n = rec.normal;
            return (Color) new Color(n.x()+1, n.y()+1, n.z()+1).mul(0.5f);
        }

        Vec3 unitDirection = Vectors.unitVector(r.direction);
        float t = 0.5f * (unitDirection.y() + 1.0f);
        Color c1 = (Color) new Color(1.0f, 1.0f, 1.0f).mul(1.0f - t);
        Color c2 = (Color) new Color(0.5f, 0.7f, 1.0f).mul(t);
        return (Color) c1.add(c2);
    }
}

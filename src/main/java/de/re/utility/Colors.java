package de.re.utility;

import de.re.common.Color;
import de.re.common.Point3;
import de.re.common.Vec3;
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

    public static Color rayColor(Ray r) {
        if (hitSphere(new Point3(0.0f, 0.0f, -1.0f), 0.5f, r)) {
            return new Color(1.0f, 0.0f, 0.0f);
        }

        Vec3 unitDirection = Vectors.unitVector(r.direction);
        float t = 0.5f * (unitDirection.y() + 1.0f);
        Color c1 = (Color) new Color(1.0f, 1.0f, 1.0f).mul(1.0f - t);
        Color c2 = (Color) new Color(0.5f, 0.7f, 1.0f).mul(t);
        return (Color) c1.add(c2);
    }

    private static boolean hitSphere(Point3 center, float radius, Ray r) {
        Vec3 oc = Vectors.sub(r.origin, center);
        float a = Vectors.dot(r.direction, r.direction);
        float b = 2.0f * Vectors.dot(oc, r.direction);
        float c = Vectors.dot(oc, oc) - radius*radius;
        float discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }
}

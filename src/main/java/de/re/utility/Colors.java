package de.re.utility;

import de.re.common.Color;
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
        Vec3 unitDirection = Vectors.unitVector(r.direction);
        float t = 0.5f * (unitDirection.y() + 1.0f);
        Color c1 = (Color) new Color(1.0f, 1.0f, 1.0f).mul(1.0f - t);
        Color c2 = (Color) new Color(0.5f, 0.7f, 1.0f).mul(t);
        return (Color) c1.add(c2);
    }
}

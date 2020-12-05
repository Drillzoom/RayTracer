package de.re;

import de.re.common.Color;
import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.model.Ray;
import de.re.utility.Colors;
import de.re.utility.Vectors;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Image
        Path imagePath = Paths.get(args[0]);
        final float aspectRatio = 16.0f / 9.0f;
        final int imageWidth = 400;
        final int imageHeight = (int) (imageWidth / aspectRatio);

        // Camera
        float viewportHeight = 2.0f;
        float viewportWidth = aspectRatio * viewportHeight;
        float focalLength = 1.0f;

        Point3 origin = new Point3(0.0f, 0.0f, 0.0f);
        Vec3 horizontal = new Vec3(viewportWidth, 0.0f, 0.0f);
        Vec3 vertical = new Vec3(0.0f, viewportHeight, 0.0f);

        Vec3 sub1 = Vectors.sub(origin, Vectors.div(horizontal, 2.0f));
        Vec3 sub2 = Vectors.sub(sub1, Vectors.div(vertical, 2.0f));
        Vec3 lowerLeftCorner = Vectors.sub(sub2, new Vec3(0.0f, 0.0f, focalLength));

        // Render
        try (BufferedWriter bw = Files.newBufferedWriter(imagePath)) {
            bw.write("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = imageHeight-1; j >= 0; j--) { // int j = 0; j < imageHeight; j++
                System.out.println("\rScanlines remaining: " + j);
                for (int i = 0; i < imageWidth; i++) {
                    float u = (float) i / (imageWidth-1);
                    float v = (float) j / (imageHeight-1);

                    Vec3 add1 = Vectors.add(lowerLeftCorner, Vectors.mul(horizontal, u));
                    Vec3 add2 = Vectors.add(add1, Vectors.mul(vertical, v));
                    Ray r = new Ray(origin, Vectors.sub(add2, origin));

                    Color pixelColor = Colors.rayColor(r);
                    Colors.writeColor(bw, pixelColor);
                }
            }
        } catch (IOException e) {
            System.err.println("\nError while printing image.\n");
            e.printStackTrace();
        }

        System.out.println("\nDone.\n" + imagePath.toAbsolutePath() + "\n");
    }
}

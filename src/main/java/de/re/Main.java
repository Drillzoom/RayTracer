package de.re;

import de.re.common.Color;
import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.materials.Dielectric;
import de.re.materials.Lambertian;
import de.re.materials.Material;
import de.re.materials.Metal;
import de.re.models.Camera;
import de.re.geometry.HittableList;
import de.re.models.Ray;
import de.re.geometry.Sphere;
import de.re.utility.Colors;
import de.re.utility.Maths;

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
        final int samplesPerPixel = 100;
        final int maxDepth = 50;

        // World
        HittableList world = new HittableList();

        Material materialGround = new Lambertian(new Color(0.8f, 0.8f, 0.0f));
        Material materialCenter = new Lambertian(new Color(0.1f, 0.2f, 0.5f));
        Material materialLeft = new Dielectric(1.5f);
        Material materialRight = new Metal(new Color(0.8f, 0.6f, 0.2f), 0.0f);

        world.add(new Sphere(new Point3(0.0f, -100.5f, -1.0f), materialGround, 100.0f));
        world.add(new Sphere(new Point3(0.0f, 0.0f, -1.0f), materialCenter, 0.5f));
        world.add(new Sphere(new Point3(-1.0f, 0.0f, -1.0f), materialLeft, 0.5f));
        world.add(new Sphere(new Point3(-1.0f, 0.0f, -1.0f), materialLeft, -0.45f));
        world.add(new Sphere(new Point3(1.0f, 0.0f, -1.0f), materialRight, 0.5f));

        // Camera
        Camera camera = new Camera(new Point3(-2.0f, 2.0f, 1.0f), new Point3(0.0f, 0.0f, -1.0f), new Vec3(0.0f, 1.0f, 0.0f), 90.0f, aspectRatio);

        // Render
        try (BufferedWriter bw = Files.newBufferedWriter(imagePath)) {
            bw.write("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = imageHeight-1; j >= 0; j--) { // int j = 0; j < imageHeight; j++
                System.out.println("\rScanlines remaining: " + j);
                for (int i = 0; i < imageWidth; i++) {
                    Color pixelColor = new Color();
                    for (int s = 0; s < samplesPerPixel; s++) {
                        float u = (i + Maths.randomFloat()) / (imageWidth - 1);
                        float v = (j + Maths.randomFloat()) / (imageHeight - 1);

                        Ray r = camera.getRay(u, v);
                        pixelColor.add(Colors.rayColor(r, world, maxDepth));
                    }
                    Colors.writeColor(bw, pixelColor, samplesPerPixel);
                }
            }
        } catch (IOException e) {
            System.err.println("\nError while printing image.\n");
            e.printStackTrace();
        }

        System.out.println("\nDone.\n" + imagePath.toAbsolutePath() + "\n");
    }
}

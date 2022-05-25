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
        final float aspectRatio = 3.0f / 2.0f;
        final int imageWidth = 1200;
        final int imageHeight = (int) (imageWidth / aspectRatio);
        final int samplesPerPixel = 500;
        final int maxDepth = 50;

        // World
        HittableList world = randomScene();

        // Camera
        Point3 lookFrom = new Point3(13.0f, 2.0f, 3.0f);
        Point3 lookAt = new Point3();
        Vec3 vUp = new Vec3(0.0f, 1.0f, 0.0f);
        float distToFocus = 10.0f;
        float aperture = 0.1f;

        Camera camera = new Camera(lookFrom, lookAt, vUp, 20.0f, aspectRatio, aperture, distToFocus);

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

    private static HittableList randomScene() {
        HittableList world = new HittableList();

        Material groundMaterial = new Lambertian(new Color(0.5f));
        world.add(new Sphere(new Point3(0.0f, -1000.0f, 0.0f), groundMaterial, 1000.0f));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++){
                float chooseMaterial = Maths.randomFloat();
                Point3 center = new Point3(a + 0.9f*Maths.randomFloat(), 0.2f, b + 0.9f*Maths.randomFloat());

                if (Vectors.sub(center, new Point3(4.0f, 0.2f, 0.0f)).length() > 0.9f) {
                    Material sphereMaterial;

                    if (chooseMaterial < 0.8f) {
                        // Diffuse
                        Color albedo = new Color(Vectors.mul(Vectors.random(), Vectors.random()));
                        sphereMaterial = new Lambertian(albedo);
                        world.add(new Sphere(center, sphereMaterial, 0.2f));
                    } else if (chooseMaterial < 0.95f) {
                        // Metal
                        Color albedo = new Color(Vectors.random(0.5f, 1.0f));
                        float fuzz = Maths.randomFloat(0.0f, 0.5f);
                        sphereMaterial = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, sphereMaterial, 0.2f));
                    } else {
                        // Glass
                        sphereMaterial = new Dielectric(1.5f);
                        world.add(new Sphere(center, sphereMaterial, 0.2f));
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5f);
        world.add(new Sphere(new Point3(0.0f, 1.0f, 0.0f), material1, 1.0f));

        Material material2 = new Lambertian(new Color(0.4f, 0.2f, 0.1f));
        world.add(new Sphere(new Point3(-4.0f, 1.0f, 0.0f), material2, 1.0f));

        Material material3 = new Metal(new Color(0.7f, 0.6f, 0.5f), 0.0f);
        world.add(new Sphere(new Point3(4.0f, 1.0f, 0.0f), material3, 1.0f));

        return world;
    }
}

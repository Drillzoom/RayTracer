package de.re;

import de.re.common.Color;
import de.re.utility.Colors;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final int width = 256;
        final int height = 256;

        Path imagePath = Paths.get(args[0]);

        try (BufferedWriter bw = Files.newBufferedWriter(imagePath)) {
            bw.write("P3\n" + width + " " + height + "\n255\n");

            for (int j = height-1; j >= 0; j--) { // int j = 0; j < height; j++
                System.out.println("\rScanlines remaining: " + j);
                for (int i = 0; i < width; i++) {
                    float r = (float) i / (width-1);
                    float g = (float) j / (height-1);
                    float b = 0.25f;

                    Color pixelColor = new Color(r, g, b);
                    Colors.writeColor(bw, pixelColor);
                }
            }
        } catch (IOException e) {
            System.err.println("\nError while printing image.\n");
            e.printStackTrace();
        }

        System.out.println("\nDone.\n");
        System.out.println("\n" + imagePath.toAbsolutePath() + "\n");
    }
}

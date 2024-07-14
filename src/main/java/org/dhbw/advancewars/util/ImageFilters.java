package org.dhbw.advancewars.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class ImageFilters {

    private static final Map<Integer, Image> darkenCache = new HashMap<>();
    private static final double darkenFactor = 0.5;

    public static Image applyDarkenFilter(Image image) {
        int hash = image.hashCode();
        if (darkenCache.containsKey(hash)) {
            return darkenCache.get(hash);
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage darkenedImage = new WritableImage(width, height);
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = darkenedImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                Color darkerColor = color.deriveColor(0, 1, darkenFactor, 1);
                pixelWriter.setColor(x, y, darkerColor);
            }
        }

        darkenCache.put(hash, darkenedImage);
        return darkenedImage;
    }
}

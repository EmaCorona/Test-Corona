package it.corona.geckosoft.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeckosoftUtils {
    private GeckosoftUtils() {
        super();
    }

    public static void resizeImage(BufferedImage resizedImage, BufferedImage originalImage) {
        // Ridimensiona l'immagine originale nel nuovo formato 100x100px
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, 100, 100, null);
        graphics2D.dispose();
    }
}
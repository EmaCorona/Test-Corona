package it.corona.geckosoft.command;

import it.corona.geckosoft.exception.ImageProcessingException;
import it.corona.geckosoft.exception.ImageNotLoadedException;
import it.corona.geckosoft.util.GeckosoftUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class ImageResizerAndSaverCommand {
    private int imagesCounter;
    private static final String SAVE_PATH = "src/main/java/it/corona/geckosoft/resizedimages/img_";  // Percorso in locale all'interno del progetto

    public void execute(List<MultipartFile> files) {
        try {
            // Effettua un controllo e lancia un'eccezione custom nel caso in cui non siano state fornite immagini
            if (!isFilePresent(files)) {
                throw new ImageNotLoadedException();
            }

            for (MultipartFile file : files) {
                BufferedImage originalImage = ImageIO.read(file.getInputStream());
                BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

                GeckosoftUtils.resizeImage(resizedImage, originalImage);

                // Salva l'immagine ridimensionata in una cartella locale
                File fileToSave = new File(SAVE_PATH + imagesCounter++ + "/");
                ImageIO.write(resizedImage, "jpg", fileToSave);
            }

        } catch (IOException e) {
            throw new ImageProcessingException();
        }
    }

    private boolean isFilePresent(List<MultipartFile> files) {
        return (files != null && files.stream().findFirst().isPresent() &&
                !Objects.requireNonNull(files.stream().findFirst().get().getOriginalFilename()).isEmpty());
    }
}

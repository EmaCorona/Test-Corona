package it.corona.geckosoft.command;

import it.corona.geckosoft.exception.ImageProcessingException;
import it.corona.geckosoft.exception.ImageNotLoadedException;
import it.corona.geckosoft.util.GeckosoftUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
public class ImageResizerCommand {
    public byte[] execute(MultipartFile file) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        try {
            // Effettua un controllo e lancia un'eccezione custom nel caso in cui non siano state fornite immagini
            if (!isFilePresent(file)) {
                throw new ImageNotLoadedException();
            }

            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

            GeckosoftUtils.resizeImage(resizedImage, originalImage);

            // Converte il BufferedImage in un array di byte
            ImageIO.write(resizedImage, "jpg", byteStream);

        } catch (IOException e) {
            throw new ImageProcessingException();
        }

        return byteStream.toByteArray();
    }

    private boolean isFilePresent(MultipartFile file) {
        return (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty());
    }
}
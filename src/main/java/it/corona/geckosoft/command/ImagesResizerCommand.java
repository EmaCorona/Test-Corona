package it.corona.geckosoft.command;

import it.corona.geckosoft.dto.ImageResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ImagesResizerCommand {
    public List<ImageResponseDTO> execute(List<MultipartFile> files) {
        List<ImageResponseDTO> listImageResponseDTO = new ArrayList<>();

        try {
            // Effettua un controllo e lancia un'eccezione custom nel caso in cui non siano state fornite immagini
            if (!isFilePresent(files)) {
                throw new ImageNotLoadedException();
            }

            for (MultipartFile file : files) {
                BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

                GeckosoftUtils.resizeImage(resizedImage, originalImage);

                // Converte il BufferedImage in un array di byte
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "jpg", byteStream);

                ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
                imageResponseDTO.setFileName(file.getOriginalFilename());
                imageResponseDTO.setImagesData(byteStream.toByteArray());

                listImageResponseDTO.add(imageResponseDTO);
            }

        } catch (IOException e) {
            throw new ImageProcessingException();
        }

        return listImageResponseDTO;
    }

    private boolean isFilePresent(List<MultipartFile> files) {
        return (files != null && files.stream().findFirst().isPresent() &&
                !Objects.requireNonNull(files.stream().findFirst().get().getOriginalFilename()).isEmpty());
    }
}
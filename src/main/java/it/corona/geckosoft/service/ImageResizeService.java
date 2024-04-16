package it.corona.geckosoft.service;

import it.corona.geckosoft.command.ImageResizerAndSaverCommand;
import it.corona.geckosoft.command.ImageResizerCommand;
import it.corona.geckosoft.command.ImagesResizerCommand;
import it.corona.geckosoft.dto.ImageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageResizeService {
    @Autowired
    private ImageResizerCommand imageResizerCmd;
    @Autowired
    private ImagesResizerCommand imagesResizerCmd;
    @Autowired
    private ImageResizerAndSaverCommand imagesResizerAndSaverCmd;

    public byte[] resize(MultipartFile file) {
        return imageResizerCmd.execute(file);
    }

    public List<ImageResponseDTO> resizeImages(List<MultipartFile> files) {
        return imagesResizerCmd.execute(files);
    }

    public void resizeAndSaveImages(List<MultipartFile> files) {
        imagesResizerAndSaverCmd.execute(files);
    }
}
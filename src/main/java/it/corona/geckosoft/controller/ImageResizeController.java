package it.corona.geckosoft.controller;

import it.corona.geckosoft.dto.ImageResponseDTO;
import it.corona.geckosoft.service.ImageResizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImageResizeController {
    @Autowired
    private ImageResizeService imageResizeService;

    /*
     *  Questa API si occupa di effettuare la miniaturizzazione di una singola immagine.
     */
    @PostMapping(value = "/resize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> resizeImage(@RequestBody MultipartFile file) {
        byte[] byteImage = imageResizeService.resize(file);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(byteImage);
    }

    /*
     *  Questa API si dovrebbe occuparsi di effettuare la serializzazione in json di una
     *  lista di immagini ricevute in input. Tuttavia in fase di serializzazione
     *  dell'oggetto ImageResponseDTO non sono riuscito a serializzare l'attributo imagesData
     *  in un'immagine, ma viene restituito come un array di byte.
     *
     */
    @PostMapping(value = "/resize-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ImageResponseDTO>> resizeImages(@RequestBody List<MultipartFile> files) {
        List<ImageResponseDTO> listImageResponseDTO = imageResizeService.resizeImages(files);
        return ResponseEntity.ok().body(listImageResponseDTO);
    }

    /*
     * Questa API si occupa di effettuare la miniaturizzazione di una lista di immagini
     * andando a salvarle in una cartella locale al progetto, chiamata: resized-images.
     * Per vedere le immagini miniaturizzate bisogna refreshare la cartella/progetto.
     */
    @PostMapping(value = "/resize-and-save-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> resizeAndSaveImages(@RequestBody List<MultipartFile> files) {
        imageResizeService.resizeAndSaveImages(files);
        return new ResponseEntity<>("Immagini elaborate con successo", HttpStatus.OK);
    }
}
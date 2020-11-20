package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.service.IDocumentationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Transactional
public class DocumentationServiceImpl implements IDocumentationService {

    @Value("${server.protocol}")
    String protocol;

    @Value("${server.host}")
    String host;

    @Value("${server.port}")
    String port;

    @Value("${location.files}")
    String locationFiles;

    @Value("${path.originalImage}")
    String pathOriginalImage;

    @Value("${server.servlet.context-path}")
    String contextPath;

    @Override
    public String createImageDocumentation(String imageUrl) throws CreateDocumentationException {

        try {
            String contentType = imageUrl.split(",")[0].split(";")[0].split(":")[1].trim();
            String base64Image = imageUrl.split(",")[1];
            String fileName = UUID.randomUUID().toString().split("-")[4].toUpperCase() + ".jpg";
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

            // Imagen Original
            File imageFile = new File(locationFiles + pathOriginalImage + fileName);
            ImageIO.write(bufferedImage, "png", imageFile);

            return protocol + "://" + host + ":" + port + contextPath + "/resources/" + pathOriginalImage + fileName;
        } catch (Exception e) {
            throw new CreateDocumentationException("Error al crear imagen");
        }
    }
}

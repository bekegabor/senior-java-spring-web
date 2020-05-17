package hu.ponte.hr.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public static final String UPLOAD = "Upload";
    public static final String IMAGES = "Images";
    public static final String IMAGE_UPLOAD_FOLDER = "Upload"+ File.separator+"Images";

    public void saveImageFile(MultipartFile file) throws IOException {
        Path path = Paths.get(UPLOAD + File.separator + IMAGES + File.separator + file.getOriginalFilename());
        Files.write(path, file.getBytes());
    }

    public byte[] loadImageFile(String fileName) throws IOException {
        Path path = Paths.get(IMAGE_UPLOAD_FOLDER + File.separator + fileName);
        return Files.readAllBytes(path);
    }

    public byte[] readFileFromResourceFolder(String pathToResource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(pathToResource);
        return IOUtils.toByteArray(inputStream);
    }
}

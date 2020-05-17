package hu.ponte.hr.services;

import hu.ponte.hr.entity.ImageMeta;
import hu.ponte.hr.repository.ImageMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageStore {

    private final ImageMetaRepository imageMetaRepository;
    private final FileService fileService;
    private final SignService signService;

    @Autowired
    public ImageStore(ImageMetaRepository imageMetaRepository, FileService fileService, SignService signService) {
        this.imageMetaRepository = imageMetaRepository;
        this.fileService = fileService;
        this.signService = signService;
    }

    public List<ImageMeta> listImages(){
        return imageMetaRepository.findAll();
    }

    public void getImageById(String id, HttpServletResponse response) throws IOException {
        Optional<ImageMeta> optionalImageMeta = imageMetaRepository.findById(Long.valueOf(id));
        if (optionalImageMeta.isPresent()){
            String fileName = optionalImageMeta.get().getName();
            byte[] requestedImage = fileService.loadImageFile(fileName);
            response.setHeader(HttpHeaders.CONTENT_TYPE, optionalImageMeta.get().getMimeType());
            response.getOutputStream().write(requestedImage);
        }
    }

    public ImageMeta createImageStoreEntry(MultipartFile uploadedFile) throws IOException {
        String name = uploadedFile.getOriginalFilename();
        String mimeType = uploadedFile.getContentType();
        long size = uploadedFile.getSize();
        String digitalSign = signService.createBase64SignatureWithSHA256RSA(uploadedFile.getBytes());
        ImageMeta imageMeta = ImageMeta.builder().name(name).mimeType(mimeType).size(size).digitalSign(digitalSign).build();
        return imageMetaRepository.save(imageMeta);
    }

    public String processFormUpload(MultipartFile uploadedFile) throws IOException {
        fileService.saveImageFile(uploadedFile);
        createImageStoreEntry(uploadedFile);
        return HttpStatus.OK.name();
    }
}

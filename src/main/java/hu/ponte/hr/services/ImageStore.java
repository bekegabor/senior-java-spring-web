package hu.ponte.hr.services;

import hu.ponte.hr.entity.ImageMeta;
import hu.ponte.hr.repository.ImageMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ImageStore {

    private final ImageMetaRepository imageMetaRepository;

    @Autowired
    public ImageStore(ImageMetaRepository imageMetaRepository) {
        this.imageMetaRepository = imageMetaRepository;
    }

    public List<ImageMeta> listImages(){
        return imageMetaRepository.findAll();
    }

    public void getImageById(String id, HttpServletResponse response){

    }


}

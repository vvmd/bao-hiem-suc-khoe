package team5.baohiem.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import team5.baohiem.model.HealthImage;
import team5.baohiem.repo.HealthImageRepo;

@Service
public class HealthImageService {
    private Cloudinary cloudinary;
    @Autowired
    private HealthImageRepo repo;

    public List<HealthImage> findAll() {
        return repo.findAll();
    }

    public String uploadImage(long registrator, String description, MultipartFile file) throws IOException {
        HealthImage image = new HealthImage();
        String URL = cloudinary.uploader()
                .upload(file.getBytes(),
                        ObjectUtils.asMap("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();

        image.createNewImage(registrator, description, URL);
        repo.save(image);
        return URL;
    }

    public Optional<HealthImage> findById(Long id) {
        return repo.findById(id);
    }

    public List<HealthImage> findByRegistrator(long registrator) {
        return repo.findByRegistrator(registrator);
    }

}

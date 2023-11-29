package com.fit.health_insurance.insurance_registration.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.fit.health_insurance.insurance_registration.model.PersonalDocument;
import com.fit.health_insurance.insurance_registration.repo.PersonalDocumentRepo;
import com.fit.health_insurance.security.user.IUserRepository;
import com.fit.health_insurance.security.user.User;

@Service
public class PersonalDocumentService {
    private Cloudinary cloudinary;
    @Autowired
    private PersonalDocumentRepo repo;

    @Autowired
    private IUserRepository userRepo;

    public List<PersonalDocument> findAll() {
        return repo.findAll();
    }

    public String uploadImage(Integer registrator, String name, MultipartFile file) throws IOException {
        PersonalDocument image = new PersonalDocument();
        String URL = cloudinary.uploader()
                .upload(file.getBytes(),
                        ObjectUtils.asMap("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
        Optional<User> user = userRepo.findById(registrator);
        if (user.isPresent()) {
            image.createNewImage(user.get(), name, URL);
            repo.save(image);
        }
        ;
        return URL;
    }

    public Optional<PersonalDocument> findById(Integer id) {
        return repo.findById(id);
    }

    public List<PersonalDocument> findByRegistrator(Integer registrator) {
        return repo.findByRegistrator(registrator);
    }

}

package team5.baohiem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import team5.baohiem.model.HealthImage;
import team5.baohiem.service.HealthImageService;

@RestController
@RequestMapping("/health-images")
public class HealthImageController {
    @Autowired
    private HealthImageService service;

    @GetMapping
    public ResponseEntity<List<HealthImage>> getAllHealthImages() {
        return new ResponseEntity<List<HealthImage>>(service.findAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    public String uploadIMageOrFile(@RequestParam("registrator") long registrator,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {
        String URL = service.uploadImage(registrator, description, file);
        return URL;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<HealthImage> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/registrator/{registrator}")
    public List<HealthImage> findByRegistrator(@PathVariable long registrator) {
        return service.findByRegistrator(registrator);
    }

}

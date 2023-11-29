package com.fit.health_insurance.insurance_registration.controller;

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

import com.fit.health_insurance.insurance_registration.model.PersonalDocument;
import com.fit.health_insurance.insurance_registration.service.PersonalDocumentService;

@RestController
@RequestMapping("/health-images")
public class PersonalDocumentController {
    @Autowired
    private PersonalDocumentService service;

    @GetMapping
    public ResponseEntity<List<PersonalDocument>> getAllPersonalDocuments() {
        return new ResponseEntity<List<PersonalDocument>>(service.findAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    public String uploadIMageOrFile(@RequestParam("registrator") Integer registrator,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws IOException {
        String URL = service.uploadImage(registrator, name, file);
        return URL;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<PersonalDocument> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/registrator/{registrator}")
    public List<PersonalDocument> findByRegistrator(@PathVariable Integer registrator) {
        return service.findByRegistrator(registrator);
    }

}

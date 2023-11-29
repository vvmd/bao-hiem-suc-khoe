package com.fit.health_insurance.insurance_registration.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fit.health_insurance.insurance_registration.model.RegistrationForm;
import com.fit.health_insurance.insurance_registration.service.RegistrationFormService;
import com.fit.health_insurance.insurance_registration.utils.RegistrationStatus;

@RestController
@RequestMapping("/registrations")
public class RegistrationFormController {
    @Autowired
    private RegistrationFormService service;

    @GetMapping
    public ResponseEntity<List<RegistrationForm>> getAllRegistrations() {
        return new ResponseEntity<List<RegistrationForm>>(service.findAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<RegistrationForm> findByid(@PathVariable Integer id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public RegistrationForm create(@RequestParam("registrator") Integer registrator,
            @RequestParam("name") String name,
            @RequestParam("birthday") String birthday,
            @RequestParam("identityCard") String identityCard,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address) {
        return service.create(registrator, name, birthday, identityCard, phone, address);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Optional<RegistrationForm> update(@RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("birthday") String birthday,
            @RequestParam("identityCard") String identityCard,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("status") RegistrationStatus status,
            @RequestParam("approvedBy") Integer approvedBy) {
        return service.update(id, name, birthday, identityCard, phone, address, status, approvedBy);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/identityCard/{identityCard}")
    public List<RegistrationForm> findByIdentityCard(@PathVariable String identityCard) {
        return service.findByIdentityCard(identityCard);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/registrator/{registrator}")
    public List<RegistrationForm> findByRegistrator(@PathVariable Integer registrator) {
        return service.findByRegistrator(registrator);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/status/{status}")
    public List<RegistrationForm> findByStatus(@PathVariable RegistrationStatus status) {
        return service.findByStatus(status);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/date-after/{date}")
    public List<RegistrationForm> findByCreatedDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return service.findByCreatedDateAfter(date);
    }

}

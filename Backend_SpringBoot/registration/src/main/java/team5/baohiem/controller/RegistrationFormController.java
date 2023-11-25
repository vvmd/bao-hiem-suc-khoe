package team5.baohiem.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import team5.baohiem.model.RegistrationForm;
import team5.baohiem.repo.RegistrationFormRepo;
import team5.baohiem.service.HealthImageService;
import team5.baohiem.service.RegistrationFormService;

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
    public Optional<RegistrationForm> findByFormId(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public RegistrationForm create(@RequestParam("name") String name,
            @RequestParam("birthday") String birthday,
            @RequestParam("IDCard") long IDCard,
            @RequestParam("phone") long phone,
            @RequestParam("address") String address,
            @RequestParam("insuranceID") long insuranceID,
            @RequestParam("registrator") long registrator) {
        return service.create(name, birthday, IDCard, phone, address, insuranceID, registrator);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Optional<RegistrationForm> update(@RequestParam("formID") long formID,
            @RequestParam("name") String name,
            @RequestParam("birthday") String birthday,
            @RequestParam("IDCard") long IDCard,
            @RequestParam("phone") long phone,
            @RequestParam("address") String address,
            @RequestParam("insuranceID") long insuranceID,
            @RequestParam("status") String status,
            @RequestParam("approvedBy") long approvedBy) {
        return service.update(formID, name, birthday, IDCard, phone, address, insuranceID, status, approvedBy);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/IDCard/{IDCard}")
    public List<RegistrationForm> findByIDCard(@PathVariable Long IDCard) {
        return service.findByIDCard(IDCard);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/registrator/{registrator}")
    public List<RegistrationForm> findByRegistrator(@PathVariable Long registrator) {
        return service.findByRegistrator(registrator);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/status/{status}")
    public List<RegistrationForm> findByStatus(@PathVariable String status) {
        return service.findByStatus(status);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/date-after/{date}")
    public List<RegistrationForm> findByCreatedDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.findByCreatedDateAfter(date);
    }

}

package team5.baohiem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import team5.baohiem.model.RegistrationForm;
import team5.baohiem.repo.RegistrationFormRepo;

@Service
public class RegistrationFormService {
    @Autowired
    private RegistrationFormRepo repo;

    public List<RegistrationForm> findAll() {
        return repo.findAll();
    }

    public Optional<RegistrationForm> findById(Long id) {
        return repo.findById(id);
    }

    public RegistrationForm create(String name,
            String birthday,
            long IDCard,
            long phone,
            String address,
            long insuranceID,
            long registrator) {
        RegistrationForm form = new RegistrationForm();
        form.create(name, birthday, IDCard, phone, address, insuranceID, registrator);
        return repo.save(form);
    }

    public Optional<RegistrationForm> update(long formID,
            String name,
            String birthday,
            long IDCard,
            long phone,
            String address,
            long insuranceID,
            String status,
            long approvedBy) {
        Optional<RegistrationForm> form = repo.findById(formID);
        form.ifPresent(f -> {
            f.update(name, birthday, IDCard, phone, address, insuranceID, status, approvedBy);
            repo.save(f);
        });
        return form;
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<RegistrationForm> findByIDCard(long IDCard) {
        return repo.findByIDCard(IDCard);
    }

    public List<RegistrationForm> findByRegistrator(long registrator) {
        return repo.findByRegistrator(registrator);
    }

    public List<RegistrationForm> findByStatus(String status) {
        return repo.findByStatus(status);
    }

    public List<RegistrationForm> findByCreatedDateAfter(LocalDate date) {
        return repo.findByCreatedDateAfter(date);
    }
}

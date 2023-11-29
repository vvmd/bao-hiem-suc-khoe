package com.fit.health_insurance.insurance_registration.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fit.health_insurance.insurance_registration.model.RegistrationForm;
import com.fit.health_insurance.insurance_registration.repo.RegistrationFormRepo;
import com.fit.health_insurance.insurance_registration.utils.RegistrationStatus;
import com.fit.health_insurance.security.user.IUserRepository;
import com.fit.health_insurance.security.user.User;

@Service
public class RegistrationFormService {
    @Autowired
    private RegistrationFormRepo registrationFormRepo;

    @Autowired
    private IUserRepository userRepo;

    public List<RegistrationForm> findAll() {
        return registrationFormRepo.findAll();
    }

    public Optional<RegistrationForm> findById(Integer id) {
        return registrationFormRepo.findById(id);
    }

    public RegistrationForm create(Integer userid,
            String name,
            String birthday,
            String identityCard,
            String phone,
            String address) {
        Optional<User> user = userRepo.findById(userid);
        RegistrationForm form = new RegistrationForm();
        user.ifPresent(u -> {
            form.create(u, name, birthday, identityCard, phone, address);
        });
        return registrationFormRepo.save(form);
    }

    public Optional<RegistrationForm> update(Integer id,
            String name,
            String birthday,
            String identityCard,
            String phone,
            String address,
            RegistrationStatus status,
            Integer approvedBy) {
        Optional<RegistrationForm> form = registrationFormRepo.findById(id);
        if (form.isPresent()) {
            Optional<User> user = userRepo.findById(approvedBy);
            if (user.isPresent()) {
                form.get().update(name, birthday, identityCard, phone, address, status, user.get());
            } else {
                form.get().update(name, birthday, identityCard, phone, address, status, null);
            }
            registrationFormRepo.save(form.get());
        }
        ;
        return form;
    }

    public void deleteById(Integer id) {
        registrationFormRepo.deleteById(id);
    }

    public List<RegistrationForm> findByIdentityCard(String identityCard) {
        return registrationFormRepo.findByIdentityCard(identityCard);
    }

    public List<RegistrationForm> findByRegistrator(Integer registrator) {
        return registrationFormRepo.findByRegistrator(registrator);
    }

    public List<RegistrationForm> findByStatus(RegistrationStatus status) {
        return registrationFormRepo.findByStatus(status);
    }

    public List<RegistrationForm> findByCreatedDateAfter(Date date) {
        return registrationFormRepo.findByCreatedDateAfter(date);
    }
}

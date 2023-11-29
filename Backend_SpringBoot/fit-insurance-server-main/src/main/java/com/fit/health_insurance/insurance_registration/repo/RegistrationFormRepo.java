package com.fit.health_insurance.insurance_registration.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fit.health_insurance.insurance_registration.model.RegistrationForm;
import com.fit.health_insurance.insurance_registration.utils.RegistrationStatus;

@Repository
public interface RegistrationFormRepo extends JpaRepository<RegistrationForm, Integer> {
    List<RegistrationForm> findByIdentityCard(String identityCard);

    @Query(value = "FROM RegistrationForm f WHERE f.registrator.id = :registrator")
    List<RegistrationForm> findByRegistrator(@Param("registrator") Integer registrator);

    List<RegistrationForm> findByStatus(RegistrationStatus status);

    @Query("SELECT f FROM RegistrationForm f WHERE f.createdAt > :date")
    List<RegistrationForm> findByCreatedDateAfter(@Param("date") Date date);

}

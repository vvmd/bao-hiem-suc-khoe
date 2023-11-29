package com.fit.health_insurance.insurance_registration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fit.health_insurance.insurance_registration.model.PersonalDocument;

@Repository
public interface PersonalDocumentRepo extends JpaRepository<PersonalDocument, Integer> {
    @Query(value = "FROM PersonalDocument d WHERE d.registrator.id = :registrator")
    List<PersonalDocument> findByRegistrator(@Param("registrator") Integer registrator);

}

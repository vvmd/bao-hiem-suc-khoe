package com.fit.health_insurance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fit.health_insurance.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
        @Query(value = "SELECT * FROM CONTRACT WHERE REGISTRATION_FORM_ID = :formID", nativeQuery = true)
        Contract findByRegistrationForm(@Param("formID") Integer formID);

        @Query(value = "SELECT CONTRACT.* FROM CONTRACT INNER JOIN USERS " +
                        "ON CONTRACT.REGISTRATOR_ID = USERS.ID WHERE USERS.EMAIL = :email", nativeQuery = true)
        List<Contract> findAllByEmail(@Param("email") String email);

        @Query(value = "SELECT CONTRACT.* FROM CONTRACT INNER JOIN USERS " +
                        "ON CONTRACT.REGISTRATOR_ID = USERS.ID WHERE USERS.IDENTITYCARD = : identityCard", nativeQuery = true)
        List<Contract> findAllByIdentityCard(@Param("identityCard") String identityCard);

        @Query(value = "SELECT * FROM CONTRACT WHERE CONTRACT.startAt >= :date", nativeQuery = true)
        List<Contract> findAllByStartDateAfter(@Param("date") Date date);

        @Query(value = "SELECT * FROM CONTRACT WHERE CONTRACT.endAt >= :date", nativeQuery = true)
        List<Contract> findAllByEndDateAfter(@Param("date") Date date);
}

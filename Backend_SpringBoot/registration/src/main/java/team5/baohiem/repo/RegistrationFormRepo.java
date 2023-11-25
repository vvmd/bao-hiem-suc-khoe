package team5.baohiem.repo;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import team5.baohiem.model.RegistrationForm;

@Repository
public interface RegistrationFormRepo extends JpaRepository<RegistrationForm, Long> {
    List<RegistrationForm> findByIDCard(long IDCard);

    List<RegistrationForm> findByRegistrator(long registrator);

    List<RegistrationForm> findByStatus(String status);

    @Query("SELECT f FROM RegistrationForm f WHERE f.createdAt > :date")
    List<RegistrationForm> findByCreatedDateAfter(@Param("date") LocalDate date);
}

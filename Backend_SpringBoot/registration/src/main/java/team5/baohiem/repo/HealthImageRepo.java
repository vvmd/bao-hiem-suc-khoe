package team5.baohiem.repo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import team5.baohiem.model.HealthImage;

@Repository // repository: DTO object
public interface HealthImageRepo extends JpaRepository<HealthImage, Long> {
    List<HealthImage> findByRegistrator(long registrator);

}

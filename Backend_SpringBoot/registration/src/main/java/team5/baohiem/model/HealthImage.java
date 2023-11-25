package team5.baohiem.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "HealthImage")
public class HealthImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ImageID;
    private long registrator;
    private String description;
    private LocalDate uploadedAt;
    private String URL;

    public void createNewImage(long registrator, String description, String URL) {
        this.registrator = registrator;
        this.description = description;
        this.uploadedAt = LocalDate.now();
        this.URL = URL;
    }
}

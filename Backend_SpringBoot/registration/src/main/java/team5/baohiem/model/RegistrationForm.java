package team5.baohiem.model;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RegistrationForm")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long formID;
    private String name;
    private String birthday;
    private long IDCard;
    private long phone;
    private String address;
    private long insuranceID;
    private long registrator;
    private String status;
    private long approvedBy;
    private LocalDate createdAt;
    private long healthImageID;

    public void create(String name,
            String birthday,
            long IDCard,
            long phone,
            String address,
            long insuranceID,
            long registrator) {
        this.name = name;
        this.birthday = birthday;
        this.IDCard = IDCard;
        this.phone = phone;
        this.address = address;
        this.insuranceID = insuranceID;
        this.registrator = registrator;
        this.status = "pending";
        this.createdAt = LocalDate.now();

    }

    public void update(String name,
            String birthday,
            long IDCard,
            long phone,
            String address,
            long insuranceID,
            String status,
            long approvedBy) {
        this.name = name;
        this.birthday = birthday;
        this.IDCard = IDCard;
        this.phone = phone;
        this.address = address;
        this.insuranceID = insuranceID;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    public void updateHealth(long healthImageID) {
        this.healthImageID = healthImageID;
    }
}

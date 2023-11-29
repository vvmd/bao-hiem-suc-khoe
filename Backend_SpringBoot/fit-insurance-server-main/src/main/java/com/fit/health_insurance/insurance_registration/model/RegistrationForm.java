package com.fit.health_insurance.insurance_registration.model;

import java.util.Date;

import com.fit.health_insurance.insurance_registration.utils.RegistrationStatus;
import com.fit.health_insurance.security.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "registration_forms")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrator", referencedColumnName = "id")
    private User registrator;

    private String name;
    private String birthday;
    private String identityCard;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approvedBy", referencedColumnName = "id")
    private User approvedBy;
    private Date createdAt;

    public void create(User registrator,
            String name,
            String birthday,
            String identityCard,
            String phone,
            String address) {
        this.registrator = registrator;
        this.name = name;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.status = RegistrationStatus.PENDING;
        this.createdAt = new Date();
    }

    public void update(String name,
            String birthday,
            String identityCard,
            String phone,
            String address,
            RegistrationStatus status,
            User approvedBy) {
        this.name = name;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.approvedBy = approvedBy;
    }

}

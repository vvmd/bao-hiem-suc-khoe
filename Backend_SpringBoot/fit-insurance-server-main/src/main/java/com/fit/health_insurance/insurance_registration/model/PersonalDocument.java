package com.fit.health_insurance.insurance_registration.model;

import java.util.Date;

import com.fit.health_insurance.security.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_document")
public class PersonalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrator", referencedColumnName = "id")
    private User registrator;

    private String name;
    private Date uploadedAt;
    private String URL;

    public void createNewImage(User registrator, String name, String URL) {
        this.registrator = registrator;
        this.name = name;
        this.uploadedAt = new Date();
        this.URL = URL;
    }
}

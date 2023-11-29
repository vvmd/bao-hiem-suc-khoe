package com.fit.health_insurance.insurance_registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "health_images")
public class HealthImage {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formid", referencedColumnName = "id")
    private RegistrationForm formid;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageid", referencedColumnName = "id")
    private PersonalDocument imageid;
}

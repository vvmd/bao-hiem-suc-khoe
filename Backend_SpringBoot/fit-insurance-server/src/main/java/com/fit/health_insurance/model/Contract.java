package com.fit.health_insurance.model;

import java.util.Date;

import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.enums.Period;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_form_id")
    private RegistrationForm registrationForm;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrator_id")
    private User registrator;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 numbers")
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
    private Date startAt;
    private Date endAt;
    @Enumerated(EnumType.STRING)
    private Period periodPay;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    private Date createdAt;
    private User updatedBy;
    private Date lastUpdated;
}

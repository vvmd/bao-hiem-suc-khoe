package com.fit.health_insurance.dto;

import java.io.Serializable;
import java.util.Date;

import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.enums.Period;
import com.fit.health_insurance.model.RegistrationForm;
import com.fit.health_insurance.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDto implements Serializable {
    private Integer id;
    private RegistrationForm registratorForm;
    private User registrator;
    private String phone;
    private String address;
    private ContractStatus status;
    private Date startAt;
    private Date endAt;
    private Period periodPay;
    private Integer price;
}

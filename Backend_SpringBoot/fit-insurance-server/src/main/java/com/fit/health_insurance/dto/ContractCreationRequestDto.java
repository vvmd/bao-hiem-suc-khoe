package com.fit.health_insurance.dto;

import java.io.Serializable;
import java.util.Date;

import com.fit.health_insurance.enums.Period;
import com.fit.health_insurance.model.RegistrationForm;
import com.fit.health_insurance.model.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreationRequestDto implements Serializable {
    @NotNull(message = "The registratorForm is required")
    private RegistrationForm registratorForm;
    @NotNull(message = "The registrator is required")
    private User registrator;
    @NotEmpty(message = "The start date is required")
    private Date startAt;
    @NotEmpty(message = "The end date is required")
    private Date endAt;
    @NotNull(message = "The periodPay is required")
    private Period periodPay;
    @NotEmpty(message = "The price is required")
    private Integer price;
}

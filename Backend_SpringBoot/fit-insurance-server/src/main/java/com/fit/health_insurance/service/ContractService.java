package com.fit.health_insurance.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fit.health_insurance.dto.ContractCreationRequestDto;
import com.fit.health_insurance.dto.ContractResponseDto;
import com.fit.health_insurance.dto.ContractUpdateRequestDto;
import com.fit.health_insurance.enums.ContractStatus;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.Contract;
import com.fit.health_insurance.model.User;
import com.fit.health_insurance.repository.ContractRepository;
import com.fit.health_insurance.repository.RegistrationFormRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserService userService;
    private final RegistrationFormRepository registrationFormRepository;

    private ContractResponseDto convertToResponseDto(Contract entity) {
        return ContractResponseDto.builder()
                .id(entity.getId())
                .registratorForm(entity.getRegistrationForm())
                .registrator(entity.getRegistrator())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .status(entity.getStatus())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .periodPay(entity.getPeriodPay())
                .price(entity.getPrice())
                .build();
    }

    public List<ContractResponseDto> findAll() {
        var contractList = contractRepository.findAll();
        return contractList.stream().map(this::convertToResponseDto).toList();
    }

    public ContractResponseDto findById(Integer id) {
        if (contractRepository.findById(id).isPresent()) {
            return convertToResponseDto(contractRepository.findById(id).get());
        } else
            throw new NotFoundException("Contract not found!");
    }

    public ContractResponseDto findByRegistrationForm(Integer formID) {
        var contract = contractRepository.findByRegistrationForm(formID);
        if (contract != null) {
            return convertToResponseDto(contract);
        } else
            throw new NotFoundException("Contract not found!");
    }

    public List<ContractResponseDto> findByEmail(String email) {
        var contractList = contractRepository.findAllByEmail(email);
        if (!contractList.isEmpty()) {
            return contractList.stream().map(this::convertToResponseDto).toList();
        } else
            throw new NotFoundException("Contract not found!");
    }

    public List<ContractResponseDto> findByIdentityCard(String identityCard) {
        var contractList = contractRepository.findAllByIdentityCard(identityCard);
        if (!contractList.isEmpty()) {
            return contractList.stream().map(this::convertToResponseDto).toList();
        } else
            throw new NotFoundException("Contract not found!");
    }

    public List<ContractResponseDto> findByStartDateAfter(Date date) {
        var contractList = contractRepository.findAllByStartDateAfter(date);
        if (!contractList.isEmpty()) {
            return contractList.stream().map(this::convertToResponseDto).toList();
        } else
            throw new NotFoundException("Contract not found!");
    }

    public List<ContractResponseDto> findByEndDateAfter(Date date) {
        var contractList = contractRepository.findAllByEndDateAfter(date);
        if (!contractList.isEmpty()) {
            return contractList.stream().map(this::convertToResponseDto).toList();
        } else
            throw new NotFoundException("Contract not found!");
    }

    public ContractResponseDto create(ContractCreationRequestDto request) {
        var registrator = userService.loadUserByUsername(request.getRegistrator().getEmail());
        if (registrationFormRepository.findById(request.getRegistratorForm().getId()).isPresent()) {
            var registrationForm = registrationFormRepository.findById(request.getRegistratorForm().getId()).get();
            if (registrator != null) {
                Authentication principal = SecurityContextHolder.getContext().getAuthentication();
                String username = principal.getName();
                var createdBy = userService.loadUserByUsername(username);
                Contract contractEntity = Contract.builder()
                        .registrationForm(registrationForm)
                        .registrator((User) registrator)
                        .phone(registrationForm.getPhone())
                        .address(registrationForm.getAddress())
                        .status(ContractStatus.LAPSED)
                        .startAt(request.getStartAt())
                        .endAt(request.getEndAt())
                        .periodPay(request.getPeriodPay())
                        .price(request.getPrice())
                        .createdBy((User) createdBy)
                        .createdAt(new Date())
                        .updatedBy((User) createdBy)
                        .lastUpdated(new Date())
                        .build();
                return convertToResponseDto(contractRepository.save(contractEntity));
            } else
                throw new NotFoundException("Registrator not found");
        } else
            throw new NotFoundException("Registration form not found");

    }

    public void deleteById(Integer id) {
        if (contractRepository.findById(id).isPresent()) {
            contractRepository.deleteById(id);
        } else
            throw new NotFoundException("Contract not found!");
    }

    public ContractResponseDto update(Integer id, ContractUpdateRequestDto request) {
        if (contractRepository.findById(id).isPresent()) {
            var contractEntity = contractRepository.findById(id).get();
            contractEntity.setStatus(request.getStatus());
            contractEntity.setStartAt(request.getStartAt());
            contractEntity.setEndAt(request.getEndAt());
            contractEntity.setPeriodPay(request.getPeriodPay());
            contractEntity.setPrice(request.getPrice());
            Authentication principal = SecurityContextHolder.getContext().getAuthentication();
            String username = principal.getName();
            var updatedBy = userService.loadUserByUsername(username);
            contractEntity.setUpdatedBy((User) updatedBy);
            contractEntity.setLastUpdated(new Date());
            return convertToResponseDto(contractRepository.save(contractEntity));
        } else
            throw new NotFoundException("Contract not found!");
    }

}

package com.fit.health_insurance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fit.health_insurance.dto.ContractCreationRequestDto;
import com.fit.health_insurance.dto.ContractResponseDto;
import com.fit.health_insurance.dto.ContractUpdateRequestDto;
import com.fit.health_insurance.service.ContractService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {
    private ContractService contractService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<ContractResponseDto>> findAll() {
        return ResponseEntity.ok(contractService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(contractService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/registration-forms/{formID}")
    public ResponseEntity<ContractResponseDto> findByRegistrationForm(@PathVariable Integer formID) {
        return ResponseEntity.ok(contractService.findByRegistrationForm(formID));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER') or #request.email == authentication.principal.username")
    @GetMapping("/users/email/{email}")
    public ResponseEntity<List<ContractResponseDto>> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(contractService.findByEmail(email));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/identity-card/{identityCard}")
    public ResponseEntity<List<ContractResponseDto>> findByIdentityCard(@PathVariable String identityCard) {
        return ResponseEntity.ok(contractService.findByIdentityCard(identityCard));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/start-date-after/{date}")
    public ResponseEntity<List<ContractResponseDto>> findByStartDateAfter(@PathVariable Date date) {
        return ResponseEntity.ok(contractService.findByStartDateAfter(date));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/end-date-after/{date}")
    public ResponseEntity<List<ContractResponseDto>> findByEndDateAfter(@PathVariable Date date) {
        return ResponseEntity.ok(contractService.findByEndDateAfter(date));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<ContractResponseDto> create(@Valid @ModelAttribute ContractCreationRequestDto request) {
        return new ResponseEntity<ContractResponseDto>(contractService.create(request), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        contractService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<ContractResponseDto> update(@PathVariable Integer id,
            @Valid @ModelAttribute ContractUpdateRequestDto request) {
        return ResponseEntity.ok(contractService.update(id, request));
    }

}

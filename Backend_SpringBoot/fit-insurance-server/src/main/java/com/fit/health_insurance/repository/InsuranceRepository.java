package com.fit.health_insurance.repository;

import com.fit.health_insurance.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

}
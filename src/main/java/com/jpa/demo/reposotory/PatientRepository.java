package com.jpa.demo.reposotory;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.demo.entities.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {
public Page<Patient> findByNomContains(String nom,Pageable pageable );

}

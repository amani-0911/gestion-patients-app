package com.jpa.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jpa.demo.entities.Patient;
import com.jpa.demo.reposotory.PatientRepository;

@SpringBootApplication
public class TpJpa2Application  implements CommandLineRunner {
    @Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(TpJpa2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * patientRepository.save(new Patient(null,"ahmed",new Date(),2345,false));
		 * patientRepository.save(new Patient(null,"aymene",new Date(),300,false));
		 * patientRepository.save(new Patient(null,"Hamada",new Date(),245,false));
		 * patientRepository.save(new Patient(null,"Ali",new Date(),100,true));
		 */
		 
		patientRepository.findAll().forEach(p->{
			System.out.println(p);
		});


	}
	

}

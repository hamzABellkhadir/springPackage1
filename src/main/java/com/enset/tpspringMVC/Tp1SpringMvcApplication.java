package com.enset.tpspringMVC;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enset.tpspringMVC.entities.Patient;
import com.enset.tpspringMVC.repositories.PatientRepository;

@SpringBootApplication
public class Tp1SpringMvcApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(Tp1SpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient("fifii1", new Date(), 120, false));
		/*patientRepository.save(new Patient("mouad",new Date(),1400,false));
		patientRepository.save(new Patient("limori",new Date(),2500,false));
		*/
	}

}

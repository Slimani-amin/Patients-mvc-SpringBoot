package ma.ehtp.patients_mvc;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.ehtp.patients_mvc.entities.Patient;
import ma.ehtp.patients_mvc.repositories.PatientRepository;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	//@Bean 
	CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(new Patient(null, "Hassan", new Date(), false, 12));
			patientRepository.save(new Patient(null, "Khalid", new Date(), false, 15));
			patientRepository.save(new Patient(null, "Karim", new Date(), true, 18));
			patientRepository.save(new Patient(null, "Omar", new Date(), false, 20));

			patientRepository.findAll().forEach(p->{
				System.out.println(p.toString());
			});
		};
	}



    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

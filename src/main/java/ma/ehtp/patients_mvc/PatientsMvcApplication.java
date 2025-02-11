package ma.ehtp.patients_mvc;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {

		return args-> {
			UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
			if(u1==null){
			jdbcUserDetailsManager.createUser(
				User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build()
			);
			}

			UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
			if(u2==null){
			jdbcUserDetailsManager.createUser(
				User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build()
			);
			}
           
			UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
			if(u3==null){
			jdbcUserDetailsManager.createUser(
				User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build()
			);
			}
		};

	}



    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

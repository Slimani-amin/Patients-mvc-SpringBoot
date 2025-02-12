package ma.ehtp.patients_mvc.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ehtp.patients_mvc.sec.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);

}

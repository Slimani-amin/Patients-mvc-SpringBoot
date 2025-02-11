package ma.ehtp.patients_mvc.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ehtp.patients_mvc.sec.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);

}

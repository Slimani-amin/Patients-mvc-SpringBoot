package ma.ehtp.patients_mvc.sec.repo;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);

}

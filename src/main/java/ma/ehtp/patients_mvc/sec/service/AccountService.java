package ma.ehtp.patients_mvc.sec.service;

import ma.ehtp.patients_mvc.sec.entities.AppRole;
import ma.ehtp.patients_mvc.sec.entities.AppUser;

public interface AccountService {

    AppUser addNewUser(String username, String password, String email, String confirmedPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);

}

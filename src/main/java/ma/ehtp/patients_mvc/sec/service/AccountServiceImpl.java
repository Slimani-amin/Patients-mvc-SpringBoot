package ma.ehtp.patients_mvc.sec.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import ma.ehtp.patients_mvc.sec.entities.AppRole;
import ma.ehtp.patients_mvc.sec.entities.AppUser;
import ma.ehtp.patients_mvc.sec.repo.AppRoleRepository;
import ma.ehtp.patients_mvc.sec.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmedPassword) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null) {
            throw new RuntimeException("User already exists");
        }
        if (!password.equals(confirmedPassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        user= AppUser.builder()
        .userId(UUID.randomUUID().toString())
        .username(username)
        .password(passwordEncoder.encode(password))
        .email(email)
        .build();
        return appUserRepository.save(user);
        
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findByRole(role);
        if (appRole != null) {
            throw new RuntimeException("Role already exists");
        }  
        return appRoleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addRoleToUser(String username, String roleName){
        AppUser user = appUserRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        AppRole role = appRoleRepository.findByRole(roleName);
        if(role == null) throw new RuntimeException("Role not found");
        user.getRoles().add(role);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName){
        AppUser user = appUserRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        AppRole role = appRoleRepository.findByRole(roleName);
        if(role == null) throw new RuntimeException("Role not found");
        user.getRoles().remove(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }




}

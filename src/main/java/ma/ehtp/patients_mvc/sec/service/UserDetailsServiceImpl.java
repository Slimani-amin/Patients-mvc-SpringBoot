package ma.ehtp.patients_mvc.sec.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.ehtp.patients_mvc.sec.entities.AppUser;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException(String.format("User not found with username '%s'.", username));
       String[] roles = appUser.getRoles().stream().map(r -> r.getRole()).toArray(String[]::new);
        UserDetails userDetails = User
        .withUsername(appUser.getUsername())
        .password(appUser.getPassword())
        .roles(roles).build();
        return userDetails;
}

}

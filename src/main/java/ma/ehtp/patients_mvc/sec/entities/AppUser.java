package ma.ehtp.patients_mvc.sec.entities;

import java.util.List;

import org.springframework.data.annotation.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
@Builder
public class AppUser {
    @Id
    private String userId;
    @Column
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<AppRole> roles;
    

}

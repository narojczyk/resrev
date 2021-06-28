package pl.jwn.resrev.domain.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude="passwd")
//@NoArgsConstructor
public class User {
    @Id @NotEmpty
    @Column(length = 40, nullable = false, unique=true)
    private String uuid;

    @NotEmpty
    @Column(length = 16, nullable = false, unique=true)
    private String username;

    @Email
    @Column(length = 60, nullable = false, unique=true)
    private String email;

    @NotEmpty
    @Column(length = 300, nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String role;

    public User(){
        generateUUID();
    }

    public User(String username, String email, String passwd, String role){
        generateUUID();
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }

    public User(String username, String email, String passwd){
        generateUUID();
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.role = "ROLE_USER";
    }

    private void generateUUID(){
        this.uuid = UUID.randomUUID().toString();
    }

}

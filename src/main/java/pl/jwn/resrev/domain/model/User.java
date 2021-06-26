package pl.jwn.resrev.domain.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Random;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude="passwd")
@NoArgsConstructor
public class User {
    @Id @NotEmpty
    @Column(length = 40, nullable = false, unique=true)
    private String uuid;

    @NotEmpty
    @Column(length = 16, nullable = false, unique=true)
    private String login;

    @Email
    @Column(length = 60, nullable = false, unique=true)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private String passwd;

    @NotEmpty
    @Column(nullable = false)
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private String passwdSalt;

    @Range(min=0, max=9)
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private int accessLevel;

    public User(int accessLevel, String uuid, String login, String email){
        this.uuid = uuid;
        this.login = login;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public User(String login, String email, String passwd, int accessLevel){
        this.login = login;
        this.email = email;
        this.passwd = passwd; // dodać generowanie salt'a i hashowanie
        this.accessLevel = accessLevel;

        Random rand = new Random();
        this.passwdSalt = "salt" + rand.nextInt(10000);   // poprawic
        this.uuid = "uuid"+rand.nextInt(10000);   //poprawic
    }
}

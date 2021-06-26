package pl.jwn.resrev.domain.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude="passwd")
public class User {
    @Id @NotEmpty
    @Column(length = 40, nullable = false)
    private String uuid;

    @NotEmpty
    @Column(length = 16, nullable = false)
    private String login;

    @Email
    @Column(nullable = false)
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
    @Column(nullable = false)
    private int accessLevel;

    public User(String login, String email, String passwd, int accessLevel){
        this.login = login;
        this.email = email;
        this.passwd = passwd; // dodać generowanie salt'a i hashowanie
        this.accessLevel = accessLevel;
    }

    public User(int accessLevel, String uuid, String login, String email){
        this.uuid = uuid;
        this.login = login;
        this.email = email;
        this.accessLevel = accessLevel;
    }
}

package pl.jwn.resrev.domain.model;

import lombok.*;
import pl.jwn.resrev.utils.SQLTablesConstants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import static pl.jwn.resrev.utils.KeyGen.generateUUID;

@Entity
@Table(name = SQLTablesConstants.USERS)
@Getter @Setter @ToString(exclude="passwd")
public class User {
    @Id @NotEmpty
    @Setter(AccessLevel.NONE)
    @Column(length = 40, nullable = false, unique=true)
    private String uuid;

    @NotEmpty
    @Column(length = 16, nullable = false, unique=true)
    private String username;

    // dodać pole 'division' albo 'group'

    @Email
    @Column(length = 60, nullable = false, unique=true)
    private String email;

    @NotEmpty
    @Column(length = 300, nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String role;

    public User(){
        this.uuid = generateUUID();
    }

    public User(String username, String email, String passwd, String role){
        this();
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }
}

package pl.jwn.resrev.domain.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import java.util.UUID;

import static pl.jwn.resrev.utils.KeyGen.generateUUID;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude="passwd")
//@NoArgsConstructor
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

/*    public User(String username, String email, String passwd){
        this();
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.role = "ROLE_USER";
    }*/

//    private void generateUUID(){
//        this.uuid = UUID.randomUUID().toString();
//    }

}

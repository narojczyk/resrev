package pl.jwn.resrev.domain.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "share")
@Getter
@Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private Timestamp created;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String artefactUuid;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String userUuid;

    @NotEmpty
    @Column(length = 3000, nullable = false)
    private String commentMessage;

    public Comment(String artefactUuid, String userUuid, String commentMessage){
        this.created = new Timestamp(System.currentTimeMillis());
        this.artefactUuid = artefactUuid;
        this.userUuid = userUuid;
        this.commentMessage = commentMessage;
    }

}

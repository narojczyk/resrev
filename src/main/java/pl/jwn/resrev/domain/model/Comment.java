package pl.jwn.resrev.domain.model;


import lombok.*;
import pl.jwn.resrev.utils.SQLTablesConstants;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = SQLTablesConstants.COMMENTS)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime created;

    //TODO Standard zajęciowy
    /*
    @ManyToOne
    private Artefact artefact;
     */

    //TODO Wykorzystać @ForeignKey
    @NotEmpty
    @Column(length = 40, nullable = false)
    private String artefactUuid;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String commenterUuid;

    @NotEmpty
    @Column(length = 3000, nullable = false)
    private String commentMessage;

    public Comment(String artefactUuid, String commenterUuid, String commentMessage){
        this();
        this.created = LocalDateTime.now();
        this.artefactUuid = artefactUuid;
        this.commenterUuid = commenterUuid;
        this.commentMessage = commentMessage;
    }

}

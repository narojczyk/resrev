package pl.jwn.resrev.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static pl.jwn.resrev.utils.KeyGen.generateUUID;

@Entity
@Table(name = "share")
@Getter @Setter @ToString
@NoArgsConstructor
public class Share {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // unique na parę artefactUuid i userUuid ?
    @NotEmpty
    @Column(length = 40, nullable = false)
    private String artefactUuid;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String userUuid;

    public Share(String artefactUuid, String userUuid){
        this.artefactUuid = artefactUuid;
        this.userUuid = userUuid;
    }
}

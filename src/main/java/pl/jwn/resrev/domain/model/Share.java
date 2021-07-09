package pl.jwn.resrev.domain.model;

import lombok.*;
import pl.jwn.resrev.utils.SQLTablesConstants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = SQLTablesConstants.SHARES)
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
    private String sharedWithUuid;

    public Share(String artefactUuid, String sharedWithUuid){
        this.artefactUuid = artefactUuid;
        this.sharedWithUuid = sharedWithUuid;
    }
}

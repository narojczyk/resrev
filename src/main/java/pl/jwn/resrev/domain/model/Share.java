package pl.jwn.resrev.domain.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "share")
@Getter @Setter @ToString
@AllArgsConstructor
public class Share {
    // unique na parę artefactUuid i userUuid ?
    @Id @NotEmpty
    @Column(length = 40, nullable = false)
    private String atrefactUuid;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String userUuid;
}

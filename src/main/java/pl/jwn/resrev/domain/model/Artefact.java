package pl.jwn.resrev.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

import static pl.jwn.resrev.utils.KeyGen.generateUUID;

@Entity
@Table(name = "artefacts")
@Getter @Setter @ToString
public class Artefact {
    @Id @NotEmpty
    @Setter(AccessLevel.NONE)
    @Column(length = 40, nullable = false, unique=true)
    private String uuid;    // * (wymagane)

    @Column(length = 40, nullable = false)
    private String userUuid;    // * (wymagane)

    @NotEmpty
    @Column(length = 2000, nullable = false)
    private String container; // pojemnik na artefakt   *

    // Tehniczne atrybuty artefaktu
    @NotEmpty
    @Column(length = 16, nullable = false)
    private String type;     // archiwum | raport | rysunek *

    @NotEmpty
    @Column(length = 4, nullable = false)
    private String filetype; // zip | pdf | png *

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime created; // data *

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime modified;    // data *

    @NotEmpty
    @Column(length = 500, nullable = false)
    private String description; // opis *

    // Fizyczne atrybuty artefaktu
    @Column(length = 100)
    private String model;       // spheres | dimers | ....

    @Column(length = 3)
    private String dimension;   // 1D | 2D | 3D

    @Column(length = 4)
    private String initialSymmetry;    // fcc | bcc | hcp | ...

    @Column(length = 100)
    private String inclusionType;   // plane | channel | ...

    @Column(columnDefinition = "TINYINT(1)")
    private Integer inclusionCount;

    @Column(length = 25)
    private String inclusionDirection;

    @Column(length = 16)
    private String inclusionIteraction;

    @Column
    private Double sD;

    @Column(length = 16)
    private String matrixInteraction;   // typ oddziaływania

    //  ** parametry oddziaływań
    @Column
    private Double kS;

    @Column
    private Double bE;

    @Column
    private Double InvPowExp;

    @Column
    private Double sigma;

    @Column
    private Double epsilon;

    //  ** parametry zespołu termodynamicznego
    @Column
    private Double pressure;

    @Column
    private Double temperature;

    @Column
    private Double nParticles;

    @Column
    private Double Volume;

    @Column
    private Double Energy;

    public Artefact(){
        this.uuid = generateUUID();
        this.created = LocalDateTime.now();
        this.modified = created;
    }

    public Artefact(String userUuid, String container, String type, String filetype, String description){
        this();
        this.userUuid = userUuid;
        this.container = container;
        this.type = type;
        this.filetype = filetype;
        this.description = description;
    }

}

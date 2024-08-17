package br.com.postgis.postgres.domain;

import lombok.*;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

/**
 * Entidade que representa um local de votação.
 */
@Entity
@Getter
@Setter
@Table(name = "local_votacao")
@AllArgsConstructor
@NoArgsConstructor
public class LocalVotacao {

    /**
     * Identificador único do local de votação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do local de votação.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Longitude geográfica do local de votação.
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * Latitude geográfica do local de votação.
     */
    @Column(nullable = false)
    private Double latitude;

    @Column(columnDefinition = "MDSYS.SDO_GEOMETRY")
    private Point geoloc;

    /**
     * Sobrescrita do método toString para facilitar a exibição do objeto em formato de string.
     *
     * @return Uma representação em string do objeto {@code LocalVotacao}.
     */

//    @OneToOne
//    @JoinColumn(name = "sdo_geometry_join")
//    private SdoGeometry sdoGeometry;

    @Override
    public String toString() {
        return "LocalVotacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", geoloc=" + geoloc +
                '}';
    }
}

//    @ColumnTransformer(
//            read = "SDO_UTIL.TO_GEOJSON(endereco)",
//            write = "SDO_UTIL.FROM_GEOJSON(?)"
//    )
//    @JsonProperty("endereco")
//    @Column(columnDefinition="MDSYS.SDO_GEOMETRY", nullable = false, unique = true)
//    private SDOGeometryType endereco;

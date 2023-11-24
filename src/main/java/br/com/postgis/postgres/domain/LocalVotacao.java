package br.com.postgis.postgres.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.spatial.dialect.oracle.SDOGeometryType;

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


    public void SDOGeometryType(){

    }

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


//    @ColumnTransformer(
//            read = "SDO_UTIL.TO_GEOJSON(endereco)",
//            write = "SDO_UTIL.FROM_GEOJSON(?)"
//    )
//    @JsonProperty("endereco")
//    @Column(columnDefinition="MDSYS.SDO_GEOMETRY", nullable = false, unique = true)
//    private SDOGeometryType endereco;


    /**
     * Sobrescrita do método toString para facilitar a exibição do objeto em formato de string.
     *
     * @return Uma representação em string do objeto {@code LocalVotacao}.
     */

    @OneToOne
    @JoinColumn(name = "sdo_geometry_join")
    private SdoGeometry sdoGeometry;

    @Override
    public String toString() {
        return "LocalVotacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

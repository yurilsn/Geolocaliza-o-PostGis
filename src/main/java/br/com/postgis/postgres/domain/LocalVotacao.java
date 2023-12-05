package br.com.postgis.postgres.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import org.hibernate.annotations.Type;
import org.hibernate.spatial.dialect.oracle.SDOGeometryType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import javax.persistence.*;

import org.hibernate.spatial.JTSGeometryJavaType;



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

//    @JsonDeserialize(using = PointDeserializer.class)
//    @Column(columnDefinition = "MDSYS.SDO_GEOMETRY")
//    private Geometry geoloc;

//Tenho uma entidade no meu projeto que possui uma coluna do tipo Double com collumnDefinition = "MDSYS.SDOGEOMETRY", o problema é que quando envio um JSON




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
//                ", geoloc=" + geoloc +
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

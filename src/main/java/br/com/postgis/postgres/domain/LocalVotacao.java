package br.com.postgis.postgres.domain;

import lombok.*;
import org.geolatte.geom.codec.db.oracle.SDOGeometry;
import org.hibernate.spatial.Spatial;

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

    @Column(columnDefinition = "MDSYS.SDO_GEOMETRY")
    private Double geoloc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public Double getGeoloc() {
        return geoloc;
    }

    public void setGeoloc(Double geoloc) {
        this.geoloc = geoloc;
    }

    /**
     * Sobrescrita do método toString para facilitar a exibição do objeto em formato de string.
     *
     * @return Uma representação em string do objeto {@code LocalVotacao}.
     */
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

//    @ColumnTransformer(
//            read = "SDO_UTIL.TO_GEOJSON(endereco)",
//            write = "SDO_UTIL.FROM_GEOJSON(?)"
//    )
//    @JsonProperty("endereco")
//    @Column(columnDefinition="MDSYS.SDO_GEOMETRY", nullable = false, unique = true)
//    private SDOGeometryType endereco;
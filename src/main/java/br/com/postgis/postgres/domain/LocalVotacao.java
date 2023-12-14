package br.com.postgis.postgres.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;


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
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     * Latitude geográfica do local de votação.
     */
    @Column(nullable = false)
    private Double latitude;

    /**
     * Longitude geográfica do local de votação.
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * Objeto nativo Oracle para cálculo de distancia.
     */
    @JsonIgnore
    @Column(columnDefinition = "MDSYS.SDO_GEOMETRY")
    private Point geoloc;

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

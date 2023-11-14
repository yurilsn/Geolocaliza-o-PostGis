package br.com.postgis.postgres.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     * Longitude geográfica do local de votação.
     */
    @Column(nullable = false, unique = true)
    private Double longitude;

    /**
     * Latitude geográfica do local de votação.
     */
    @Column(nullable = false, unique = true)
    private Double latitude;

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

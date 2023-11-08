package br.com.xavecoding.regesc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BigDecimalType;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "local_votacao")
@AllArgsConstructor
@NoArgsConstructor
public class LocalVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    
    @Column(nullable = false, unique = true)
    private Double longitude;


    @Override
    public String toString() {
        return "LocalVotacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Column(nullable = false, unique = true)
    private Double latitude;

//    @Column(columnDefinition = "integer GENERATED ALWAYS AS (latitudeB + latitudeA) STORED", updatable = false, insertable = false)
//    private Integer distancia;
}




//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;

//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "professores")
//public class Professor {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    private String nome;
//    @Column(nullable = false, unique = true)
//    private String prontuario;
//}

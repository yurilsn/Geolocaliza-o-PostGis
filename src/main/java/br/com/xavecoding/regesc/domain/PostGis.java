package br.com.xavecoding.regesc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "LocalVotacao")
public class PostGis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Integer longitude;
    @Column(nullable = false, unique = true)
    private Integer latitude;
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

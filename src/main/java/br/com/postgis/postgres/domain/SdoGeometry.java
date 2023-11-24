package br.com.postgis.postgres.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@Table(name = "SdoGeometry")
@AllArgsConstructor
@NoArgsConstructor
public class SdoGeometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int gtype;

    @Column(nullable = false)
    private int srid;

    @Column
    private Double sdo_elem_info = null;

    @Column
    private Double sdo_ordinates = null;

    @OneToOne
    private SdoPointType sdoPointType;
}

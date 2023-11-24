package br.com.postgis.postgres.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@Table(name = "sdopointtype")
@AllArgsConstructor
@NoArgsConstructor
public class SdoPointType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column
    private Double altitude = null;
}

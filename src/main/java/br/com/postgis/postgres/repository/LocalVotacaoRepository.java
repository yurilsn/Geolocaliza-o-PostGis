package br.com.postgis.postgres.repository;

import br.com.postgis.postgres.domain.LocalVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define operações de banco de dados para a entidade {@link LocalVotacao}.
 */
public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {

 /**
  * Busca um {@link LocalVotacao} pelo nome.
  *
  * @param nome O nome do local de votação a ser pesquisado.
  * @return O local de votação encontrado ou null se não encontrado.
  */
 Optional<LocalVotacao> findByNome(String nome);

 /**
  * Calcula a distância entre dois pontos geográficos usando a fórmula de distância esférica.
  *
  * @param cidLat1 Latitude do ponto 1.
  * @param cidLong1 Longitude do ponto 1.
  * @param cidLat2 Latitude do ponto 2.
  * @param cidLong2 Longitude do ponto 2.
  * @return Uma lista contendo a distância entre os pontos.
  */
 @Query(value = """
        SELECT sdo_geom.sdo_distance(
          sdo_geometry(2001, 4326, sdo_point_type(:cidLong1, :cidLat1, null), null, null),
          sdo_geometry(2001, 4326, sdo_point_type(:cidLong2, :cidLat2, null), null, null),
          0.0001,
          'unit=KM'
        ) as distance
        from dual
        """, nativeQuery = true)
 List<Double> findLocalVotacaoByDistancia(
         @Param("cidLat1") Double cidLat1,
         @Param("cidLong1") Double cidLong1,
         @Param("cidLat2") Double cidLat2,
         @Param("cidLong2") Double cidLong2
 );

 @Query(value = """
        SELECT id, description, latitude, longitude, geoloc
        FROM places_with_distance p
        WHERE sdo_within_distance(
          p.geoloc,
          SDO_GEOMETRY(2001, 4326, SDO_POINT_TYPE(:cidLat, :cidLong, NULL), NULL, NULL),
          'distance=' || :raio || 'unit=KM'
        ) = 'TRUE';
        """, nativeQuery = true)
 List<LocalVotacao> findLocalVotacaoByProximidade(@Param("cidLat") Double cidLat, @Param("cidLong") Double cidLong, @Param("raio") Double raio);

// @Query(value = """
//        INSERT INTO local_votacao (nome, latitude, longitude, geoloc)
//        VALUES (:cidade, :cidLat, :cidLong, MDSYS.SDO_GEOMETRY(2001, 4326, MDSYS.SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL))
//        COMMIT;
//        """, nativeQuery = true)
// LocalVotacao saveLocalVotacaBySpatialData(@Param("cidade") String cidade,  @Param("cidLong") Double cidLong, @Param("cidLat") Double cidLat);
//@Query(value = "INSERT INTO local_votacao (nome, latitude, longitude, geoloc) " +
//        "VALUES (:cidade, :cidLat, :cidLong, MDSYS.SDO_GEOMETRY(2001, 4326, MDSYS.SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL))",
//        nativeQuery = true)
//LocalVotacao saveLocalVotacaBySpatialData(@Param("cidade") String cidade,
//                                          @Param("cidLong") Double cidLong,
//                                          @Param("cidLat") Double cidLat);
 @Query(value = "INSERT INTO local_votacao (nome, latitude, longitude, geoloc) " +
         "VALUES (:cidade, :cidLat, :cidLong, MDSYS.SDO_GEOMETRY(2001, 4326, MDSYS.SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL)) ",
         nativeQuery = true)
 LocalVotacao saveLocalVotacaBySpatialData(@Param("cidade") String cidade,
                                           @Param("cidLong") Double cidLong,
                                           @Param("cidLat") Double cidLat);

}


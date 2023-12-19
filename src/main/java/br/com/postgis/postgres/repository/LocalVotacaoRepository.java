package br.com.postgis.postgres.repository;

import br.com.postgis.postgres.domain.LocalVotacao;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Interface que define operações de banco de dados para a entidade {@link LocalVotacao}.
 */

@Transactional
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
  * @param cidLat1 Latitude do Local 1.
  * @param cidLong1 Longitude do Local 1.
  * @param cidLat2 Latitude do Local 2.
  * @param cidLong2 Longitude do Local 2.
  * @return A distância entre os pontos.
  */
 @Query(value = """
        SELECT sdo_geom.sdo_distance(
          sdo_geometry(2001, 4326, sdo_point_type(:cidLong1, :cidLat1, null), null, null),
          sdo_geometry(2001, 4326, sdo_point_type(:cidLong2, :cidLat2, null), null, null),
          0.0001,
          'unit=M'
        ) as distance
        from dual
        """, nativeQuery = true)
 List<Double> findLocalVotacaoByDistancia(
         @Param("cidLat1") Double cidLat1,
         @Param("cidLong1") Double cidLong1,
         @Param("cidLat2") Double cidLat2,
         @Param("cidLong2") Double cidLong2
 );



 /**
  * Realiza a persistência dos dados junto ao o ojeto nativo do Oracle MDSYS.SDO_GEOMETRY
  * @param local nome do Local.
  * @param cidLong Longitude do Local.
  * @param cidLat Latitude do Local.
  */
 @Modifying
 @Query(value = "INSERT INTO local_votacao (nome, latitude, longitude, geoloc) " +
         "VALUES (:local, :cidLat, :cidLong, SDO_GEOMETRY(2001, 4326, MDSYS.SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL))",
         nativeQuery = true)
 void saveLocalVotacaoBySpatialData(@Param("local") String local,
                                    @Param("cidLat") Double cidLat,
                                    @Param("cidLong") Double cidLong);


 /**
  * Realiza o update/PUT dos dados junto ao o ojeto nativo do Oracle MDSYS.SDO_GEOMETRY
  * @param local Nome do Local.
  * @param cidLong Longitude do ponto 1.
  * @param cidLat Latitude do ponto 2.
  * @param id Id do Local
  */
 @Modifying
 @Query(value = "UPDATE local_votacao " +
         "SET GEOLOC = SDO_GEOMETRY(2001, 4326, MDSYS.SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL), LATITUDE = :cidLat, LONGITUDE = :cidLong, NOME = :local " +
         "WHERE id = :id",
         nativeQuery = true)
 void updateLocalVotacaoBySpatialData(@Param("local") String local,
                                      @Param("cidLat") Double cidLat,
                                      @Param("cidLong") Double cidLong,
                                      @Param("id") Long id);


 /**
  * Realiza o calculo entre os pontos próximos de um determinado local, dentro de um determinado raio
  * @param cidLong Longitude do Local.
  * @param cidLat Latitude do Local.
  * @param raio raio a ser determinado
  */
 @Query(value =
         "SELECT * " +
         "FROM local_votacao l "+
         "WHERE sdo_within_distance(" +
         "SDO_GEOMETRY(2001, 4326, SDO_POINT_TYPE(l.longitude, l.latitude, NULL), NULL, NULL), " +
         "SDO_GEOMETRY(2001, 4326, SDO_POINT_TYPE(:cidLong, :cidLat, NULL), NULL, NULL), " +
         "'distance=' || :raio || ' unit=M'" +
         ") = 'TRUE'",
         nativeQuery = true)
 Collection<LocalVotacao> findLocalVotacaoByRaio(@Param("cidLat") Double cidLat,
                                                 @Param("cidLong") Double cidLong,
                                                 @Param("raio") String raio);

}


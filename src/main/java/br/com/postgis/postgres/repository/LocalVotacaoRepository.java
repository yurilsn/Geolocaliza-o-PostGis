package br.com.xavecoding.postgres.repository;

import br.com.xavecoding.postgres.domain.LocalVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
 LocalVotacao findByNome(String nome);

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
        SELECT ST_DistanceSpheroid(
            ST_GeomFromText('POINT(' || :cidLong1 || ' ' || :cidLat1 || ')', 4326),
            ST_GeomFromText('POINT(' || :cidLong2 || ' ' || :cidLat2 || ')', 4326),
            'SPHEROID[\"WGS 84\",6378137,298.257223563]'
        ) AS distancia
        """, nativeQuery = true)
 List<Double> findLocalVotacaoByDistancia(
         @Param("cidLat1") Double cidLat1,
         @Param("cidLong1") Double cidLong1,
         @Param("cidLat2") Double cidLat2,
         @Param("cidLong2") Double cidLong2
 );

 // Outras consultas podem ser adicionadas aqui conforme necessário.

}

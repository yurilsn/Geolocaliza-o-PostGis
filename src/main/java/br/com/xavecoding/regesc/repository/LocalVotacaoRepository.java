package br.com.xavecoding.regesc.repository;

import br.com.xavecoding.regesc.domain.LocalVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {
   @Query(value = "SELECT * FROM LocalVotacao =", nativeQuery = true)
    LocalVotacao findLocalVotacaoByNome(String local);


    LocalVotacao findByNome(String nome);

//    @Query(value = "SELECT ST_DistanceSpheroid(\n" +
//            "        ST_GeomFromText(:cidade1, 4326),\n" +
//            "        ST_GeomFromText(:cidade2, 4326),\n" +
//            "         'SPHEROID[\"WGS 84\",6378137,298.257223563]'\n" +
//            "      ) AS distancia;", nativeQuery = true)
//    List<Double> findLocalVotacaoBydistancia(@Param("cidade1") String cidade1, @Param("cidade2") String cidade2);
//

    @Query(value = """
        SELECT ST_DistanceSpheroid(
            ST_GeomFromText('POINT(' || :cidLat1 || ' ' || :cidLong1 || ')', 4326),
            ST_GeomFromText('POINT(' || :cidLat2 || ' ' || :cidLong2 || ')', 4326),
            'SPHEROID[\"WGS 84\",6378137,298.257223563]'
        ) AS distancia
        """, nativeQuery = true)
    List<Double> findLocalVotacaoByDistancia(
            @Param("cidLat1") Double cidLat1,
            @Param("cidLong1") Double cidLong1,
            @Param("cidLat2") Double cidLat2,
            @Param("cidLong2") Double cidLong2
    );


//    @Query(value = "SELECT * FROM LocalVotacao local_votacao", nativeQuery = true)
//    List<Double> findLocalVotacaoBydistancia(Integer status);

}

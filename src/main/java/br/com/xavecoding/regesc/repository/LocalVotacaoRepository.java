package br.com.xavecoding.regesc.repository;

import br.com.xavecoding.regesc.domain.LocalVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Long> {
//   @Query(value = "SELECT * FROM LocalVotacao =", nativeQuery = true)
//    LocalVotacao findLocalVotacaoByNome(String local);


    LocalVotacao findByNome(String nome);
}

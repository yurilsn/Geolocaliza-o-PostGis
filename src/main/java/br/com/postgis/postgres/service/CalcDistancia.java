package br.com.postgis.postgres.service;

import br.com.postgis.postgres.domain.LocalVotacao;
import br.com.postgis.postgres.repository.LocalVotacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Serviço para cálculo de distância entre dois pontos geográficos usando {@link LocalVotacaoRepository}.
 */
@Service
@AllArgsConstructor
public class CalcDistancia {

    private LocalVotacaoRepository localVotacaoRepository;

    /**
     * Calcula a distância entre dois pontos geográficos utilizando os nomes das cidades.
     *
     * @param cidade1 Nome da primeira cidade.
     * @param cidade2 Nome da segunda cidade.
     * @return Lista contendo a distância entre os pontos.
     */
    public List<Double> exec(String cidade1, String cidade2) {
        var cid1 = localVotacaoRepository.findByNome(cidade1).orElseThrow(() -> new EntityNotFoundException("Local votação não encontrado."));
        var cid2 = localVotacaoRepository.findByNome(cidade2).orElseThrow(() -> new EntityNotFoundException("Local votação não encontrado."));

        // Verifica se ambos os locais foram encontrados antes de calcular a distância
        return localVotacaoRepository.findLocalVotacaoByDistancia(cid1.getLatitude(), cid1.getLongitude(), cid2.getLatitude(), cid2.getLongitude());

    }

    public Collection<LocalVotacao> proximo(String cidade, String raio){
        var cid = localVotacaoRepository.findByNome(cidade).orElseThrow(() -> new EntityNotFoundException("Local votação não encontrado."));
        return localVotacaoRepository.findLocalVotacaoByRaio(cid.getLatitude(), cid.getLongitude(), raio);
    }

    public void spatialSave(LocalVotacao localVotacao){
        localVotacaoRepository.saveLocalVotacaoBySpatialData(localVotacao.getNome(), localVotacao.getLatitude(), localVotacao.getLongitude());
    }

    public void spatialUpdate(LocalVotacao localVotacao, Long id){
        localVotacaoRepository.updateLocalVotacaoBySpatialData(localVotacao.getNome(), localVotacao.getLatitude(), localVotacao.getLongitude(), id);
    }

}

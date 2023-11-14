package br.com.xavecoding.postgres.service;

import br.com.xavecoding.postgres.repository.LocalVotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para cálculo de distância entre dois pontos geográficos usando {@link LocalVotacaoRepository}.
 */
@Service
@AllArgsConstructor
public class CalcDistancia {

    private final LocalVotacaoRepository localVotacaoRepository;

    /**
     * Calcula a distância entre dois pontos geográficos utilizando os nomes das cidades.
     *
     * @param cidade1 Nome da primeira cidade.
     * @param cidade2 Nome da segunda cidade.
     * @return Lista contendo a distância entre os pontos.
     */
    public List<Double> calcDistanciaExec(String cidade1, String cidade2) {
        var cid1 = localVotacaoRepository.findByNome(cidade1);
        var cid2 = localVotacaoRepository.findByNome(cidade2);

        // Verifica se ambos os locais foram encontrados antes de calcular a distância
        if (cid1 != null && cid2 != null) {
            return localVotacaoRepository.findLocalVotacaoByDistancia(cid1.getLatitude(), cid1.getLongitude(), cid2.getLatitude(), cid2.getLongitude());
        } else {
            // Retorna null se algum dos locais não for encontrado
            return null;
        }
    }
}

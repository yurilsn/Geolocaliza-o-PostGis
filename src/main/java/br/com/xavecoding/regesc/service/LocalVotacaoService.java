package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.domain.LocalVotacao;
import br.com.xavecoding.regesc.repository.LocalVotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class LocalVotacaoService {
    private LocalVotacaoRepository localVotacaoRepository;

    public LocalVotacao getLocalVotacaoByName(Long id){
        return localVotacaoRepository.findById(id).orElse(null);
    }
}

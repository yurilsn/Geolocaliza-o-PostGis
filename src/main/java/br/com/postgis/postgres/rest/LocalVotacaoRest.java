package br.com.postgis.postgres.rest;

import br.com.postgis.postgres.service.CalcDistancia;
import br.com.postgis.postgres.domain.LocalVotacao;
import br.com.postgis.postgres.repository.LocalVotacaoRepository;

import lombok.AllArgsConstructor;

import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manipulação de {@link LocalVotacao}.
 */
@RestController
@RequestMapping("/api/LocalVotacao")
@AllArgsConstructor
public class LocalVotacaoRest {
    private LocalVotacaoRepository localVotacaoRepository;
    private CalcDistancia calcDistancia;

    /**
     * Retorna todos os locais de votação.
     *
     * @return ResponseEntity contendo a lista de todos os locais de votação.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LocalVotacao>> findAll() {
        return ResponseEntity.ok().body(localVotacaoRepository.findAll());
    }

    /**
     * Retorna um local de votação pelo ID.
     *
     * @param id O ID do local de votação a ser retornado.
     * @return ResponseEntity contendo o local de votação encontrado ou status 404 se não encontrado.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<LocalVotacao>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(localVotacaoRepository.findById(id));
    }

    /**
     * Salva um novo local de votação.
     *
     * @param localVotacao O local de votação a ser salvo.
     * @return ResponseEntity contendo o local de votação salvo.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocalVotacao> save(@RequestBody LocalVotacao localVotacao) {
        calcDistancia.spatialSave(localVotacao);
        return ResponseEntity.ok().build();
    }

    /**
     * Atualiza um local de votação pelo ID.
     *
     * @param updatedLocalVotacao O local de votação atualizado.
     * @param id O ID do local de votação a ser atualizado.
     * @return ResponseEntity contendo o local de votação atualizado.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocalVotacao> update(@RequestBody LocalVotacao updatedLocalVotacao, @PathVariable("id") Long id) {
        calcDistancia.spatialUpdate(updatedLocalVotacao, id);
        return ResponseEntity.ok().build();
    }

    /**
     * Exclui um local de votação pelo ID.
     *
     * @param id O ID do local de votação a ser excluído.
     * @return ResponseEntity vazio indicando sucesso.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        localVotacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Retorna a distância entre dois pontos geográficos.
     *
     * @param local1 Nome do primeiro Local.
     * @param local2 Nome do segundo Local.
     * @return ResponseEntity contendo a lista de distâncias entre os pontos.
     */
    @GetMapping("/distancia")
    public ResponseEntity<List<Double>> getDistance(@RequestParam String local1, @RequestParam String local2) {
        return ResponseEntity.ok().body(calcDistancia.exec(local1, local2));
    }

    /**
     * Retorna os Locais próximos do Local escolhido dentro de um determinado raio(em metros) escolhido pelo usuáiro.
     *
     * @param local Nome do Local.
     * @param raio raio (em metros).
     * @return ResponseEntity contendo a lista de distâncias entre os pontos.
     */
    @GetMapping("/proximos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<LocalVotacao>> getProximos(@RequestParam String local, @RequestParam String raio){
        return ResponseEntity.ok().body(calcDistancia.proximo(local, raio));
    }


}

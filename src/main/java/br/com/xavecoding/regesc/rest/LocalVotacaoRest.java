package br.com.xavecoding.regesc.rest;

import br.com.xavecoding.regesc.domain.LocalVotacao;
import br.com.xavecoding.regesc.repository.LocalVotacaoRepository;
import lombok.AllArgsConstructor;


import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/LocalVotacao")
@AllArgsConstructor
public class LocalVotacaoRest {
    private LocalVotacaoRepository localVotacaoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LocalVotacao>> findAll() {
        return ResponseEntity
                .ok()
                .body(localVotacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<LocalVotacao>> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(localVotacaoRepository.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocalVotacao> save(@RequestBody LocalVotacao localVotacao) {
        return ResponseEntity
                .ok(localVotacaoRepository.save(localVotacao));

    }

//    @GetMapping("/tudo")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<LocalVotacao> saveDistancia(@RequestBody LocalVotacao localVotacao){
//        return ResponseEntity
//                    .ok()
//                .body((LocalVotacao) localVotacaoRepository.findLocalVotacaoByNome("Ana"));
//    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LocalVotacao> update(@RequestBody LocalVotacao updatedPostGis, @PathVariable("id") Long id) {
        var postGis = localVotacaoRepository.findById(id).get();
        BeanUtils.copyProperties(updatedPostGis, postGis);
        return ResponseEntity
                .ok()
                .body(localVotacaoRepository.save(postGis));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        localVotacaoRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/cidades")
    public ResponseEntity<?> findCidade(@RequestParam String cidade1, @RequestParam String cidade2){
        var cid1 = localVotacaoRepository.findByNome(cidade1);
        var cid2 = localVotacaoRepository.findByNome(cidade2);

        return ResponseEntity.status(HttpStatus.OK).body("cidade 1 - Longitude = " + cid1.getLongitude() + "\ncidade 1 - Latitude = " + cid1.getLatitude());
    }

    @GetMapping("/distancia")
    public ResponseEntity<List<Double>> getDistance(@RequestParam String cidade1, @RequestParam String cidade2){
        var cid1 = localVotacaoRepository.findByNome(cidade1);
        var cid2 = localVotacaoRepository.findByNome(cidade2);

        String cidlatLon1 = "'POINT(" + String.valueOf(cid1.getLatitude()) + " " + String.valueOf(cid1.getLongitude()) + ")'";
        String cidLatLon2 = "'POINT(" + String.valueOf(cid2.getLatitude()) + " " + String.valueOf(cid2.getLongitude()) + ")'";

        return ResponseEntity
                .ok()
                .body(localVotacaoRepository.findLocalVotacaoByDistancia(cid1.getLatitude(), cid1.getLongitude(), cid2.getLatitude(), cid2.getLongitude()));
    }


}

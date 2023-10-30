package br.com.xavecoding.regesc.rest;

import br.com.xavecoding.regesc.domain.PostGis;
import br.com.xavecoding.regesc.repository.PostGisRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ApiRest {
    private PostGisRepository postGisRepository;

    @GetMapping("/PostGisis")
    public ResponseEntity<List<PostGis>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(postGisRepository.findAll());
    }

    @GetMapping("/PostGisis/{id}")
    public ResponseEntity<Optional<PostGis>> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postGisRepository.findById(id));
    }

    @PostMapping("/PostGisis")
    public ResponseEntity save(@RequestBody PostGis postGis){
        postGisRepository.save(postGis);
        return ResponseEntity.status(HttpStatus.OK).body("Sucesso!");

    }
}

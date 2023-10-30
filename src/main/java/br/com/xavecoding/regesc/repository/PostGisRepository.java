package br.com.xavecoding.regesc.repository;

import br.com.xavecoding.regesc.domain.PostGis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostGisRepository extends JpaRepository<PostGis, Long> {

}

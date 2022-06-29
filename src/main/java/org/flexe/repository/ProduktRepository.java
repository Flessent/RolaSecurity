package org.flexe.repository;

import java.util.UUID;

import org.flexe.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProduktRepository extends JpaRepository<Produkt, Integer>{
	@Transactional
	@Modifying
	@Query("delete from Produkt p where p.idProdukt=:idProdukt")
	void deleteProdukt(@Param("idProdukt") int idProdukt);
	//@Transactional
	//@Query("select codeSprache  from Sprache s where s.libelle=:libelle ")
	Produkt getProduktByIdProdukt(int idProdukt);
	
}

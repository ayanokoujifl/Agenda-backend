package com.ayanokoujifl.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ayanokoujifl.agenda.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	@Query("SELECT c FROM Contato c INNER JOIN c.telefones t ON t.numeroTelefone = :telefone")
	Contato findByTelefones(@Param("telefone") String telefone);
	
}

package com.ayanokoujifl.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayanokoujifl.agenda.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
	
}

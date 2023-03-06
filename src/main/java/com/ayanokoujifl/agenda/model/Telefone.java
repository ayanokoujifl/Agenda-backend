package com.ayanokoujifl.agenda.model;

import java.util.HashSet;
import java.util.Set;

import com.ayanokoujifl.agenda.model.enums.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String numeroTelefone;
	@JsonIgnore
	@ManyToOne
	private Contato contato;
	private Set<TipoTelefone> tipo = new HashSet<>();

	public Telefone() {
	}

	public Telefone(String numero_telefone, Integer tipo) {
		super();
		this.numeroTelefone = numero_telefone;
		this.tipo.add(TipoTelefone.toEnum(tipo));
	}
}

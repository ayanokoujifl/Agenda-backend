package com.ayanokoujifl.agenda.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ayanokoujifl.agenda.model.enums.TipoContato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "contato")
	private Set<Telefone> telefones = new HashSet<>();
	private String endereco;
	private Set<TipoContato> tipo = new HashSet<>();

	public Contato() {
		 new Telefone();
	}

	public Contato(String nome, String endereco, Integer tipo) {
		super();
		new Telefone();
		this.nome = nome;
		this.endereco = endereco;
		this.tipo.add(TipoContato.toEnum(tipo));
	}
}

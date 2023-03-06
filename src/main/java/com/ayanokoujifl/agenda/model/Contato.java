package com.ayanokoujifl.agenda.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ayanokoujifl.agenda.dto.ContatoDTO;
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
	}

	public Contato(ContatoDTO objDto) {
		this.nome = objDto.getNome();
		this.endereco = objDto.getEndereco();
		this.tipo = objDto.getTipoContato();
	}

	public Contato(String nome, String endereco, Integer tipo) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.tipo.add(TipoContato.toEnum(tipo));
	}

	public Contato(Long id, String nome, Telefone telefone, String endereco, TipoContato tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefones.add(telefone);
		this.endereco = endereco;
		this.tipo.add(tipo);
	}

	public Contato(String nome, Telefone telefone, String endereco, TipoContato tipoContato) {
		new Telefone(telefone.getNumeroTelefone(), tipoContato.getId());
		new Contato(null, nome, telefone, endereco, tipoContato);
	}
}

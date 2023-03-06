package com.ayanokoujifl.agenda.dto;

import java.util.Set;

import com.ayanokoujifl.agenda.model.enums.TipoContato;
import com.ayanokoujifl.agenda.model.enums.TipoTelefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDTO {
	private String nome;
	private String telefone;
	private String endereco;
	private Set<TipoContato> tipoContato;
	private Set<TipoTelefone> tipoTelefone;

	public ContatoDTO() {
		
	}

	public ContatoDTO(String nome, String telefone, String endereco, Set<TipoContato> tipoContato,
			Set<TipoTelefone> tipoTelefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.tipoContato = tipoContato;
		this.tipoTelefone = tipoTelefone;
	}	
}

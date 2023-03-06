package com.ayanokoujifl.agenda.model.enums;

import lombok.Getter;

@Getter
public enum TipoTelefone {

	CASA(0), TRABALHO(1), OUTRO(2);

	private int id;

	private TipoTelefone() {
	}

	private TipoTelefone(int id) {
		this.id = id;
	}

	public static TipoTelefone toEnum(Integer cod) {
		if (cod == null) {
			return null;
		} else {
			for (TipoTelefone tipo : TipoTelefone.values()) {
				if (cod.equals(tipo.getId())) {
					return tipo;
				}
			}
		}
		throw new IllegalArgumentException("ID inválido");
	}
}

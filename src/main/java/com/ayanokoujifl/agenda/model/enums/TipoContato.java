package com.ayanokoujifl.agenda.model.enums;

import lombok.Getter;

@Getter
public enum TipoContato {

	FAMILIA(0), AMIGOS(1), TRABALHO(2), OUTRO(3);

	private int id;

	private TipoContato() {
	}

	private TipoContato(int id) {
		this.id = id;
	}

	public static TipoContato toEnum(Integer cod) {
		if (cod == null) {
			return null;
		} else {
			for (TipoContato tipo : TipoContato.values()) {
				if (cod.equals(tipo.getId())) {
					return tipo;
				}
			}
		}
		throw new IllegalArgumentException("ID inválido");
	}

}

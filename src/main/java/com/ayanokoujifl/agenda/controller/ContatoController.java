package com.ayanokoujifl.agenda.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ayanokoujifl.agenda.model.Contato;
import com.ayanokoujifl.agenda.repositories.ContatoRepository;

@Controller
@RequestMapping(value = "/contatos")
public class ContatoController {

	@Autowired
	ContatoRepository repository;

	@GetMapping
	public ResponseEntity<List<Contato>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Contato> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id).get());
	}

	@GetMapping(value = "/number")
	public ResponseEntity<Contato> findByNumber(@RequestParam String number) {
		return ResponseEntity.ok().body(repository.findByTelefones(number));
	}

	@PostMapping
	public ResponseEntity<Void> saveContato(@RequestBody Contato contato) {
		repository.saveAndFlush(contato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato) {
		Contato oldObj = repository.findById(id).get();
		contato.setId(id);
		updateData(oldObj, contato);
		repository.saveAndFlush(contato);
		return ResponseEntity.noContent().build();
	}

	private void updateData(Contato oldObj, Contato newObj) {
		if (newObj.getNome() == null) {
			newObj.setNome(oldObj.getNome());
		}
		if (newObj.getEndereco() == null) {
			newObj.setEndereco(oldObj.getEndereco());
		}
		if (newObj.getTipo().isEmpty()) {
			newObj.setTipo(oldObj.getTipo());
		}
	}
}

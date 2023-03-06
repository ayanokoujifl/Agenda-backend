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

import com.ayanokoujifl.agenda.dto.ContatoDTO;
import com.ayanokoujifl.agenda.model.Contato;
import com.ayanokoujifl.agenda.model.Telefone;
import com.ayanokoujifl.agenda.repositories.ContatoRepository;
import com.ayanokoujifl.agenda.repositories.TelefoneRepository;
import com.ayanokoujifl.agenda.service.TwilioService;

@Controller
@RequestMapping(value = "/contatos")
public class ContatoController {

	@Autowired
	ContatoRepository repository;

	@Autowired
	TwilioService twilio;

	@Autowired
	TelefoneRepository telefoneRepository;

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
	public ResponseEntity<Void> saveContato(@RequestBody ContatoDTO objDto) {
		Telefone telefone = new Telefone(objDto);
		telefoneRepository.save(telefone);
		Contato contato = new Contato(objDto);
		contato.getTelefones().add(telefone);
		repository.saveAndFlush(contato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato) {
		contato.setId(id);
		repository.saveAndFlush(contato);
		return ResponseEntity.accepted().body(contato);
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

	@PostMapping(value = "/notify/{id}")
	public ResponseEntity<Void> notifySms(@PathVariable Long id, @RequestBody String message) {
		Contato c = repository.findById(id).get();
		String numberTo = c.getTelefones().stream().findFirst().get().getNumeroTelefone();
		System.out.println(numberTo);
		twilio.sendSms(id, "+" + numberTo, message);
		return ResponseEntity.noContent().build();
	}
}

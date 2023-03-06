package com.ayanokoujifl.agenda;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ayanokoujifl.agenda.model.Contato;
import com.ayanokoujifl.agenda.model.Telefone;
import com.ayanokoujifl.agenda.repositories.ContatoRepository;
import com.ayanokoujifl.agenda.repositories.TelefoneRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	TelefoneRepository telefoneRepository;
	@Autowired
	ContatoRepository contatoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Telefone t1 = new Telefone("38997569902", 0);
		 * 
		 * Contato c1 = new Contato("Eu", "Bairro da amizade", 0);
		 * c1.getTelefones().addAll(Arrays.asList(t1)); t1.setContato(c1);
		 * 
		 * Telefone t2 = new Telefone("38998278249", 0); Contato c2 = new
		 * Contato("Edgar", "Rua ai", 0); c2.getTelefones().add(t2); t2.setContato(c2);
		 * 
		 * contatoRepository.saveAll(Arrays.asList(c1, c2));
		 * telefoneRepository.saveAll(Arrays.asList(t1, t2));
		 */
	}
}

package com.apirest.webflux;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;

import reactor.core.publisher.Flux;

/*
@Component // Anotação que vai fazer o spring gerenciar automaticamente o bean
public class DummyData implements CommandLineRunner {

	private final PlaylistRepository playlistRepository;

	public DummyData(PlaylistRepository playlistRepository) {
		super();
		this.playlistRepository = playlistRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		playlistRepository.deleteAll() // Vai deletar tudo, caso já tenha algum dado populado
			.thenMany(
				Flux.just("API Rest Spring Boot", "Deploy de aplicação java no IBM Cloud", "Java 8",
						"Github", "Spring Security", "Web Service Restfull", "Bean no Spring Framework")
				.map(nome -> new Playlist(UUID.randomUUID().toString(), nome)) // Irá adicionar algumas playlista automaticamente, através do map, com o ID aleatorio
				.flatMap(playlistRepository::save)) // metodo save do PlaylistRepository
				.subscribe();
	}
}
*/
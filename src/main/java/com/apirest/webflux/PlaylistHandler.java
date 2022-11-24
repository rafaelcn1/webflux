package com.apirest.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Mono;

//@Component // Para mostrar que ela será um bean e será gerenciando pelo spring
public class PlaylistHandler {

	@Autowired
	PlaylistService playlistService; // Para utlizar os metodos de transação com o banco

	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON) // O conteudo será do tipo JSON
				.body(playlistService.findAll(), Playlist.class); // O corpo irá ultilizar o metodo findAll do
																	// playlistService, onde o tipo de retorno será do
																	// tipo Playlist.class
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id"); // Id recebido pelo path
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON) // O conteudo será do tipo JSON
				.body(playlistService.findById(id), Playlist.class); // O corpo irá ultilizar o metodo findById do
																		// playlistService passando o valor do ID, onde
																		// o tipo de retorno será do tipo Playlist.class
	}

	public Mono<ServerResponse> save(ServerRequest request) {
		// playlist capturada do corpo da requisição convertendo para Mono
		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class); 
		
		// Passando a playlist que será salva no banco, através do metodo fromPublisher, onde será passado a playlist, usando o flatMap para passar o metodo save da playlistService, passando a referencia "::", depois passando o tipo que será salvo, no caso o Playlist.class 
		BodyInserter<Mono<Playlist>, ReactiveHttpOutputMessage> fromPublisher = BodyInserters.fromPublisher(playlist.flatMap(playlistService::save), Playlist.class);
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON) // O conteudo será do tipo JSON
				.body(fromPublisher); // O corpo vai ter a playlist recuperada atraves do fromPublisher
	}


}

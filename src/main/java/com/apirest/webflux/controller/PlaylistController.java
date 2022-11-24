package com.apirest.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController // Anotação para controlar as requisições
// Comentado para usar o handler e router
public class PlaylistController {

	@Autowired // Ponto de injeção para iniciar a class PlaylistService
	PlaylistService playlistService;

	@GetMapping( value="/playlist") // Mapeando com o valor a ser passado na utl
	public Flux<Playlist> getPlaylist() { // Metodo para retornar todas as playlist com o tipo Flux
		return playlistService.findAll();
	}

	@GetMapping(value="/playlist/{id}") // {id} será o valor do id da playlist que iremos listar
	public Mono<Playlist> getPlaylistId(@PathVariable String id) { // O id será passado pelo @PathVariable via url,
																	// retornando um mono de Playlist
		return playlistService.findById(id);

	}

	@PostMapping(value="/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist) { // @RequestBody irá informar que uma playlist será enviada em formato de json, a playlist vai pelo corpo da requsição
		return playlistService.save(playlist);
	}
	
	@GetMapping(value="/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // Value será o valor que o user vai digitar no navegador, e a produces será o metodo que será retornado
	public Flux<Tuple2<Long, Playlist>> getPlaylistEvents(){
		
		Flux<Long> intervalo = Flux.interval(Duration.ofSeconds(10)); // Definição do intervalor
		Flux<Playlist> playlists = playlistService.findAll(); // retorno de todas as playlists cadastradas
		
		Flux<Tuple2<Long, Playlist>> fluxZip = Flux.zip(intervalo, playlists); // Retorno do flux
		
		return fluxZip;
	}

}

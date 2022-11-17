package com.apirest.webflux.services;

import com.apirest.webflux.document.Playlist;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {

	Flux<Playlist> findAll(); // Irá retorar o objeto tipo flux e não mais uma list como de costume
	
	Mono<Playlist> findById(String id); // Irá retornar um objeto do tipo mono e não o objeto playlist como de costume
	
	Mono<Playlist> save(Playlist playlist); // Irá retornar um objeto do tipo mono caso tenha sido salvo com sucesso
}

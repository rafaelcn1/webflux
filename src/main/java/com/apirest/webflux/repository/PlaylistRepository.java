package com.apirest.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apirest.webflux.document.Playlist;

/*
 * Após o extends os parametros Playlist é a class Playlist com a anotação @Document e o String é o tipo da anotação ID da class playlist, no caso o nosso ID é do tipo String
 * */
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {

}

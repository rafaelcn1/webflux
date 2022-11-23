package com.apirest.webflux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;



@Configuration
public class PlaylistRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(PlaylistHandler handler){
		return RouterFunctions
				.route(GET("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::findAll) // Listar todas as playlists cadastradas, usando o metodo findAll referenciando "::" atraves do handler, da classe PlaylistHandler  
				.andRoute(GET("/playlist/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)  // Listar a playlists cadastradas, usando o metodo findById referenciando "::" atraves do handler, da classe PlaylistHandler
				.andRoute(POST("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::save);
			
	}
	

}
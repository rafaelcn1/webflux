# Projeto de estudo webflux

https://www.youtube.com/watch?v=cWvwjCObkxQ&list=PL8iIphQOyG-CyD9uuRTMiqxEut5QAKHga&index=2

0 - Criar o projeto maven no site https://start.spring.io/

1 - Criar e Configurar a database no https://cloud.mongodb.com/

2 - Configurar o arquivo application.properties
	spring.data.mongodb.database=<nome da database>
	spring.data.mongodb.uri=<URI que foi gerado no mongodb.com>

3 - Criar uma class Playlist
	Usando a anotação @Document, para criar automaticamento no mongomlab

4 - Criar a class interface PlaylistRepository extends ReactiveMongoRepository
	O parametro Playlist é a class Playlist com a anotação @Document 
	O parametro String é o tipo da anotação ID da class playlist, no caso o nosso ID é do tipo String

5 - Criar a class DummyData
	Usando a anotação @Component, para fazer o spring gerenciar automaticamente o bean
	Iniciando o PlaylistRepository, para utilizarmos o metodo save
	Implementar o metodo public void run (@Override) 
	Usando o metodo deleteAll() do populadoplaylistRepository para deletar tudo, caso já tenha algum dado
	Depois vamos usar o metodo save do PlaylistRepository adicionar algumas playlista automaticamente, através do map, com o ID aleatorio

6 - Depois de popular o banco, vamos comentar a class DummyData	 
	


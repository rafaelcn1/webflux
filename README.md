# Projeto de estudo webflux

https://www.youtube.com/playlist?list=PL8iIphQOyG-CyD9uuRTMiqxEut5QAKHga

Spring Webflux! Ele permite trabalharmos com programação reativa em uma aplicação java web dentro do Spring.

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
	
7 - Criação da classe PlaylistService do tipo Service para realizar as transações do banco

	Sobreescrever os metodos:
	findAll(), onde irá retorar o objeto tipo flux e não mais uma list como de costume
	findById(String id), onde irá retornar um objeto do tipo mono e não o objeto playlist como de costume
	save(Playlist playlist), onde irá retornar um objeto do tipo mono caso tenha sido salvo com sucesso
	
8 - Criar uma classe PlaylistServiceImpl para implementar os metodos da classe PlaylistService
	Usaremos a anotação @Service
	Implementaremos a class PlaylistService e adicionaremos os metodos da classe 
	Adicionar um ponto de injeção usando a anotação @Autowired sobre o objeto PlaylistRepository
	usar os metodos do PlaylistRepository conforme os metodos implementados
	
9 - Criando um controler, para receber as requisições (endpoint)
	Usaremos a anotação @RestController
	Adicionar um ponto de injeção usando a anotação @Autowired sobre o objeto PlaylistService
	Criar um metodo para retornar (do tipo Flux) todas as playlist salva no banco e Usar no metodo a anotação @GetMapping recebendo o valor "playlists"
	Criar um metodo para retornar (do tipo Mono) uma playlist salva no banco, usando o parametro ID atraves da anotação @PathVariable sendo passado o tipo e o nome do parametro String id e Usar no metodo a anotação @GetMapping recebendo o valor "/playlist/{id}"
	Criar um metodo para salvar uma playlist, onde será retornardo (do tipo Mono) uma playlist, iremos usar a anotação @PostMapping recebendo o value="/playlist", o metodo irá receber um objeto do tipo playlista com a anotação @RequestBody pois a requisição da playlist será enviada pelo corpo da requsiçãoem e no formato de json, se não usar o @PostMapping será enviado como nulo
	

10 - Criar uma classe handler
	Handler, vamos determinar quais sao os metodos e quais vão responder as regras de negocios, no caso do projeto, será a busca e salvar os produtos no banco
	
	Criar uma classe PlaylistHandler, usando a anotação @Component na classe.
	criar um ponto de injeção da classe PlaylistService com a anotação @Autowired
	Implementar um metodo para retornar todas as playlist (findAll) cadastrada no banco, retornando um objeto Flex de ServerResponse, para montar a resposta para ser enviado como retorno, irá receber um parametro ServerRequest
	Implementar um metodo para retornar uma playlist (findById) cadastrada no banco, retornando um objeto Flex de ServerResponse, para montar a resposta para ser enviado como retorno, irá receber um parametro id do ServerRequest, atraves do pathVariable
	
11 - Criar uma classe router
		Router, vai ser a responsavel para gerenciar as requsições dos metodos handler
		
		Criar uma class PlaylistRouter, usando a anotação @Configuration
		Criar um metodo router com a anotação @Bean recebendo como parametro a classe PlaylistHandler que criamos, com o tipo RouterFunction<ServerResponse>, onde será retornando um RouterFunctions

para fazer chamadas com o estilo funcional (endpoint)
Obs.: Quando for usar o handler e router, temos que comentar a anotação @RestController da classe PlaylistController para nao dar conflitos com o passo 9
		

12 - Construção de Stream Events
	Na class Controller:
		Criar um metodo sem receber nenhum parametro;
		Usando a anotação @GetMapping, pois é uma chamada GET da URL, passando o value="/playlist/events", definindo o parametro produce, passando um MediaType.TEXT_EVENT_STREAM_VALUE
		O metodo tera o retorno Flux<Tuple2>, passando dois elementos, um tipo Long para o intervalo e o outro o objeto, no caso a playlist. Ex: Flux<Tuple2<Long, Playlist> ;
		Criar um flux do tipo long, para definir o intervalo da resposta;
		Buscar todos os dados cadastrado no banco, no caso as playlists, atraves do playlistService.findAll()
		Defenir o retorno o intervalo e a play list, atraves do metodo ZIP do FLUX do react
	
	  	
	
	
	
		
	
	

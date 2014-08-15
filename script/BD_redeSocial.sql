
CREATE TABLE usuario(
	id_usuario integer not null,
	nome_completo VARCHAR( 200 ) NOT NULL,
	email VARCHAR( 180 ) NOT NULL unique,
	senha VARCHAR( 12 ) NOT NULL,
	data_nascimento Date NOT NULL,
	apelido VARCHAR( 60 ),
	cidade VARCHAR ( 80 ),
	foto_perfil VARCHAR( 300 ),
	status_perfil BOOLEAN DEFAULT TRUE,
	PRIMARY KEY(id_usuario)
);

CREATE TABLE locais_estudo(
	cod_usuario INTEGER NOT NULL,
	nome VARCHAR(300) NOT NULL,
	PRIMARY KEY(cod_usuario, nome),
	FOREIGN KEY(cod_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE locais_trabalho(
	cod_usuario INTEGER NOT NULL,
	nome VARCHAR(300),
	PRIMARY KEY(cod_usuario),
	FOREIGN KEY(cod_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE relacionamento(
	cod_proprietario INTEGER NOT NULL,
	cod_amigo INTEGER NOT NULL,
	tipo VARCHAR(30) NOT NULL,
	solicitacao VARCHAR(30) NOT NULL,
	PRIMARY KEY(cod_proprietario, cod_amigo),
	FOREIGN KEY(cod_proprietario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(cod_amigo) REFERENCES usuario(id_usuario)
);

CREATE TABLE foto(
	id_foto SERIAL,
	cod_usuario INTEGER NOT NULL,
	descricao VARCHAR(300),
	Data DATE NOT NULL,
	caminho_foto varchar(300),
	PRIMARY KEY(id_foto),
	FOREIGN KEY(cod_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE Mensagem(
	id_mensagem SERIAL,
	mensagem VARCHAR(600) NOT NULL,
	Data date NOT NULL,
	PRIMARY KEY(Id_mensagem)
);

create table enviar_mensagem(

	cod_mensagem integer not null,
	cod_remetente integer not null,
	cod_destinatario integer not null,

	primary key (cod_mensagem, cod_remetente, cod_destinatario),
	foreign key (cod_mensagem) references mensagem(id_mensagem),
	foreign key (cod_remetente) references usuario(id_usuario),
	foreign key (cod_destinatario) references usuario(id_usuario)

);

CREATE TABLE grupo(
	id_grupo SERIAL,
	cod_usuario_criador INTEGER NOT NULL,
	descricao VARCHAR(300) NOT NULL,
	fundador VARCHAR(60) NOT NULL,
	nome VARCHAR(60) unique NOT NULL,
	data DATE NOT NULL,
	PRIMARY KEY(id_grupo),
	FOREIGN KEY(cod_usuario_criador) REFERENCES usuario(id_usuario)
);

CREATE TABLE topico(
	id_topico SERIAL,
	cod_usuario INTEGER NOT NULL,
	cod_grupo INTEGER NOT NULL,
	data Date NOT NULL,
	nome VARCHAR(120) NOT NULL,
	PRIMARY KEY(id_topico),
	FOREIGN KEY(cod_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(cod_grupo) REFERENCES grupo(id_grupo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE comentario_topico(
	id_comentario_topico SERIAL,
	cod_usuario INTEGER NOT NULL,
	cod_topico INTEGER NOT NULL,
	data date NOT NULL,
	comentario_topico VARCHAR(600) NOT NULL,
	PRIMARY KEY(id_comentario_topico),
	FOREIGN KEY(cod_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(cod_topico) REFERENCES topico(id_topico) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE participa_grupo(

	cod_usuario_participante INTEGER,
	cod_grupo INTEGER,

	PRIMARY KEY(cod_usuario_participante,cod_grupo),
	FOREIGN KEY(cod_usuario_participante) REFERENCES usuario(id_usuario),
	FOREIGN KEY(cod_grupo) REFERENCES grupo(id_grupo) ON DELETE CASCADE ON UPDATE CASCADE
	
);
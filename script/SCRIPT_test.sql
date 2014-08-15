-- Novos valores para as tabelas
-- Tabela Usuario
INSERT INTO usuario (id_usuario, data_nascimento, email, apelido, cidade, nome_completo, foto_perfil, senha, status_perfil) VALUES (1220558405, '2014-09-30', 'felipedebritolira@gmail.com', 'felipe', 'sousa', 'felipe de brito lira', 'c://imagens_perfil', '123456', true);
INSERT INTO usuario (id_usuario, data_nascimento, email, apelido, cidade, nome_completo, foto_perfil, senha, status_perfil) VALUES (1434501423, '2014-10-23', 'felipedebritolira2@gmail.com', 'brito', 'sousa', 'brito lira felipe', 'c://imagens_perfil/1', '123456', true);
INSERT INTO usuario (id_usuario, data_nascimento, email, apelido, cidade, nome_completo, foto_perfil, senha, status_perfil) VALUES (947450563, '2014-05-13', 'vampiro020@gmail.com', 'vampiro', 'sousa', 'vampiro da silva', 'c://imgaens_user/2', '123456', true);

-- Tabela relacionamento
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (1220558405, 1434501423, 'AMIGO', 'CONFIRMADA');
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (1434501423, 1220558405, 'AMIGO', 'CONFIRMADA');
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (1220558405, 947450563, 'AMIGO', 'CONFIRMADA');
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (947450563, 1220558405, 'AMIGO', 'CONFIRMADA');
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (947450563, 1434501423, 'AMIGO', 'CONFIRMADA');
INSERT INTO relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao) VALUES (1434501423, 947450563, 'AMIGO', 'CONFIRMADA');

-- Tabela locais de estudo
INSERT INTO locais_estudo (cod_usuario, nome) VALUES (1220558405, 'E.E.E.M Mestre Julio Sarmento');
INSERT INTO locais_estudo (cod_usuario, nome) VALUES (1220558405, 'Colégio Monteiro Lobato');
INSERT INTO locais_estudo (cod_usuario, nome) VALUES (1434501423, 'IFPB');

-- Tabela locais de trabalho
INSERT INTO locais_trabalho (cod_usuario, nome) VALUES (1220558405, 'Colégio Monteiro Lobato');
INSERT INTO locais_trabalho (cod_usuario, nome) VALUES (1434501423, 'Colégio Monteiro Lobato');
INSERT INTO locais_trabalho (cod_usuario, nome) VALUES (947450563, 'Recursive');

-- Tabela foto
INSERT INTO foto (id_foto, cod_usuario, descricao, data, caminho_foto) VALUES (1, 1220558405, 'Niver', '2014-03-01', 'c://fotos_usuer');
INSERT INTO foto (id_foto, cod_usuario, descricao, data, caminho_foto) VALUES (2, 1434501423, 'Casa', '2014-05-13', 'c://fotos_user/2');
INSERT INTO foto (id_foto, cod_usuario, descricao, data, caminho_foto) VALUES (3, 1220558405, 'Escola', '2014-05-13', 'c://fotos_user/1');

-- Tabela mensagem
INSERT INTO mensagem (id_mensagem, mensagem, data) VALUES (1, 'Olá! Tudo Bem?', '2014-05-13');
INSERT INTO mensagem (id_mensagem, mensagem, data) VALUES (2, '????', '2014-05-13');
INSERT INTO mensagem (id_mensagem, mensagem, data) VALUES (3, 'Oi!! Está sim, e com vc?', '2014-05-13');

-- Tabela enviar
INSERT INTO enviar_mensagem(cod_mensagem, cod_remetente, cod_destinatario) VALUES(1,1220558405,1434501423);
INSERT INTO enviar_mensagem(cod_mensagem, cod_remetente, cod_destinatario) VALUES(2,1220558405,947450563);
INSERT INTO enviar_mensagem(cod_mensagem, cod_remetente, cod_destinatario) VALUES(3,1434501423,1220558405);

-- Tabela grupo
INSERT INTO grupo (id_grupo, cod_usuario, descricao, fundador, nome, data) VALUES (1, 1220558405, 'Forum de discurção sobre Java', 'FELIPE', 'JAVA RANCH', '2014-05-13');
INSERT INTO grupo (id_grupo, cod_usuario, descricao, fundador, nome, data) VALUES (2, 1434501423, 'Fórum de discusão em JAVA', 'Brito', 'java home', '2014-05-13');

-- Tabela topico
INSERT INTO topico (id_topico, cod_usuario, cod_grupo, data, nome) VALUES (1, 1220558405, 1, '2014-05-13', 'Discussão sobre Collections');
INSERT INTO topico (id_topico, cod_usuario, cod_grupo, data, nome) VALUES (2, 1220558405, 2, '2014-05-13', 'Dúvidas sobre C!!!');

-- Tabela comentario topico
INSERT INTO comentario_topico (id_comentario_topico, cod_usuario, cod_topico, data, comentario_topico) VALUES (1, 1220558405, 1, '2014-05-13', 'Dúvida sobre Collections');
INSERT INTO comentario_topico (id_comentario_topico, cod_usuario, cod_topico, data, comentario_topico) VALUES (2, 1220558405, 1, '2014-05-13', 'utilize o pacote java.util.*');
INSERT INTO comentario_topico (id_comentario_topico, cod_usuario, cod_topico, data, comentario_topico) VALUES (3, 1220558405, 1, '2014-05-13', 'ou utilize o java.util.Collection');
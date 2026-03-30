CREATE DATABASE atividade_bd;

USE atividade_bd;

CREATE TABLE grupo(
	letra_identificadora char(1) PRIMARY KEY,
    lugar1 varchar(30) DEFAULT 'A definir',
    lugar2 varchar(30) DEFAULT 'A definir',
    lugar3 varchar(30) DEFAULT 'A definir',
    lugar4 varchar(30) DEFAULT 'A definir');
    
CREATE TABLE pais_sede(
	nome_pais varchar(30) PRIMARY KEY,
    numero_jogos_do_pais INT CHECK (numero_jogos_do_pais >= 0),
    numero_estadios INT,
    nome_mascote varchar(30));
    
CREATE TABLE estadio(
	id INT PRIMARY KEY,
    cidade varchar(30),
    capacidade_maxima INT CHECK (capacidade_maxima > 0),
    nome varchar(30),
    fk_pais_sede_nome_pais varchar(30),
    FOREIGN KEY (fk_pais_sede_nome_pais) REFERENCES pais_sede(nome_pais) 
    ON UPDATE CASCADE);
    
CREATE TABLE selecao(
	inscricao INT PRIMARY KEY,
	ranking_da_fifa INT UNIQUE,
    media_idade INT, 
    media_gols INT DEFAULT 0,
    continente varchar(30),
    fk_grupo_letra_identificadora char(1) REFERENCES grupo(letra_identificadora),
    pontuacao INT CHECK (pontuacao >= 0),
    nome varchar(30) NOT NULL);

CREATE TABLE mascote(
	pais varchar(30) , 
    animal varchar(30) , 
    nome varchar(30),  
    fk_selecao_inscricao INT REFERENCES selecao(inscricao),
    CONSTRAINT pk_mascote PRIMARY KEY(pais, animal, fk_selecao_inscricao));

CREATE TABLE comissao_tecnica(
	inscricao INT PRIMARY KEY, 
    fk_inscricao_selecao INT REFERENCES selecao(inscricao));
    
CREATE TABLE treinador(
	fk_comissao_tecnica_inscricao INT PRIMARY KEY REFERENCES comissao_tecnica(inscricao),
	estilo_de_jogo varchar(30),
    nome varchar(30));

CREATE TABLE preparador_fisico(
	fk_comissao_tecnica_inscricao INT PRIMARY KEY REFERENCES comissao_tecnica(inscricao),
	especializacao varchar(30),
    nome varchar(30));
    
CREATE TABLE auxiliar_tecnico(
	fk_comissao_tecnica_inscricao INT PRIMARY KEY REFERENCES comissao_tecnica(inscricao),
	especialidade varchar(30),
    nome varchar(30));
    
CREATE TABLE jogo(
	id INT PRIMARY KEY,
    placar varchar(60),
    data DATETIME, 
    vencedor varchar(30), 
	perdedor varchar(30), 
	tipo_jogo varchar(30));
    
CREATE TABLE contido(
	fk_jogo_id INT REFERENCES jogo(id), 
	fk_selecao_inscricao INT REFERENCES selecao(inscricao),
    CONSTRAINT pk_contido PRIMARY KEY(fk_jogo_id, fk_selecao_inscricao));

CREATE TABLE jogador(
	inscricao INT PRIMARY KEY, 
    numero_da_camisa INT, 
    posicao varchar(30), 
    nome varchar(30) NOT NULL, 
    idade INT, 
    fk_selecao_inscricao INT REFERENCES selecao(inscricao), 
    capitao INT,
    FOREIGN KEY (fk_selecao_inscricao) REFERENCES selecao(inscricao) ON DELETE SET NULL,
    FOREIGN KEY (capitao) REFERENCES jogador(inscricao));
    
CREATE TABLE posicao(
	NomePosicao varchar(30),
    inscricao_jogador INT REFERENCES jogador(inscricao),
    CONSTRAINT pk_posicao PRIMARY KEY (NomePosicao, inscricao_jogador));
    
CREATE TABLE participa_jogo_estadio_jogador(
	fk_jogador_inscricao INT REFERENCES jogador(inscricao),
	fk_jogo_id INT REFERENCES jogo(id), 
	fk_estadio_id INT REFERENCES estadio(id),
	CONSTRAINT pk_participa PRIMARY KEY(fk_jogador_inscricao, fk_jogo_id));

INSERT INTO grupo (letra_identificadora) VALUES 
('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H');

INSERT INTO grupo (letra_identificadora) VALUES 
('I'), ('J'), ('K'), ('L'), ('M'), ('N'), ('O'), ('P'), ('Q'), ('R'), 
('S'), ('T'), ('U'), ('V'), ('W'), ('X'), ('Y'), ('Z'), ('1'), ('2'), ('3'), ('4');

INSERT INTO selecao (inscricao, ranking_da_fifa, media_idade, media_gols, continente, fk_grupo_letra_identificadora, pontuacao, nome) VALUES
(1, 1, 27, 2, 'América do Sul', 'A', 100, 'Brasil'),
(2, 2, 26, 1, 'Europa', 'A', 95, 'França'),
(3, 3, 28, 2, 'América do Sul', 'B', 90, 'Argentina'),
(4, 4, 25, 1, 'Europa', 'B', 88, 'Inglaterra'),
(5, 5, 29, 1, 'Europa', 'C', 85, 'Bélgica'),
(6, 6, 26, 2, 'Europa', 'C', 82, 'Portugal'),
(7, 7, 24, 1, 'Europa', 'D', 80, 'Espanha'),
(8, 8, 27, 1, 'Europa', 'D', 78, 'Holanda'),
(9, 9, 28, 1, 'Europa', 'E', 75, 'Itália'),
(10, 10, 25, 2, 'Europa', 'E', 73, 'Alemanha'),
(11, 11, 26, 1, 'América do Norte', 'F', 70, 'EUA'),
(12, 12, 27, 1, 'América do Norte', 'F', 68, 'México'),
(13, 13, 28, 0, 'África', 'G', 65, 'Marrocos'),
(14, 14, 25, 1, 'África', 'G', 63, 'Senegal'),
(15, 15, 24, 1, 'Ásia', 'H', 60, 'Japão'),
(16, 16, 26, 1, 'Ásia', 'H', 58, 'Coreia do Sul'),
(17, 17, 27, 0, 'América do Sul', 'I', 55, 'Uruguai'),
(18, 18, 28, 1, 'Europa', 'I', 53, 'Suíça'),
(19, 19, 25, 1, 'Europa', 'J', 50, 'Dinamarca'),
(20, 20, 26, 0, 'Europa', 'J', 48, 'Croácia'),
(21, 21, 27, 1, 'América do Sul', 'K', 45, 'Colômbia'),
(22, 22, 25, 0, 'África', 'K', 43, 'Egito'),
(23, 23, 29, 1, 'Europa', 'L', 40, 'Suécia'),
(24, 24, 26, 1, 'Europa', 'L', 38, 'Polônia'),
(25, 25, 27, 0, 'América do Sul', 'M', 35, 'Chile'),
(26, 26, 24, 0, 'África', 'M', 33, 'Nigéria'),
(27, 27, 28, 1, 'África', 'N', 30, 'Camarões'),
(28, 28, 26, 0, 'Europa', 'N', 28, 'Áustria'),
(29, 29, 25, 1, 'Europa', 'O', 25, 'Noruega'),
(30, 30, 27, 0, 'Europa', 'O', 22, 'Ucrânia');
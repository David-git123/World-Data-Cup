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
    cidade varchar(50),
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
('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I'), ('J'),
('K'), ('L'), ('M'), ('N'), ('O'), ('P'), ('Q'), ('R'), ('S'), ('T'),
('U'), ('V'), ('W'), ('X'), ('Y'), ('Z'), ('1'), ('2'), ('3'), ('4');

INSERT INTO pais_sede VALUES 
('Brasil', 7, 12, 'Fuleco'), ('Alemanha', 6, 10, 'Goleo'), ('Africa do Sul', 3, 10, 'Zakumi'),
('Japao', 4, 8, 'Ato'), ('Coreia do Sul', 4, 10, 'Kaz'), ('Franca', 7, 10, 'Footix'),
('EUA', 5, 9, 'Striker'), ('Mexico', 3, 5, 'Pique'), ('Italia', 6, 12, 'Ciao'),
('Espanha', 5, 10, 'Naranjito'), ('Argentina', 7, 8, 'Gauchito'), ('Inglaterra', 6, 8, 'Willie'),
('Chile', 4, 4, 'Juanito'), ('Suica', 3, 6, 'Worldie'), ('Uruguai', 4, 3, 'Tero'),
('Portugal', 2, 5, 'Quinas'), ('Canada', 1, 3, 'Nanuck'), ('Australia', 2, 6, 'Olly'),
('Catar', 3, 8, 'Laeeb'), ('Russia', 5, 12, 'Zabivaka'), ('Belgica', 2, 4, 'Diabo'),
('Holanda', 4, 5, 'Lion'), ('Suecia', 3, 4, 'Elk'), ('Noruega', 1, 2, 'Viking'),
('Egito', 1, 3, 'Sphinx'), ('China', 1, 10, 'Dragon'), ('India', 0, 5, 'Tiger'),
('Peru', 2, 3, 'Vicu'), ('Colombia', 3, 4, 'Coffee'), ('Grecia', 2, 3, 'Zeus');

INSERT INTO estadio VALUES 
(1, 'Rio', 78000, 'Maracana', 'Brasil'), (2, 'Berlim', 74000, 'Olympiastadion', 'Alemanha'),
(3, 'Soweto', 94000, 'Soccer City', 'Africa do Sul'), (4, 'Saitama', 63000, 'Saitama Stadium', 'Japao'),
(5, 'Seul', 66000, 'Seoul World Cup', 'Coreia do Sul'), (6, 'Paris', 80000, 'Stade de France', 'Franca'),
(7, 'Pasadena', 90000, 'Rose Bowl', 'EUA'), (8, 'CDMX', 87000, 'Estadio Azteca', 'Mexico'),
(9, 'Roma', 70000, 'Stadio Olimpico', 'Italia'), (10, 'Madrid', 81000, 'Santiago Bernabeu', 'Espanha'),
(11, 'B. Aires', 70000, 'Monumental', 'Argentina'), (12, 'Londres', 90000, 'Wembley', 'Inglaterra'),
(13, 'Santiago', 48000, 'Nacional', 'Chile'), (14, 'Berna', 32000, 'Wankdorf', 'Suica'),
(15, 'Montevidéu', 60000, 'Centenario', 'Uruguai'), (16, 'Lisboa', 65000, 'Estadio da Luz', 'Portugal'),
(17, 'Toronto', 30000, 'BMO Field', 'Canada'), (18, 'Sydney', 83000, 'ANZ Stadium', 'Australia'),
(19, 'Doha', 88000, 'Lusail Stadium', 'Catar'), (20, 'Moscou', 81000, 'Luzhniki', 'Russia'),
(21, 'Bruxelas', 50000, 'King Baudouin', 'Belgica'), (22, 'Amsterda', 55000, 'Johan Cruyff', 'Holanda'),
(23, 'Estocolmo', 50000, 'Friends Arena', 'Suecia'), (24, 'Oslo', 28000, 'Ullevaal', 'Noruega'),
(25, 'Cairo', 75000, 'Cairo International', 'Egito'), (26, 'Pequim', 80000, 'Birds Nest', 'China'),
(27, 'Mumbai', 50000, 'DY Patil', 'India'), (28, 'Lima', 43000, 'Estadio Nacional', 'Peru'),
(29, 'Bogota', 36000, 'El Campin', 'Colombia'), (30, 'Atenas', 69000, 'Olympic Stadium', 'Grecia');

INSERT INTO selecao VALUES
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

INSERT INTO mascote VALUES 
('Brasil', 'Tatu-bola', 'Fuleco', 1), ('Franca', 'Galo', 'Footix', 2),
('Argentina', 'Gaucho', 'Gauchito', 3), ('Inglaterra', 'Leao', 'Willie', 4),
('Belgica', 'Diabo', 'Red', 5), ('Alemanha', 'Leao', 'Goleo', 6),
('Espanha', 'Laranja', 'Naranjito', 7), ('Holanda', 'Leao', 'Dutchy', 8),
('Portugal', 'Anjo', 'Quinas', 9), ('Senegal', 'Leao', 'Lion', 10),
('Dinamarca', 'Viking', 'Rollo', 11), ('Uruguai', 'Pássaro', 'Tero', 12),
('Marrocos', 'Leao', 'Atlas', 13), ('Croacia', 'Cachorro', 'Dalmatas', 14),
('Japao', 'Robo', 'Ato', 15), ('Suica', 'Menino', 'Worldie', 16),
('Coreia', 'Robo', 'Kaz', 17), ('EUA', 'Cachorro', 'Striker', 18),
('Mexico', 'Pimenta', 'Pique', 19), ('Italia', 'Boneco', 'Ciao', 20),
('Chile', 'Condor', 'Andino', 21), ('Colombia', 'Cafe', 'Juan', 22),
('Suecia', 'Alce', 'Elk', 23), ('Polonia', 'Aguia', 'Bialy', 24),
('Egito', 'Farao', 'Tut', 25), ('Peru', 'Vicunha', 'Vicu', 26),
('Ira', 'Guepardo', 'Asiatico', 27), ('Gana', 'Estrela', 'Black Star', 28),
('Catar', 'Lenço', 'Laeeb', 29), ('Nigéria', 'Águia', 'Super', 30);

INSERT INTO comissao_tecnica VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20),
(21, 21), (22, 22), (23, 23), (24, 24), (25, 25), (26, 26), (27, 27), (28, 28), (29, 29), (30, 30);

INSERT INTO treinador VALUES 
(1, 'Ofensivo', 'Tite'), (2, 'Equilibrado', 'Deschamps'), (3, 'Posse de Bola', 'Scaloni'),
(4, 'Vertical', 'Southgate'), (5, 'Ofensivo', 'Tedesto'), (6, 'Pressão', 'Nagelsmann'),
(7, 'Posse', 'De la Fuente'), (8, 'Total', 'Koeman'), (9, 'Contra-ataque', 'Martinez'),
(10, 'Defensivo', 'Cisse'), (11, 'Equilibrado', 'Blanc'), (12, 'Tatico', 'Hjulmand'),
(13, 'Garra', 'Bielsa'), (14, 'Defensivo', 'Regragui'), (15, 'Equilibrado', 'Dalic'),
(16, 'Disciplina', 'Moriyasu'), (17, 'Organizado', 'Yakin'), (18, 'Veloz', 'Klinsmann'),
(19, 'Fisico', 'Berhalter'), (20, 'Pressao', 'Lozano'), (21, 'Tatico', 'Spalletti'),
(22, 'Ofensivo', 'Berizzo'), (23, 'Posse', 'Lorenzo'), (24, 'Defensivo', 'Andersson'),
(25, 'Vertical', 'Page'), (26, 'Fisico', 'Vitoria'), (27, 'Equilibrado', 'Reynoso'),
(28, 'Defensivo', 'Ghalenoei'), (29, 'Ofensivo', 'Probierz'), (30, 'Veloz', 'Hughton');

INSERT INTO auxiliar_tecnico VALUES 
(1, 'Defesa', 'Cléber Xavier'), (2, 'Ataque', 'Guy Stephan'), (3, 'Tática', 'Walter Samuel'),
(4, 'Bolas Paradas', 'Steve Holland'), (5, 'Meio-campo', 'Andreas Hinkel'), (6, 'Análise', 'Benjamin Glück'),
(7, 'Jovens', 'Santi Denia'), (8, 'Defesa', 'Erwin Koeman'), (9, 'Transição', 'Anthony Barry'),
(10, 'Físico-Tático', 'Regis Bogaert'), (11, 'Ataque', 'Franck Passi'), (12, 'Defesa', 'Morten Wieghorst'),
(13, 'Pressão', 'Pablo Quiroga'), (14, 'Vídeo', 'Rachid Benmahmoud'), (15, 'Goleiros', 'Vedran Corluka'),
(16, 'Tática', 'Hiroshi Nanami'), (17, 'Defesa', 'Giorgio Contini'), (18, 'Ataque', 'Andreas Herzog'),
(19, 'Estatística', 'B. J. Callaghan'), (20, 'Campo', 'Jaime Lozano Jr'), (21, 'Posse', 'Marco Domenichini'),
(22, 'Defesa', 'Mariano Soso'), (23, 'Meio-campo', 'Luis Amaranto Perea'), (24, 'Fisico', 'Peter Wettergren'),
(25, 'Psicologia', 'Rob Page Jr'), (26, 'Tática', 'Arnaldo Teixeira'), (27, 'Ataque', 'Joaquin del Solar'),
(28, 'Goleiros', 'Saeed Al-Hajri'), (29, 'Defesa', 'Robert Kasper'), (30, 'Base', 'Chris Ramsey');

INSERT INTO preparador_fisico VALUES 
(1, 'Resistencia', 'Fabio'), (2, 'Velocidade', 'Jean'), (3, 'Força', 'Pablo'), (4, 'Resistencia', 'John'), (5, 'Recuperacao', 'Luc'),
(6, 'Força', 'Hans'), (7, 'Velocidade', 'Jose'), (8, 'Resistencia', 'Pieter'), (9, 'Força', 'Nuno'), (10, 'Recuperacao', 'Moussa'),
(11, 'Resistencia', 'Pierre'), (12, 'Velocidade', 'Erik'), (13, 'Força', 'Luis'), (14, 'Resistencia', 'Ahmed'), (15, 'Recuperacao', 'Ivan'),
(16, 'Força', 'Kenji'), (17, 'Velocidade', 'Beat'), (18, 'Resistencia', 'Kim'), (19, 'Força', 'Mike'), (20, 'Recuperacao', 'Carlos'),
(21, 'Resistencia', 'Marco'), (22, 'Velocidade', 'Diego'), (23, 'Força', 'James'), (24, 'Resistencia', 'Sven'), (25, 'Recuperacao', 'Gareth'),
(26, 'Força', 'Said'), (27, 'Velocidade', 'Raul'), (28, 'Resistencia', 'Ali'), (29, 'Força', 'Andrzej'), (30, 'Recuperacao', 'Kofi');

INSERT INTO jogo VALUES 
(1, '2-1', '2026-06-10 15:00:00', 'Brasil', 'França', 'Fase de Grupos'),
(2, '0-0', '2026-06-10 18:00:00', 'Empate', 'Empate', 'Fase de Grupos'),
(3, '3-1', '2026-06-11 12:00:00', 'Argentina', 'Inglaterra', 'Fase de Grupos'),
(4, '1-0', '2026-06-11 15:00:00', 'Bélgica', 'Alemanha', 'Fase de Grupos'),
(5, '2-2', '2026-06-11 20:00:00', 'Empate', 'Empate', 'Fase de Grupos'),
(6, '4-0', '2026-06-12 10:00:00', 'Espanha', 'Holanda', 'Fase de Grupos'),
(7, '1-2', '2026-06-12 13:00:00', 'Portugal', 'Senegal', 'Fase de Grupos'),
(8, '0-1', '2026-06-12 16:00:00', 'Dinamarca', 'França', 'Fase de Grupos'),
(9, '2-0', '2026-06-13 11:00:00', 'Uruguai', 'Marrocos', 'Fase de Grupos'),
(10, '1-1', '2026-06-13 14:00:00', 'Empate', 'Empate', 'Fase de Grupos'),
(11, '3-2', '2026-06-13 17:00:00', 'Japão', 'Suíça', 'Fase de Grupos'),
(12, '0-2', '2026-06-14 12:00:00', 'EUA', 'Coreia do Sul', 'Fase de Grupos'),
(13, '1-0', '2026-06-14 15:00:00', 'Itália', 'México', 'Fase de Grupos'),
(14, '0-0', '2026-06-14 18:00:00', 'Empate', 'Empate', 'Fase de Grupos'),
(15, '2-1', '2026-06-15 13:00:00', 'Colômbia', 'Suécia', 'Fase de Grupos'),
(16, '3-0', '2026-06-15 16:00:00', 'Gales', 'Egito', 'Fase de Grupos'),
(17, '1-1', '2026-06-16 12:00:00', 'Empate', 'Empate', 'Fase de Grupos'),
(18, '2-0', '2026-06-16 15:00:00', 'Peru', 'Irã', 'Fase de Grupos'),
(19, '4-1', '2026-06-17 14:00:00', 'Polônia', 'Gana', 'Fase de Grupos'),
(20, '1-0', '2026-06-18 20:00:00', 'Brasil', 'Argentina', 'Fase de Grupos'),
(21, '2-2', '2026-06-20 15:00:00', 'Empate', 'Empate', 'Oitavas'),
(22, '3-1', '2026-06-21 15:00:00', 'França', 'Inglaterra', 'Oitavas'),
(23, '1-0', '2026-06-22 15:00:00', 'Espanha', 'Itália', 'Oitavas'),
(24, '2-1', '2026-06-23 15:00:00', 'Portugal', 'Holanda', 'Oitavas'),
(25, '3-0', '2026-06-25 15:00:00', 'Brasil', 'Japão', 'Quartas'),
(26, '1-1', '2026-06-26 15:00:00', 'Argentina', 'Bélgica', 'Quartas'),
(27, '2-0', '2026-06-28 15:00:00', 'França', 'Espanha', 'Semifinal'),
(28, '3-2', '2026-06-29 15:00:00', 'Brasil', 'Portugal', 'Semifinal'),
(29, '1-0', '2026-07-02 12:00:00', 'Espanha', 'Portugal', '3º Lugar'),
(30, '2-1', '2026-07-03 16:00:00', 'Brasil', 'França', 'Final');

INSERT INTO jogador VALUES 
(1, 10, 'Atacante', 'Neymar Jr', 34, 1, NULL),
(2, 1, 'Goleiro', 'Alisson Becker', 33, 1, 1),
(3, 4, 'Zagueiro', 'Marquinhos', 31, 1, 1),
(4, 10, 'Atacante', 'Kylian Mbappe', 27, 2, NULL),
(5, 7, 'Atacante', 'Antoine Griezmann', 35, 2, 4),
(6, 10, 'Atacante', 'Lionel Messi', 38, 3, NULL),
(7, 11, 'Meia', 'Angel Di Maria', 38, 3, 6),
(8, 9, 'Atacante', 'Julian Alvarez', 26, 3, 6),
(9, 9, 'Atacante', 'Harry Kane', 32, 4, NULL),
(10, 10, 'Meia', 'Jude Bellingham', 22, 4, 9),
(11, 7, 'Meia', 'Kevin De Bruyne', 34, 5, NULL),
(12, 1, 'Goleiro', 'Thibaut Courtois', 33, 5, 11),
(13, 7, 'Atacante', 'Cristiano Ronaldo', 41, 6, NULL),
(14, 8, 'Meia', 'Bruno Fernandes', 31, 6, 13),
(15, 20, 'Meia', 'Pedri', 23, 7, NULL),
(16, 1, 'Goleiro', 'Unai Simon', 28, 7, 15),
(17, 4, 'Zagueiro', 'Virgil van Dijk', 34, 8, NULL),
(18, 21, 'Meia', 'Frenkie de Jong', 28, 8, 17),
(19, 10, 'Meia', 'Luka Modric', 40, 9, NULL),
(20, 1, 'Goleiro', 'Gianluigi Donnarumma', 27, 9, 19),
(21, 10, 'Meia', 'Jamal Musiala', 23, 10, NULL),
(22, 1, 'Goleiro', 'Manuel Neuer', 40, 10, 21),
(23, 10, 'Atacante', 'Christian Pulisic', 27, 11, NULL),
(24, 13, 'Goleiro', 'Guillermo Ochoa', 40, 12, NULL),
(25, 2, 'Zagueiro', 'Achraf Hakimi', 27, 13, NULL),
(26, 10, 'Atacante', 'Sadio Mane', 33, 14, NULL),
(27, 7, 'Atacante', 'Son Heung-min', 33, 15, NULL),
(28, 15, 'Meia', 'Fede Valverde', 27, 17, NULL),
(29, 10, 'Meia', 'Christian Eriksen', 34, 18, NULL),
(30, 9, 'Atacante', 'Robert Lewandowski', 37, 24, NULL);

INSERT INTO contido VALUES 
(1, 1), (1, 2), (2, 3), (2, 4), (3, 5), (3, 6), (4, 7), (4, 8), (5, 9), (5, 10),
(6, 11), (6, 12), (7, 13), (7, 14), (8, 15), (8, 16), (9, 17), (9, 18), (10, 19), (10, 20),
(11, 21), (11, 22), (12, 23), (12, 24), (13, 25), (13, 26), (14, 27), (14, 28), (15, 29), (15, 30);

INSERT INTO posicao VALUES 
('Ponta Esquerda', 1), ('Goleiro', 2), ('Zagueiro Central', 3), ('Falso 9', 4), ('Meia Direita', 5),
('Ponta Direita', 6), ('Centroavante', 7), ('Meia Armador', 8), ('Centroavante', 9), ('Meia Ofensivo', 10),
('Regista', 11), ('Pivô', 12), ('Meia Central', 13), ('Goleiro', 14), ('Centroavante', 15),
('Ponta Direita', 16), ('Ponta Esquerda', 17), ('Meia Atacante', 18), ('Goleiro', 19), ('Meia', 20),
('Volante', 21), ('Goleiro', 22), ('Líbero', 23), ('Atacante', 24), ('Box-to-box', 25),
('Lateral Direito', 26), ('Meia Criativo', 27), ('Goleiro', 28), ('Segundo Atacante', 29), ('Meia', 30);

INSERT INTO participa_jogo_estadio_jogador VALUES 
(1, 1, 1), (2, 1, 1), (3, 1, 1), (4, 1, 1), (5, 1, 1),
(6, 3, 11), (7, 3, 11), (8, 3, 11), (9, 3, 11), (10, 3, 11),
(11, 4, 21), (12, 4, 21), (13, 7, 16), (14, 7, 16), (15, 5, 10),
(16, 5, 10), (17, 5, 22), (18, 5, 22), (19, 10, 30), (20, 10, 30),
(21, 4, 2), (22, 4, 2), (23, 12, 7), (24, 13, 8), (25, 9, 15),
(26, 7, 14), (27, 11, 4), (28, 9, 15), (29, 8, 23), (30, 12, 24);
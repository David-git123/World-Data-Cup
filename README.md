# World Data Cup

## Descrição Minimundo

O mini mundo tem como tema principal a copa do mundo de clubes que acontecerá no ano de 2026. As principais entidades são seleção, jogo, jogador e país sede

## Entidades e Relacionamentos

* Seleção - Grupo (1:N): Uma seleção pertence a um único grupo, mas um grupo possui várias seleções.

* Seleção - Jogador (1:N): Uma seleção possui muitos jogadores.

* País Sede - Estádio (1:N): Um país pode ter vários estádios sediando jogos.

* Jogo - Seleção (N:N): Um jogo envolve duas seleções (mapeado via tabela contido).

* Comissão Técnica (Herança): A comissao_tecnica é uma entidade genérica que se especializa em treinador, preparador_fisico ou auxiliar_tecnico.

* Mascote é entidade fraca

* Jogador tem o auto-relacionamento comanda

## Modelo conceitual

![Diagrama de Entidade e Relacionamento](imagens/conceitual.jpeg)

## Modelo lógico

![Diagrama de Entidade e Relacionamento](imagens/logico.jpeg)

## Tecnologias utilizadas

* Linguagem: Java 
* Bnaco de dados: MySQL

## Query's

* Adição(Insert)

"INSERT INTO jogador (numero_da_camisa, inscricao, posicao, nome, idade, fK_selecao_inscricao, capitao) VALUES (?, ?, ?, ?, ?, ?, ?)";

"INSERT INTO selecao (inscricao,media_idade, media_gols, ranking_da_fifa, continente, pontuacao, nome, fk_grupo_letra_identificadoVALUES (?, ?, ?, ?, ?,?,?,?)";

* Atualização(Update)

"UPDATE jogador SET numero_da_camisa = ?, posicao = ?,nome= ?, idade = ?, fk_selecao_inscricao= ?, capitao = ?  WHERE inscricao = ?";


"UPDATE selecao SET media_idade =?, media_gols = ?, ranking_da_fifa= ? , continente = ?, pontuacao = ? , nome = ?, fk_grupo_letra_identificadora = ? WHERE inscricao = ?";

* Remoção(Delete)

"DELETE FROM jogador WHERE inscricao = ?";

"DELETE FROM selecao WHERE inscricao = ?";

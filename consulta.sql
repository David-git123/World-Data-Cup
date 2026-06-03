use Atividade_bd9;

-- 1 consulta com join + group by + having. 
-- Mostra continentes com média acima de 25

SELECT s.continente, AVG(j.idade) AS media_geral_idade 
FROM selecao s 
JOIN jogador j ON s.inscricao = j.fk_selecao_inscricao 
GROUP BY s.continente 
HAVING AVG(j.idade) > 25; 

-- 1 com 2 joins + where      
-- mostra o jogador, a seleção e o nome do mascote da seleção apenas da seleção brasileira

SELECT j.nome AS Nome_Jogador, s.nome AS Nome_Selecao, m.nome AS Nome_Mascote
FROM jogador j
JOIN selecao s ON j.fk_selecao_inscricao = s.inscricao
JOIN mascote m ON s.inscricao = m.fk_selecao_inscricao
WHERE s.nome = 'Brasil';

-- 1 com anti join (esquerda ou direita)     
-- mostra seleções que não possuem jogadores cadastrado

SELECT s.nome 
FROM selecao s
LEFT JOIN jogador j ON s.inscricao = j.fk_selecao_inscricao
WHERE j.inscricao IS NULL;

-- 1 com subconsulta    
-- mostra os jogos onde o vencedor possui pontuação maior que 5

SELECT id, placar, vencedor 
FROM jogo 
WHERE vencedor IN (SELECT nome FROM selecao WHERE pontuacao > 5);

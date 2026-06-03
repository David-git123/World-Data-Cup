use Atividade_bd9;

-- 1 com 3 joins + where   
-- Centraliza dados técnicos e locais de atuação dos jogadores em que o tipo de jogo é fase de grupos
CREATE VIEW v_escalacao_estadios AS
SELECT j.nome AS jogador, e.nome AS estadio, ps.nome_pais AS sede, jo.id AS id_jogo
FROM jogador j
JOIN participa_jogo_estadio_jogador pje ON j.inscricao = pje.fk_jogador_inscricao
JOIN estadio e ON pje.fk_estadio_id = e.id
JOIN pais_sede ps ON e.fk_pais_sede_nome_pais = ps.nome_pais
JOIN jogo jo ON pje.fk_jogo_id = jo.id
WHERE jo.tipo_jogo = 'Fase de Grupos';

-- 1 com 1 join + subconsulta    
-- Lista seleções com ranking melhor que a média 

CREATE VIEW v_selecoes_topo AS
SELECT s.nome AS selecao, s.ranking_da_fifa, m.nome AS mascote
FROM selecao s
JOIN mascote m ON s.inscricao = m.fk_selecao_inscricao
WHERE s.ranking_da_fifa < (SELECT AVG(ranking_da_fifa) FROM selecao);


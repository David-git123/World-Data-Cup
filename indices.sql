-- Acelera os JOINs entre as tabelas jogador e selecao 
CREATE INDEX idx_fk_selecao ON jogador(fk_selecao_inscricao); 

-- Otimiza buscas de filtragem e ordenação baseadas na posição das seleções no ranking, acelerando comparações de maior/menor e subconsultas. 
CREATE INDEX idx_ranking_fifa ON selecao(ranking_da_fifa);

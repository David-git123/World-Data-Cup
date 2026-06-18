SET GLOBAL log_bin_trust_function_creators = 1;

-- Automatiza a atualização de pontos(3 por vitória, 1 por empate) e o recálculo da média móvel de gols de uma seleção após o término de uma partida

DROP PROCEDURE IF EXISTS atualizar_pontuacao_selecao;

DELIMITER $$
CREATE PROCEDURE atualizar_pontuacao_selecao (
  p_inscricao   INT,
  p_resultado   VARCHAR(10),
  p_gols_feitos INT
)
BEGIN
  UPDATE selecao
  SET
    pontuacao  = pontuacao +
      CASE p_resultado
        WHEN 'vitoria' THEN 3
        WHEN 'empate'  THEN 1
        ELSE 0
      END,
    media_gols = ROUND((media_gols + p_gols_feitos) / 2, 0)
  WHERE inscricao = p_inscricao;
END $$
DELIMITER ;

-- Classifica as seleções de um grupo do 1º ao 4º lugar, ordenando-as pelos critérios oficiais de desempate do torneio (pontuação e ranking FIFA). O cursor preenche as vagas físicas da tabela de grupos para definir quem avança de fase.

DROP PROCEDURE IF EXISTS atualizar_lugares_grupo;

DELIMITER $$
CREATE PROCEDURE atualizar_lugares_grupo (p_letra CHAR(1))
BEGIN
  DECLARE done      INT DEFAULT FALSE;
  DECLARE v_nome    VARCHAR(30);
  DECLARE v_posicao INT DEFAULT 1;

  DECLARE cur_grupos CURSOR FOR
    SELECT nome FROM selecao
    WHERE fk_grupo_letra_identificadora = p_letra
    ORDER BY pontuacao DESC, ranking_da_fifa ASC;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  UPDATE grupo
  SET lugar1 = 'A definir', lugar2 = 'A definir',
      lugar3 = 'A definir', lugar4 = 'A definir'
  WHERE letra_identificadora = p_letra;

  OPEN cur_grupos;

  cursor_loop: LOOP
    FETCH cur_grupos INTO v_nome;

    IF done THEN
      LEAVE cursor_loop;
    END IF;

    CASE v_posicao
      WHEN 1 THEN
        UPDATE grupo SET lugar1 = v_nome WHERE letra_identificadora = p_letra;
      WHEN 2 THEN
        UPDATE grupo SET lugar2 = v_nome WHERE letra_identificadora = p_letra;
      WHEN 3 THEN
        UPDATE grupo SET lugar3 = v_nome WHERE letra_identificadora = p_letra;
      WHEN 4 THEN
        UPDATE grupo SET lugar4 = v_nome WHERE letra_identificadora = p_letra;
    END CASE;

    SET v_posicao = v_posicao + 1;
  END LOOP;

  CLOSE cur_grupos;
END $$
DELIMITER ;

-- TABELA DE LOGS
-- Justificativa semântica:
-- Em um sistema de torneio, alterações em dados críticos, como a pontuação de uma seleção, precisam ser auditadas. Isso permite rastrear quem alterou, quando alterou e qual foi a mudança realizada.


CREATE TABLE IF NOT EXISTS logs_auditoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tabela_nome VARCHAR(100) NOT NULL,
  acao VARCHAR(20) NOT NULL,
  chave_registro VARCHAR(200) NOT NULL,
  coluna_alterada VARCHAR(100) NOT NULL,
  valor_antigo TEXT,
  valor_novo TEXT,
  usuario VARCHAR(100),
  data_hora DATETIME DEFAULT CURRENT_TIMESTAMP
);


-- TRIGGER 1
-- Justificativa semântica:
-- Sempre que a pontuação de uma seleção mudar, registramos o evento na tabela de logs para manter histórico e auditoria. Isso é coerente com o domínio esportivo, porque a pontuação influencia classificação, avanço de fase e resultado do torneio.


DELIMITER $$


CREATE TRIGGER trg_audit_update_selecao
AFTER UPDATE ON selecao
FOR EACH ROW
BEGIN
  IF OLD.pontuacao <> NEW.pontuacao THEN
    INSERT INTO logs_auditoria (
      tabela_nome,
      acao,
      chave_registro,
      coluna_alterada,
      valor_antigo,
      valor_novo,
      usuario
    )
    VALUES (
      'selecao',
      'UPDATE',
      CONCAT('inscricao=', OLD.inscricao),
      'pontuacao',
      OLD.pontuacao,
      NEW.pontuacao,
      COALESCE(USER(), 'sistema')
    );
  END IF;
END$$


DELIMITER ;




-- TABELA DE FALTAS/CARTÕES
-- Justificativa semântica:
-- No ambito esportivo, cartões vermelhos representam uma punição disciplinar. Registrar esse evento em uma tabela própria permite controlar suspensões e manter a regra do jogo de forma centralizada no banco.


CREATE TABLE IF NOT EXISTS cartoes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fkjogadorinscricao INT NOT NULL,
  fkjogoid INT NOT NULL,
  tipo_cartao ENUM('amarelo','vermelho') NOT NULL,
  minuto INT,
  data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (fkjogadorinscricao) REFERENCES jogador(inscricao) ON DELETE CASCADE,
  FOREIGN KEY (fkjogoid) REFERENCES jogo(id) ON DELETE CASCADE
);




-- TABELA DE SUSPENSÕES
-- Justificativa semântica:
-- Quando um jogador recebe cartão vermelho, ele precisa ficar suspenso por uma partida. Esta tabela guarda a penalidade para controle disciplinar do torneio.


CREATE TABLE IF NOT EXISTS suspensoes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fkjogadorinscricao INT NOT NULL,
  jogos_a_cumprir INT NOT NULL DEFAULT 0,
  motivo VARCHAR(200),
  data_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_suspensao_jogador (fkjogadorinscricao),
  FOREIGN KEY (fkjogadorinscricao) REFERENCES jogador(inscricao) ON DELETE CASCADE
);




-- TRIGGER 2
-- Justificativa semântica:
-- Ao inserir um cartão vermelho, o banco atualiza a suspensão do jogador automaticamente. Isso evita inconsistência entre a punição registrada e a regra disciplinar aplicada.


DELIMITER $$


CREATE TRIGGER trg_cartao_vermelho_ins
AFTER INSERT ON cartoes
FOR EACH ROW
BEGIN
  IF NEW.tipo_cartao = 'vermelho' THEN
    INSERT INTO suspensoes (
      fkjogadorinscricao,
      jogos_a_cumprir,
      motivo
    )
    VALUES (
      NEW.fkjogadorinscricao,
      1,
      CONCAT('Cartão vermelho no jogo id=', NEW.fkjogoid)
    )
    ON DUPLICATE KEY UPDATE
      jogos_a_cumprir = jogos_a_cumprir + 1,
      motivo = VALUES(motivo),
      data_registro = CURRENT_TIMESTAMP;
  END IF;
END$$


DELIMITER ;


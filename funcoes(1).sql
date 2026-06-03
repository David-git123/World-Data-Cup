-- Função com estrutura condicional que classifica o porte do estádio baseado na sua capacidade máxima

DELIMITER $$

CREATE FUNCTION fn_categoria_estadio (id_estadio INT)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE cap INT;
    DECLARE categoria VARCHAR(20);
    
    SELECT capacidade_maxima INTO cap 
    FROM estadio 
    WHERE id = id_estadio;
    
    IF cap >= 80000 THEN 
        SET categoria = 'Elite';
    ELSEIF cap >= 60000 THEN 
        SET categoria = 'Grande';
    ELSEIF cap >= 45000 THEN 
        SET categoria = 'Médio';
    ELSE 
        SET categoria = 'Pequeno';
    END IF;
    
    RETURN categoria;
END $$

DELIMITER ;


-- Função que categoriza as seleções de acordo com a idade média

DELIMITER $$

CREATE FUNCTION categorizar_maturidade_selecao(id_selecao INT)
RETURNS VARCHAR(30)
DETERMINISTIC
BEGIN
    DECLARE idade_media INT;
    DECLARE categoria VARCHAR(30);
    
    SELECT media_idade INTO idade_media 
    FROM selecao 
    WHERE inscricao = id_selecao;
    
    CASE 
        WHEN idade_media IS NULL THEN 
            SET categoria = 'Não informada';
        WHEN idade_media >= 28 THEN 
            SET categoria = 'Elenco Experiente';
        WHEN idade_media >= 24 THEN 
            SET categoria = 'Elenco Equilibrado';
        ELSE 
            SET categoria = 'Elenco Jovem';
    END CASE; 
    
    RETURN categoria;
END $$

DELIMITER ;

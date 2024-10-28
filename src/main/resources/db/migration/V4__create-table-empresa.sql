CREATE TABLE Empresa(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) UNIQUE,
    uf VARCHAR (2) NOT NULL,
    reg_tributacao VARCHAR(100) NOT NULL,
    qualificacao VARCHAR(100),
    inscricao_estadual VARCHAR(9) NOT NULL,
    telefone VARCHAR(11),
    email VARCHAR(100) NOT NULL UNIQUE,
    status TINYINT NOT NULL,
    data_ultima_atualizacao VARCHAR(30) NOT NULL,
    cliente_id BIGINT,
    CONSTRAINT fk_cliente_empresa FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE SET NULL
);
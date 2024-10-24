CREATE TABLE Funcionario(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(11),
    cargo VARCHAR(30) NOT NULL,
    situacao VARCHAR(7) NOT NULL,
    data_entrada DATE NOT NULL,
    data_saida DATE
);
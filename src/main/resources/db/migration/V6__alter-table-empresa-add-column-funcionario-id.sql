ALTER TABLE Empresa ADD COLUMN funcionario_id BIGINT NOT NULL;
ALTER TABLE Empresa ADD CONSTRAINT fk_funcionario_empresa FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id);

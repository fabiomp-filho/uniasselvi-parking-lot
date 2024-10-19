CREATE TABLE Vaga
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    numero  INT                   NOT NULL,
    ocupada TINYINT(1) DEFAULT 0      NOT NULL,
    CONSTRAINT pk_vaga PRIMARY KEY (id)
);

CREATE TABLE Carro
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    placa   VARCHAR(255)          NULL,
    modelo  VARCHAR(255)          NULL,
    cor     VARCHAR(255)          NULL,
    vaga_id BIGINT                NULL,
    CONSTRAINT pk_carro PRIMARY KEY (id)
);

ALTER TABLE Carro
    ADD CONSTRAINT uc_carro_vaga UNIQUE (vaga_id);

ALTER TABLE Carro
    ADD CONSTRAINT FK_CARRO_ON_VAGA FOREIGN KEY (vaga_id) REFERENCES Vaga (id);


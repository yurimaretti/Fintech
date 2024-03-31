-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2023-05-25 19:25:53 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE t_categoria_transacao (
    cd_categoria NUMBER(2) NOT NULL,
    nm_categoria VARCHAR2(30) NOT NULL
);

ALTER TABLE t_categoria_transacao ADD CONSTRAINT t_categoria_transacao_pk PRIMARY KEY ( cd_categoria );

CREATE TABLE t_conta (
    cd_conta NUMBER(4) NOT NULL,
    nm_conta VARCHAR2(50) NOT NULL,
    sd_conta NUMBER(12, 2) NOT NULL,
    ds_conta VARCHAR2(200),
    ds_email VARCHAR2(64) NOT NULL,
    ds_cor   VARCHAR2(30) NOT NULL
);

ALTER TABLE t_conta ADD CONSTRAINT t_conta_pk PRIMARY KEY ( cd_conta );

CREATE TABLE t_cor (
    ds_cor VARCHAR2(30) NOT NULL
);

ALTER TABLE t_cor ADD CONSTRAINT t_cor_pk PRIMARY KEY ( ds_cor );

CREATE TABLE t_objetivos (
    cd_objetivo NUMBER(2) NOT NULL,
    ds_objetivo VARCHAR2(200) NOT NULL,
    ds_email    VARCHAR2(64) NOT NULL
);

ALTER TABLE t_objetivos ADD CONSTRAINT t_objetivos_pk PRIMARY KEY ( cd_objetivo );

CREATE TABLE t_tipo_transacao (
    tp_transacao VARCHAR2(14) NOT NULL
);

ALTER TABLE t_tipo_transacao ADD CONSTRAINT t_tipo_transacao_pk PRIMARY KEY ( tp_transacao );

CREATE TABLE t_transacoes (
    cd_transacao     NUMBER(6) NOT NULL,
    vl_transacao     NUMBER(12, 2) NOT NULL,
    dt_transacao     DATE NOT NULL,
    ds_observacoes   VARCHAR2(200),
    cd_categoria     NUMBER(2) NOT NULL,
    tp_transacao     VARCHAR2(14) NOT NULL,
    cd_conta_origem  NUMBER(4) NOT NULL,
    cd_conta_destino NUMBER(4)
);

ALTER TABLE t_transacoes ADD CONSTRAINT t_transacoes_pk PRIMARY KEY ( cd_transacao );

CREATE TABLE t_usuario (
    ds_email      VARCHAR2(64) NOT NULL,
    nm_usuario    VARCHAR2(100) NOT NULL,
    ds_senha      VARCHAR2(7) NOT NULL,
    dt_nascimento DATE NOT NULL
);

ALTER TABLE t_usuario ADD CONSTRAINT t_usuario_pk PRIMARY KEY ( ds_email );

ALTER TABLE t_conta
    ADD CONSTRAINT t_conta_t_cor_fk FOREIGN KEY ( ds_cor )
        REFERENCES t_cor ( ds_cor );

ALTER TABLE t_conta
    ADD CONSTRAINT t_conta_t_usuario_fk FOREIGN KEY ( ds_email )
        REFERENCES t_usuario ( ds_email );

ALTER TABLE t_objetivos
    ADD CONSTRAINT t_objetivos_t_usuario_fk FOREIGN KEY ( ds_email )
        REFERENCES t_usuario ( ds_email );

ALTER TABLE t_transacoes
    ADD CONSTRAINT t_trans_t_categoria_trans_fk FOREIGN KEY ( cd_categoria )
        REFERENCES t_categoria_transacao ( cd_categoria );

ALTER TABLE t_transacoes
    ADD CONSTRAINT t_trans_t_tipo_trans_fk FOREIGN KEY ( tp_transacao )
        REFERENCES t_tipo_transacao ( tp_transacao );

ALTER TABLE t_transacoes
    ADD CONSTRAINT t_transacoes_t_conta_fk FOREIGN KEY ( cd_conta_origem )
        REFERENCES t_conta ( cd_conta );

ALTER TABLE t_transacoes
    ADD CONSTRAINT t_transacoes_t_conta_fkv2 FOREIGN KEY ( cd_conta_destino )
        REFERENCES t_conta ( cd_conta );



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             0
-- ALTER TABLE                             14
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

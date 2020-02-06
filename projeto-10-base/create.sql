

CREATE DATABASE despesas;




CREATE TABLE public.conta (
    id integer NOT NULL,
    descricao character varying(255) NOT NULL,
    valor numeric(11,2),
    pago boolean,
    id_tipo integer,
    id_referencia integer,
    version integer,
    padrao boolean
);


ALTER TABLE public.conta OWNER TO postgres;


CREATE SEQUENCE public.conta_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_id_seq OWNER TO postgres;


ALTER SEQUENCE public.conta_id_seq OWNED BY public.conta.id;


CREATE TABLE public.conta_padrao (
    id integer NOT NULL,
    id_conta integer
);


ALTER TABLE public.conta_padrao OWNER TO postgres;


CREATE SEQUENCE public.conta_padrao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_padrao_id_seq OWNER TO postgres;


ALTER SEQUENCE public.conta_padrao_id_seq OWNED BY public.conta_padrao.id;


CREATE TABLE public.grupo (
    id integer NOT NULL,
    version integer,
    nome character varying(255),
    ativo boolean
);


ALTER TABLE public.grupo OWNER TO postgres;


CREATE SEQUENCE public.grupo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grupo_id_seq OWNER TO postgres;
ALTER SEQUENCE public.grupo_id_seq OWNED BY public.grupo.id;



CREATE TABLE public.grupos_usuarios (
    id integer NOT NULL,
    id_grupo integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.grupos_usuarios OWNER TO postgres;


CREATE SEQUENCE public.grupos_usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grupos_usuarios_id_seq OWNER TO postgres;


ALTER SEQUENCE public.grupos_usuarios_id_seq OWNED BY public.grupos_usuarios.id;


CREATE TABLE public.referencia (
    id integer NOT NULL,
    descricao character varying(255) NOT NULL,
    version integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.referencia OWNER TO postgres;


CREATE SEQUENCE public.referencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.referencia_id_seq OWNER TO postgres;

ALTER SEQUENCE public.referencia_id_seq OWNED BY public.referencia.id;


CREATE TABLE public.rendimento (
    id integer NOT NULL,
    descricao character varying(255) NOT NULL,
    valor numeric(11,2),
    id_referencia integer,
    version integer
);


ALTER TABLE public.rendimento OWNER TO postgres;

CREATE SEQUENCE public.rendimento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rendimento_id_seq OWNER TO postgres;


ALTER SEQUENCE public.rendimento_id_seq OWNED BY public.rendimento.id;

CREATE TABLE public.tarefa (
    id_tarefa integer NOT NULL,
    descricao character varying(255),
    version integer
);


ALTER TABLE public.tarefa OWNER TO postgres;


CREATE SEQUENCE public.tarefa_id_tarefa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tarefa_id_tarefa_seq OWNER TO postgres;


ALTER SEQUENCE public.tarefa_id_tarefa_seq OWNED BY public.tarefa.id_tarefa;


CREATE TABLE public.tipo (
    id integer NOT NULL,
    descricao character varying(255) NOT NULL,
    version integer
);


ALTER TABLE public.tipo OWNER TO postgres;


CREATE SEQUENCE public.tipo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_id_seq OWNER TO postgres;

ALTER SEQUENCE public.tipo_id_seq OWNED BY public.tipo.id;


CREATE TABLE public.usuario (
    nome character varying(255),
    senha character varying(255),
    login character varying(255),
    perfil integer,
    id integer NOT NULL,
    version integer,
    senha_padrao boolean DEFAULT true NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;


CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


ALTER TABLE ONLY public.conta ALTER COLUMN id SET DEFAULT nextval('public.conta_id_seq'::regclass);


ALTER TABLE ONLY public.conta_padrao ALTER COLUMN id SET DEFAULT nextval('public.conta_padrao_id_seq'::regclass);


ALTER TABLE ONLY public.grupo ALTER COLUMN id SET DEFAULT nextval('public.grupo_id_seq'::regclass);


ALTER TABLE ONLY public.grupos_usuarios ALTER COLUMN id SET DEFAULT nextval('public.grupos_usuarios_id_seq'::regclass);

ALTER TABLE ONLY public.referencia ALTER COLUMN id SET DEFAULT nextval('public.referencia_id_seq'::regclass);

ALTER TABLE ONLY public.rendimento ALTER COLUMN id SET DEFAULT nextval('public.rendimento_id_seq'::regclass);

ALTER TABLE ONLY public.tarefa ALTER COLUMN id_tarefa SET DEFAULT nextval('public.tarefa_id_tarefa_seq'::regclass);

ALTER TABLE ONLY public.tipo ALTER COLUMN id SET DEFAULT nextval('public.tipo_id_seq'::regclass);

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


INSERT INTO public.conta (id, descricao, valor, pago, id_tipo, id_referencia, version, padrao) VALUES (1, 'Condomínio', 450.00, false, NULL, NULL, 0, NULL);
INSERT INTO public.conta (id, descricao, valor, pago, id_tipo, id_referencia, version, padrao) VALUES (8, 'Condomínio', 450.00, false, 1, 7, 0, NULL);
INSERT INTO public.conta (id, descricao, valor, pago, id_tipo, id_referencia, version, padrao) VALUES (9, 'Luz', 450.00, false, 1, 7, 0, NULL);
INSERT INTO public.conta (id, descricao, valor, pago, id_tipo, id_referencia, version, padrao) VALUES (10, 'Prestação', 450.00, false, 1, 7, 0, NULL);
INSERT INTO public.conta (id, descricao, valor, pago, id_tipo, id_referencia, version, padrao) VALUES (11, 'Condomínio', 450.00, true, 2, 8, 10, true);


INSERT INTO public.conta_padrao (id, id_conta) VALUES (4, 11);

INSERT INTO public.grupo (id, version, nome, ativo) VALUES (6, 29, 'Grupo Atendimento', true);
INSERT INTO public.grupo (id, version, nome, ativo) VALUES (5, 21, 'Grupo Financeiro', true);
INSERT INTO public.grupo (id, version, nome, ativo) VALUES (4, 36, 'Grupo Adm', true);



INSERT INTO public.grupos_usuarios (id, id_grupo, id_usuario) VALUES (51, 4, 2);
INSERT INTO public.grupos_usuarios (id, id_grupo, id_usuario) VALUES (57, 4, 12);
INSERT INTO public.grupos_usuarios (id, id_grupo, id_usuario) VALUES (58, 5, 12);
INSERT INTO public.grupos_usuarios (id, id_grupo, id_usuario) VALUES (59, 6, 12);


INSERT INTO public.referencia (id, descricao, version) VALUES (8, 'Fevereiro 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (9, 'Março 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (10, 'Abril 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (11, 'Maio 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (12, 'Junho 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (13, 'Julho 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (14, 'Agosto 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (15, 'Setembro 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (16, 'Outubro 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (17, 'Novembro 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (18, 'Dezembro 2019', 0);
INSERT INTO public.referencia (id, descricao, version) VALUES (21, 'Janeiro-3', 1);
INSERT INTO public.referencia (id, descricao, version) VALUES (7, 'Janeiro 2019', 14);
INSERT INTO public.referencia (id, descricao, version) VALUES (24, 'Janeiro 2020', 0);



INSERT INTO public.tarefa (id_tarefa, descricao, version) VALUES (1, 'Tarefa 1', 1);
INSERT INTO public.tarefa (id_tarefa, descricao, version) VALUES (2, 'Tarefa 2', 1);
INSERT INTO public.tarefa (id_tarefa, descricao, version) VALUES (3, NULL, 0);
INSERT INTO public.tarefa (id_tarefa, descricao, version) VALUES (4, 'nova tarefa 59', 0);



INSERT INTO public.tipo (id, descricao, version) VALUES (2, 'Casa', 1);
INSERT INTO public.tipo (id, descricao, version) VALUES (3, 'Casa4', 1);
INSERT INTO public.tipo (id, descricao, version) VALUES (4, 'Cartão', 1);
INSERT INTO public.tipo (id, descricao, version) VALUES (1, 'Casas', 3);
INSERT INTO public.tipo (id, descricao, version) VALUES (5, 'CArteira', 0);
INSERT INTO public.tipo (id, descricao, version) VALUES (10, 'CArteira 2', 0);


INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('Mariana ', '$2a$12$ZuHnGlBCBG5bRLHSyvTW6.Qyrs4ex774jrPDpFZJ37NLV.UUXwbtq', 'mariana', 2, 8, 14, true);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('Maria', '$2a$12$KSo7Y8XUcJGHXGaRsjJ7n.bTors65/YlwC7fz277V6B3ofbqFJBom', 'maria', 2, 6, 14, true);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('Sistema', '$2a$12$m.BSuCEq5BSMMPSPMaKTUuOWiKilssGwMayvjSCZO/0wQhnn6ty3a', 'admin', 1, 2, 15, false);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('Alessandro Silva', '$2a$12$ehS5MKZvFwE.vtzdBwt86OPoiCoKOflTR5sWfRDAl91lcjXeJEEti', 'sandro02', 2, 4, 62, false);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('clinica', '$2a$12$p7L.5u6pn/1PbKH5AWECluEs5Apn1hHTxqC9Ar51Naa0yCBVtOqwS', 'clinica', 2, 3, 25, false);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('Julia', '$2a$12$IAKqGU2V44Dc2GNjOnxfwuSt5TjjNPDAtzpiSG.Cjp5mU/y0VWTPW', 'julia', 2, 7, 25, false);
INSERT INTO public.usuario (nome, senha, login, perfil, id, version, senha_padrao) VALUES ('vinicius', '$2a$12$9NgMzoCKndTYEOgUmBCMOOwgYd0hGiJKvCepLWYdlo8z3HK6LCIHq', 'vinicius', 1, 12, 6, false);


SELECT pg_catalog.setval('public.conta_id_seq', 11, true);
SELECT pg_catalog.setval('public.conta_padrao_id_seq', 4, true);
SELECT pg_catalog.setval('public.grupo_id_seq', 6, true);
SELECT pg_catalog.setval('public.grupos_usuarios_id_seq', 61, true);
SELECT pg_catalog.setval('public.referencia_id_seq', 24, true);
SELECT pg_catalog.setval('public.rendimento_id_seq', 1, false);
SELECT pg_catalog.setval('public.tarefa_id_tarefa_seq', 4, true);
SELECT pg_catalog.setval('public.tipo_id_seq', 10, true);
SELECT pg_catalog.setval('public.usuario_id_seq', 12, true);
ALTER TABLE ONLY public.conta_padrao
    ADD CONSTRAINT conta_padrao_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.grupos_usuarios
    ADD CONSTRAINT grupos_usuarios_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT pk_tarefa PRIMARY KEY (id_tarefa);

ALTER TABLE ONLY public.referencia
    ADD CONSTRAINT referencia_descricao_u UNIQUE (descricao);


ALTER TABLE ONLY public.referencia
    ADD CONSTRAINT referencia_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.rendimento
    ADD CONSTRAINT rendimento_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tipo
    ADD CONSTRAINT tipo_descricao_u UNIQUE (descricao);

ALTER TABLE ONLY public.tipo
    ADD CONSTRAINT tipo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_fk_referencia FOREIGN KEY (id_referencia) REFERENCES public.referencia(id);

ALTER TABLE ONLY public.rendimento
    ADD CONSTRAINT conta_fk_referencia FOREIGN KEY (id_referencia) REFERENCES public.referencia(id);

ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_fk_tipo FOREIGN KEY (id_tipo) REFERENCES public.tipo(id);


ALTER TABLE ONLY public.conta_padrao
    ADD CONSTRAINT conta_padrao_fk_conta FOREIGN KEY (id_conta) REFERENCES public.conta(id);

ALTER TABLE ONLY public.grupos_usuarios
    ADD CONSTRAINT fk_grupos_grupo FOREIGN KEY (id_grupo) REFERENCES public.grupo(id);


ALTER TABLE ONLY public.grupos_usuarios
    ADD CONSTRAINT fk_grupos_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);

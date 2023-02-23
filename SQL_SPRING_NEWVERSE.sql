drop database newverse;
create database newverse;
use newverse;

CREATE TABLE Cliente(
	id_cliente INT AUTO_INCREMENT,
    nome varchar(50),
    CPF varchar(15) NOT NULL UNIQUE,
	telefone varchar(15),
    dt_nasc date,
    email varchar(110) NOT NULL UNIQUE,
    nome_usuario varchar(14) NOT NULL,
	senha varchar(16) NOT NULL,
    PRIMARY KEY(id_cliente)
);

CREATE TABLE endereco_cliente (
	titulo_endereco varchar(20) NOT NULL,
	rua_numero varchar(50) NOT NULL,
	bairro varchar(30) NOT NULL,
	cidade varchar(30) NOT NULL,
	CEP INT(8) NOT NULL,
	fk_pk_id_cliente INT NOT NULL,
    FOREIGN KEY (fk_pk_id_cliente) REFERENCES Cliente(id_cliente),
	PRIMARY KEY (titulo_endereco,  fk_pk_id_cliente)
);

CREATE TABLE cartoes_cliente (
	id_cartao INT auto_increment,
	nome_cartao varchar(30) NOT NULL,
	numero_cartao BIGINT NOT NULL,
	bandeira varchar(20) NOT NULL,
	cvv INT(3) NOT NULL,
	fk_pk_id_cliente INT NOT NULL,
	PRIMARY KEY (id_cartao, fk_pk_id_cliente)
);


CREATE TABLE NFTS_autorais (
	id_nft_autoral INT AUTO_INCREMENT,
    num_operacao BIGINT NOT NULL UNIQUE,
    nome varchar(30) NOT NULL,
    tipo_arquivo varchar(5) NOT NULL,
    preco double NOT NULL,
    moeda varchar(3) NOT NULL,
    PRIMARY KEY(id_nft_autoral)    
);

CREATE TABLE plataformas_nft(
	id_carteira INT AUTO_INCREMENT,
    nome_plataforma varchar(20) NOT NULL,
    PRIMARY KEY(id_carteira)
);

CREATE TABLE plataformas_nfts_autorais(
	fk_pk_id_carteira INT NOT NULL AUTO_INCREMENT,
    fk_pk_id_nft_autoral INT NOT NULL,
    PRIMARY KEY(fk_pk_id_carteira, fk_pk_id_nft_autoral),
    FOREIGN KEY(fk_pk_id_carteira) REFERENCES plataformas_nft(id_carteira),
    FOREIGN KEY(fk_pk_id_nft_autoral) REFERENCES plataformas_nft(id_nft_autoral)
);

CREATE TABLE plataformas_nfts_n_autorais(
	fk_pk_id_carteira INT NOT NULL AUTO_INCREMENT,
    fk_pk_id_nft_n_autoral INT NOT NULL,
    PRIMARY KEY(fk_pk_id_carteira, fk_pk_id_nft_n_autoral),
    FOREIGN KEY(fk_pk_id_carteira) REFERENCES plataformas_nft(id_carteira)
);

CREATE TABLE Software(
	id_usuario INT AUTO_INCREMENT,
    login VARCHAR(25) NOT NULL,
    fk_id_carteira INT NOT NULL,
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(fk_id_carteira) REFERENCES plataformas_nft(id_carteira)
);

CREATE TABLE Produto(
	id_produto INT AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    categoria varchar(20) NOT NULL,
    modelo varchar(20) NOT NULL,
    valor float NOT NULL,
    descricao TEXT NOT NULL,
	fk_id_usuario INT NOT NULL,
    PRIMARY KEY(id_produto),
    FOREIGN KEY(fk_id_usuario) REFERENCES Software(id_usuario)
);

CREATE TABLE Pedido(
	id_pedido INT AUTO_INCREMENT,
    data_compra DATE NOT NULL,
    prev_entrega DATE not null,
    status_pedido text,
    forma_pag VARCHAR(15) NOT NULL,
    qtd_pedida INT NOT NULL,
    cod_desconto varchar(10),
    valor_frete double,
    fk_id_produto INT NOT NULL,
    fk_id_cliente INT NOT NULL,
    fk_titulo_endereco varchar(20) NOT NULL,
    PRIMARY KEY(id_pedido),
    FOREIGN KEY(fk_id_produto) REFERENCES Produto(id_produto),
    FOREIGN KEY(fk_id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY(fk_titulo_endereco) REFERENCES endereco_cliente(titulo_endereco)    
);

CREATE TABLE info_vendas(
id_info INT auto_increment,
nota_fiscal varchar(15) NOT NULL,
fk_id_pedido INT NOT NULL,
PRIMARY KEY(id_info),
FOREIGN KEY(fk_id_pedido) REFERENCES Pedido(id_pedido)

);
CREATE TABLE CURSO(
	id_curso INT AUTO_INCREMENT,
    nome varchar(30) NOT NULL,
    descricao text NOT NULL,
    carga INT(5) NOT NULL,
    ano YEAR NOT NULL,
    valor double,
    total_aulas INT NOT NULL,
    PRIMARY KEY(id_curso)
);

CREATE TABLE Aluno(
	id_aluno INT AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    CPF varchar(15) NOT NULL UNIQUE,
    dt_nasc date NOT NULL,
    sexo enum('M', 'F', 'N/A'),
    profissao varchar(20),
    email varchar(110) NOT NULL,
    nome_usuario varchar(14) NOT NULL,
	senha varchar(16) NOT NULL,
    fk_id_curso INT NOT NULL,
    PRIMARY KEY(id_aluno),
    FOREIGN KEY(fk_id_curso) REFERENCES Curso(id_curso)
);

select * from cliente;	

DELETE FROM Cliente
WHERE cliente.id_cliente = 1;


Select nome_usuario, email, senha
from Cliente 
where senha='Victor1109*' and (email = 'victorh' or nome_usuario = 'victorh')





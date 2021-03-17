

<h1 align="center">
       💻 Dryve-system 
</h1>

<h3 align="center">
     Sistema para cadastro de veículos, tendo como base o ambiente dryve. 
</h3>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/Daiven75/dryve-system?color=%2304D361">
  
  <a href="https://github.com/Daiven75/dryve-system/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Daiven75/dryve-system">
  </a>
 
</p>

<h4 align="center">
	🚧  EM ANDAMENTO 🚀 🚧
</h4>

Tabela de conteúdos
=================
<!--ts-->
   * [Sobre o projeto](#-sobre-o-projeto)
   * [Funcionalidades](#-funcionalidades)
   * [Como executar o projeto](#-como-executar-o-projeto)
     * [Rodando o Backend (servidor)](#user-content--rodando-o-backend-servidor)
   * [Tecnologias](#-tecnologias)
   * [Como contribuir no projeto](#-como-contribuir-no-projeto)
   * [Autor](#-autor)
   * [Licença](#user-content--licença)
<!--te-->


## 💻 Sobre o projeto

Dryve-system - É sistema básico de cadastro de veículos mapeado pelo seu modelo e marca no intuito da divulgação do preço do anúncio, e podendo fazer consultas de forma
paginada como também filtradas a partir de dados enviados.

---

## ⚙️ Funcionalidades

- [x] Cadastro de novos veículos enviando:
  - [x] Placa
  - [x] Id da marca
  - [x] Id do modelo
  - [x] Preço do anúncio
  - [x] Ano

- [x] Busca de veículos de forma paginada podendo ser filtrada através da placa enviando:
  - [x] Placa (Opcional)
  - [x] Página (Opcional)
  - [x] Linhas por página (Opcional)
  - [x] Ordem (Opcional)
  - [x] Direção (Opcional)
  
- [x] Busca de veículos por id enviando:
  - [x] Id do veículo

---

## 🚀 Como executar o projeto

#### 🎲 Rodando o Backend (servidor)

```bash

# Clone este repositório
$ git clone git@github.com:Daiven75/dryve-system.git

# Acesse a pasta do projeto no terminal/cmd
$ cd dryve-system

# Abra a pasta do repositório em sua IDE favorita

# Execute o comando no terminal da pasta do repositório para instalação das tecnologias
$ mvn install

# Dê um start na aplicação para subir projeto

# O servidor inciará na porta:8080 - acesse http:localhost:8080

```
<p align="center">
  <a href="https://github.com/tgmarinho/README-ecoleta/blob/master/Insomnia_API_Ecoletajson.json" target="_blank"><img src="https://insomnia.rest/images/run.svg" alt="Run in Insomnia"></a>
</p>

---

## 🛠 Tecnologias

As seguintes tecnologias foram usadas na construção do projeto:

-   **[Spring](https://https://spring.io/)**
-   **[Java](https://www.java.com/pt-BR/)**
-   **[RabbitMQ](https://www.rabbitmq.com/)**
-   **[MariaDB](https://mariadb.org/)**
-   **[Flyway](https://flywaydb.org/)**
-   **[Junit 5](https://junit.org/junit5/)**
-   **[Lombok](https://projectlombok.org/)**


> Veja o arquivo  [pom.xml](https://github.com/Daiven75/dryve-system/blob/master/pom.xml)

#### [](https://github.com/tgmarinho/Ecoleta#utilit%C3%A1rios)**Utilitários**

-   Teste de API:  **[Insomnia](https://insomnia.rest/)**

---

## 💪 Como contribuir no projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feature: My new feature"`
4. Envie as suas alterações: `git push origin my-feature`
> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](./CONTRIBUTING.md)

---

## 🦸 Autor

 <br />
 <a href="https://github.com/Daiven75">Lucas Silva</a>
 <br />
 <br />
 
[![Gmail Badge](https://img.shields.io/badge/-75.lucas.slima@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:75.lucas.slima@gmail.com)](mailto:75.lucas.slima@gmail.com)

---

## 📝 Licença

Este projeto esta sobe a licença [MIT](./LICENSE).

Feito por Lucas Silva 👋🏽 [Entre em contato!](https://www.linkedin.com/in/lucas-silva-959102169)

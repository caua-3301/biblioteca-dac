# Biblioteca

📚 Biblioteca API – Documentação do Projeto
📝 Sobre o Projeto

A Biblioteca API é um sistema backend desenvolvido em Java 17 + Spring Boot, utilizando uma arquitetura inspirada em DDD (Domain-Driven Design) e Clean Architecture.
O objetivo do sistema é realizar o gerenciamento de livros, incluindo operações de:

Cadastro
Consulta
Atualização
Remoção

Além disso, o projeto implementa uma validação de ISBN utilizando regex e fornece vários exemplos de ISBN válidos para auxiliar o usuário.

🧱 Arquitetura

O projeto segue uma divisão clara em camadas:

┌────────────────────────────────────┐
│              API                   │
│ Controllers (exposição REST)       │
└────────────────────────────────────┘
┌────────────────────────────────────┐
│            APPLICATION              │
│ Services (coordenação de casos)    │
└────────────────────────────────────┘
┌────────────────────────────────────┐
│              DOMAIN                │
│ Entidades, Regras de negócio       │
│ Interfaces de Repositórios         │
└────────────────────────────────────┘
┌────────────────────────────────────┐
│           INFRASTRUCTURE           │
│ Repositórios JPA                   │
│ Configurações e persistência       │
└────────────────────────────────────┘

Fluxo

A API recebe requisições → Application coordena → Domain aplica regras → Infra realiza a persistência.

🛢️ Banco de Dados – H2
🔗 Acesso ao console H2

Abra no navegador:
http://localhost:8080/h2

📘 Endpoints da API
Base URL:
http://localhost:8080/book

➕ 1. Criar Livro
POST /book/save

Request Body
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "published": "2008-08-01",
  "inStock": 5,
  "isbn": "978-0-13-235088-4"
}

📄 2. Buscar por ID

GET /book/find/id/{id}
Exemplo:
GET /book/find/id/1

📚 3. Buscar Todos
GET /book/find/all

✏️ 4. Atualizar Livro
PUT /book/update/{id}

{
  "title": "Clean Code (Revisado)",
  "author": "Robert C. Martin",
  "published": "2008-08-01",
  "inStock": 10
}

🗑️ 5. Remover Livro
DELETE /book/delete/{id}

🔍 Validação de ISBN
A API valida ISBN no formato:

ISBN-10
ISBN-13

✔ Regex utilizada
^(97(8|9))?\d{9}(\d|X)$

🧪 Exemplos de ISBN Válidos
✔ ISBN-13

978-0-13-235088-4
978-8535217190
978-8550804019
978-8543004792
978-6555642940

✔ ISBN-10

0132350882
8535217190
8543004792

🧪 Testes

O projeto utiliza:
JUnit 5
Spring Boot Starter Test
MockMvc para testes de API

🚀 Como Rodar o Projeto
1. Clonar o repositório
git clone https://github.com/seu-user/biblioteca-api.git
cd biblioteca-api

2. Rodar via Maven
mvn spring-boot:run

3. Acessar aplicação
API: http://localhost:8080
H2 Console: http://localhost:8080/h2

📄 Licença
Projeto de estudo – uso livre.

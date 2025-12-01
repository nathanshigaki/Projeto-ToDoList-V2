# ✅ Projeto ToDo API - Java + Spring Boot + Railway

Este é um projeto de **API RESTful** desenvolvida com **Spring Boot**, que permite a criação e gerenciamento de usuários e tarefas (*todos*). A documentação da API é feita com **Swagger**, e o deploy está hospedado na plataforma **Render**.

---

## 🔗 Acesso à API

- **Swagger UI (documentação interativa):**  
  👉 [.https://projeto-todolist-v2.onrender.com/swagger-ui/index.html#/](https://projeto-todolist-v2.onrender.com/swagger-ui/index.html#/)]
---

## 📌 Tecnologias Utilizadas

- ✅ Java 17+
- ✅ Spring Boot 3+
- ✅ Spring Data JPA
- ✅ Spring Web
- ✅ H2 / PostgreSQL
- ✅ Swagger OpenAPI (Springdoc)
- ✅ Render (Deploy)

---

## 🔧 Funcionalidades

### 👤 Usuário
- Criar usuário (`POST /users`)
- Listar usuários (`GET /users`)
- Buscar por ID (`GET /users/{id}`)
- Atualizar usuário (`PUT /users/{id}`)
- Deletar usuário (`DELETE /users/{id}`)

### ✅ Tarefas (Todos)
- Criar tarefa para um usuário (`POST /users/{userId}/todos`)
- Listar todas as tarefas de um usuário (`GET /users/{userId}/todos`)
- Atualizar tarefa (`PUT /users/{userId}/todos/{todoId}`)
- Deletar tarefa (`DELETE /users/{userId}/todos/{todoId}`)

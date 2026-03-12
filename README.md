# 🚀 Java Backend : Estudando e apredendo 

Este repositório é meu laboratório principal de estudos e desenvolvimento backend, onde aplico conceitos avançados de engenharia de software utilizando o ecossistema Java. O foco é a criação de APIs robustas, escaláveis e seguindo as melhores práticas de mercado (Clean Code e SOLID).

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java (JDK 17+)
- **Framework Principal:** Spring Boot 3
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Bancos de Dados:** MySQL (Desenvolvimento)
- **Segurança e Validação:** Bean Validation (Hibernate Validator)
- **Arquitetura:** Padrão MVC (Model-View-Controller)
- **Padrões de Projeto:** DTO Pattern (Data Transfer Object)
- **Documentação:** Swagger UI (OpenAPI 3)
- **Gerenciador de Dependências:** Maven
- **Versionamento:** Git / GitHub

---

## 🏗️ Arquitetura e Padrões Implementados

O projeto foi estruturado para garantir a separação de responsabilidades e a facilidade de manutenção:

1.  **Controller Layer:** Exposição dos endpoints REST e manipulação de requisições HTTP.
2.  **Service Layer:** Onde reside toda a regra de negócio, garantindo que o Controller seja "magro".
3.  **Repository Layer:** Interface de comunicação direta com o banco de dados via JPA.
4.  **DTO Pattern:** Utilizado para transferir apenas os dados necessários entre as camadas, aumentando a segurança e performance da API.
5.  **Exception Handling:** Tratamento global de erros para retornar mensagens claras e padronizadas ao cliente.

---

## 📖 Documentação da API (Swagger)

A API conta com documentação interativa via **Swagger UI**. Com ele, você pode visualizar todos os endpoints disponíveis e testar as requisições em tempo real.

Para acessar, certifique-se de que a aplicação está rodando e acesse:
> `http://localhost:8080/swagger-ui/index.html`

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/brunoluancruzleal/SpringEstudantAndPratic.git](https://github.com/brunoluancruzleal/SpringEstudantAndPratic.git)

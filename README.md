# challenge-forum-hub

FórumHub é uma API REST desenvolvida para gerenciamento de tópicos e discussões em um fórum de cursos online.
O projeto oferece funcionalidades para autenticação, criação, leitura, atualização e exclusão de tópicos.

Tecnologias Utilizadas
O projeto foi construído com as seguintes tecnologias:

Java 21: Linguagem de programação utilizada para desenvolvimento do backend.
Spring Boot: Framework para simplificar a criação de aplicações Java, com módulos de segurança, persistência de dados e API REST.
Maven: Gerenciador de dependências e automação do projeto.
Spring Security: Para autenticação e autorização com tokens JWT.
JWT (JSON Web Token): Gerenciamento de tokens para autenticação segura dos usuários.
MySQL: Banco de dados utilizado para armazenar informações de usuários, tópicos e cursos.
Flyway: Ferramenta para versionamento e migração do banco de dados.
Lombok: Simplifica a criação de código boilerplate, como getters, setters e construtores.
Postman: Ferramenta utilizada para testar os endpoints da API.


Funcionalidades
Autenticação
Login: Autenticação via /login, gerando um token JWT para acesso aos endpoints protegidos.
Autorização: Usuários autenticados podem criar, atualizar e excluir tópicos.
Gerenciamento de Tópicos
Listagem de Tópicos: GET /topicos lista todos os tópicos com paginação e ordenação, com filtros opcionais por curso e ano.
Detalhamento de Tópico: GET /topicos/{id} exibe informações detalhadas de um tópico específico.
Criação de Tópico: POST /topicos permite a criação de um novo tópico. Campos obrigatórios: título, mensagem e nome do curso.
Atualização de Tópico: PUT /topicos/{id} permite atualizar o título, mensagem e status de um tópico específico.
Exclusão de Tópico: DELETE /topicos/{id} permite a exclusão de um tópico específico.
Gerenciamento de Cursos e Usuários
Cadastro de Curso: POST /cursos permite cadastrar cursos (em desenvolvimento).
Cadastro de Usuário: POST /usuarios permite o cadastro de novos usuários (em desenvolvimento).
Segurança e Validação
JWT para Autenticação: Implementação de autenticação baseada em tokens JWT.
Validação: Utilização de anotações de validação para garantir a integridade dos dados recebidos na API.
Flyway para Migração do Banco de Dados: Garante versionamento e migração das tabelas no banco de dados MySQL.
You are going to deliver a Spring Boot 2.4.x project, which is going to have a complete REST web service CRUD in order to access a Client resource, covering the five basic operations learned in the first course session:

- Pageable resource search;

- Resource search by id;

- Insert a new resource;

- Update the resource;

- Delete a resource;


The project must have a testing environment configured, which is going to access a in-memory database (H2). The project should use Maven as a dependency manager and Java 11 as language.

Um cliente possui nome, CPF, renda, data de nascimento, e quantidade de filhos.

A client has name, CPF (social security number), birthd date and number of children, accordingly to the class diagram. You must follow the naming conventions specified there.

Client
- id: Long
- name: String
- cpf: String
- income: Double
- birthDate: Instant
- children: Integer

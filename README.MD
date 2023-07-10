# Autenticação Simples com Spring Security

Este projeto implementa uma autenticação simples utilizando o Spring Security. O objetivo é oferecer uma maneira segura de autenticar usuários no sistema.

## Principais Classes

### [LoginController]([ADD_LINK_HERE](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/controller/IndexController))

A classe `IndexController` é um exemplo de um redirecionamento para uma página inicial após o login bem sucedido (não é preciso especificar redirect pois isso já é feito de forma automática na SecurityConfigurations)

Nela é demonstrado também como user o SecurityContextHolder para obter o atual usuário logado.

### [SecurityConfigurations](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/config/SecurityConfigurations.java)

A classe `SecurityConfigurations` é responsável pelas principais configurações de segurança da aplicação. Ela define os endpoints que necessitam de autenticação, o encoder para as senhas, os formulários de login etc.

Importante ter as anotações @EnableWebSecurity e @Configuration

Essas anotações são usadas para ativar a segurança web no Spring Security e indicar que é uma classe de configuração e pode conter métodos @Bean ou configurações do Spring.

### [AuthenticationService](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/config/AuthenticationService.java)

A classe `AuthenticationService` é um serviço injetado na SecurityConfigurations que implementa a interface `UserDetailsService` do Spring Security. Ela é responsável por fazer a ligação entre o Spring Security e o banco de dados.


### [IndexController]([ADD_LINK_HERE](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/controller/IndexController))

A classe `IndexController` é um exemplo de um redirecionamento para uma página inicial após o login bem sucedido (não é preciso especificar redirect pois isso já é feito de forma automática na SecurityConfigurations)

Nela é demonstrado também como user o SecurityContextHolder para obter o atual usuário logado.

## Navegação
Foram criados 3 templates simples para [login]() / [cadastro]() / [index]()

## Cadastro de Usuários e Administradores
O cadastro de novos usuários pode ser feito de duas formas, pelo formulário ou por requisições REST. O cadastro de novos administradores só pode ser feito por requests e apenas por administradores autenticados.

### [UserEndpoint](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/endpoint/UserEndpoint.java)

A classe `UserEndpoint` é um exemplo de uma configuração para cadastro de novos usuários e administradores do sistema através de requisições em uma API REST.

### [CadastroController](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/endpoint/UserEndpoint.java)

A classe `CadastroController` adminsitra a view do formulário de cadastro de usuários e faz o cadastro. 

## Modelo

### [User](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/model/User.java)

A classe `User` é uma entidade que representa um usuário na aplicação e implementa a interface `UserDetails` e tem uma relação com a Class `Profile`. Ela contém detalhes como o email do usuário e a senha.

### [Profile](https://github.com/thomazcm/spring-authentication/blob/master/src/main/java/br/com/businesstec/springauthentication/model/Profile.java)

A classe `Profile` é uma entidade que representa o perfil de autenticação de um usuário na aplicação. Cada usuário pode ter múltiplos perfis, como "USER" ou "ADMIN". Ela implementa a interface do Spring Security `GrantedAuthority `.

## Principais Dependências

### Spring Security

Spring Security é um framework de autenticação e controle de acesso altamente personalizável. 

### Spring-JPA e PostgreSQL conector

### Thymeleaf
No projeto foi usado thymeleaf para gerar as páginas simples de login, e de acesso bem sucedido demonstrando a informação do id do usuário no banco de dados.


## Executando o Projeto

Para executar este projeto, ajuste as configurações do banco de dados no arquivo application.properties de acordo com sua instalação do PostGres

Clone o repositório e navegue até o diretório do projeto:

```bash
git clone [link-do-repositorio]
cd spring-authentication

```
Agora, você pode construir o projeto e executá-lo:

```bash
Copy code
mvn clean install
mvn spring-boot:run
```
A aplicação estará rodando no endereço http://localhost:8080/
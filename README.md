# Desafio Newsletter

## Descrição do Desafio
"
Você deve criar um sistema backend para cadastro e envio de e-mail de newsletter.
Este deve
permitir via API Web o cadastro de clientes* e notícias*.
Todos os dias às 08h deve consultar os registros e disparar e-mails com as notícias cadastradas e
não processadas ainda para os e-mails cadastrados.
Se uma data de nascimento foi cadastrada, deve enviar junto um “feliz aniversário" se a data
corrente for a mesma do nascimento.
Dados para Cadastro:
Cliente: nome, e-mail, nascimento (opcional).
Notícia: título, descrição, link
Formato E-mail:
 - Assunto: Notícias do dia!
Corpo:
 - Bom dia <nome do cliente> ?< Feliz aniversário >?
- Título clicável se fornecido link
 - Descrição
 - Título clicável se fornecido link
 - Descrição
 - Até a próxima.
"

## Tecnologias Usadas


 - Java 17: A versão do Java utilizada para o desenvolvimento da aplicação.
 - Gradle: Sistema de automação de build para gerenciamento das dependências e execução de tarefas.
 - Spring Boot: Framework que facilita o desenvolvimento de aplicativos Java baseados em microserviços.
 - Spring Mail: Módulo do Spring utilizado para envio de e-mails.
 - PostgreSQL: Banco de dados relacional utilizado para persistência de dados.
 - Lombok: Biblioteca que simplifica a escrita de código.
 - JUnit: Framework de testes utilizado para garantir a qualidade e funcionalidade do código.

## Swagger

 - Para consultar todos os endpoints criados, quando a aplicação estiver rodando,
acessar: http://localhost:8080/swagger-ui/index.html#/
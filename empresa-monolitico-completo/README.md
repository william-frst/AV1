# Sistema de Gerenciamento de Funcionários e Departamentos

## Estrutura
- Controllers (REST e MVC)
- DTO (Data Transfer Object - entrada/saída)
- Model (Entidades JPA)
- Repository (JPA)
- Service (Lógica de negócio)

## Executando localmente

1. Ajuste `src/main/resources/application.properties` com as credenciais do MariaDB.
2. Crie o banco de dados "empresa".
3. Rode a aplicação.
4. Acesse: `http://localhost:8080`

## Exemplos de requisições Postman:
GET:
![1.png](assets/1.png)

POST:
![2.png](assets/2.png)

PUT:
![3.png](assets/3.png)

GET BY ID:
![4.png](assets/4.png)

DELETE:
![5.png](assets/5.png)

GET em Funcionarios:
![6.png](assets/6.png)

GET BY ID em Funcionarios:
![7.png](assets/7.png)

POST em Funcionarios:
![8.png](assets/8.png)

PUT em Funcionarios:
![9.png](assets/9.png)

DELETE em Funcionarios:
![10.png](assets/10.png)

Resultado após deletar um funcionário:
![11.png](assets/11.png)

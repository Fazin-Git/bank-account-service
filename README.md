# Bank Account Transfer API
Simple Spring boot application which provide RESTful API for money transfer and check balance

### Prerequisite
- Maven
- JDK 1.8+
### Project Structure
```bash
MoneyTransferAPI
├── src
│   ├── main
│   │   ├── java\com\bank\accounts
│   │   └── resources
│   └── test
│       ├── java\com\bank\accounts
│       └── resources
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md
```
### Packaging
```
mvn package
```
### Test
```
mvn test
```
### Running
```
java -jar demo-0.0.1-SNAPSHOT.jar
```
### Data
Initial data (src\main\resources\data.sql) will be loaded in the H2 database when application start.
> INSERT INTO account (account_d,account_number, current_balance) VALUES
> (1,'124351628', 20000),
> (2,'927453628', 100);
## Feature
This application is for demo only. It provides APIs for following 2 features
- Transfer funds between accounts
- Check account balance
### Basic API Information
| Method | Path | Usage |
| --- | --- | --- |
| GET | api/v1/accounts/{id}/balances | retrieve account balance |
| POST | api/v1/transfer | transfer funds |
### Swagger-UI
API Specification is provided in the [swagger-ui page](http://localhost:8082/swagger-ui.html) after spring boot application start.
```
http://localhost:8082/swagger-ui.html
```
### Error Code
| Code | Description |
| --- | --- |
| ERR_SYS_001 | used when internal server error happens |
| ERR_SYS_002 | used when gateway timeout happens (e.g. calling external services) |
| ERR_CLIENT_001 | used when error due to client's input or business logic |
| ERR_CLIENT_002 | used when error related to account logic |
### Library used
| Library | Usage |
| --- | --- |
| spring boot | for spring boot application |
| spring data jpa | for ORM and DB operation purpose |
| H2 | lightweight database for demo purpose |
| springfox swagger | generate swagger API specification from code |
| springfox swagger ui | generate swagger ui page for specification |

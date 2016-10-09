## run the application with gretty plugin:
```$ ./gradle jettyRun```

## topics 
- gradle with integrationTest source set
- slf4j with logback as implementation, groovy configuration and re-routing of other logging frameworks to slf4j 
- REST api with spring-webmvc and secured by spring-security, spring java configuration
- hibernate as jpa implementation
- gradle jacoco plugin

## Database
- Expects a running postgre instance at localhost:5432 with databases kona,kona_test and users kona, kona_test respectively.

## Use cases
### Authentication
```bash
httpstat http://localhost:9999/kona/authenticate -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'
```
### Create a new Customer
```bash
httpstat http://localhost:9999/kona/customers -X POST -H "Content-Type: application/json" \
    -H "Authorization: AUTH_TOKEN" \
    -d '{"name": "Uusi asiakas"}'
```

### Look up customers by name
```bash
httpstat http://localhost:9999/kona/customers?name=jeppe -X GET \
    -H "Authorization: AUTH_TOKEN" 
```


### Database creation commands 
```sql
create user kona with encrypted password 'kona';
create database kona owner kona;

create user kona_test with encrypted password 'kona_test';
create database kona_test owner kona_test;
```


## run the application with gretty plugin:
```$ ./gradle jettyRun```

## topics 
- gradle with integrationTest source set
- slf4j with logback as implementation, groovy configuration and re-routing of other logging frameworks to slf4j 
- REST api with spring-webmvc and secured by spring-security, spring java configuration
- hibernate as jpa implementation
- gradle jacoco plugin

## Test plan
repository-layer
- data access code should be integration tested to verify configuration and SQL

model-layer
- domain entities and services should be thoroughly unit tested
- application services should be integration tested to test transactions and core functionality

web-layer
- spring webmvc configuration and rest interfaces should be integration tested with Spring MockMvc

Also there should be some e2e/smoke tests against the full application

## Use cases
### Authentication
```bash
$ curl -X POST \
	-H "Content-Type: application/json" \
	-d '{"username":"admin","password":"admin"}' \
	http://localhost:8080/kona/authenticate
```
### Create a new Customer
```bash
curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authentication: AUTH_TOKEN" \
    -d '{"name": "Uusi asiakas"}' \
    http://localhost:8080/kona/customers
```

### Look up customers by name
```bash
curl -X GET \
    -H "Authentication: AUTH_TOKEN" \
    http://localhost:8080/kona/customers?name=jeppe
```

## Todo
- JS web app secured by spring security
- better integration tests
- better business logic
- Use proper database
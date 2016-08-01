## run the application with gretty plugin:
```$ ./gradle jettyRun```

## topics 
- gradle with integrationTest source set
- slf4j with logback as implementation, groovy configuration and re-routing of other logging frameworks to slf4j 
- REST api with spring-webmvc and secured by spring-security, spring java configuration
- hibernate as jpa implementation
- gradle jacoco plugin

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
    -H "Authorization: AUTH_TOKEN" \
    http://localhost:8080/kona/customers?name=jeppe
```


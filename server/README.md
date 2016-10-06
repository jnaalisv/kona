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


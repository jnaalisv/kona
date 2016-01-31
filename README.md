# Demo of the following
- gradle with integrationTest source set
- slf4j with logback as implementation, groovy configuration and re-rerouting of other logging frameworks to slf4j 
- spring-webmvc and spring-security
- hibernate as jpa implementation

# Todo
- JS web app secured by spring security
- better integration tests

```bash

$ curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"admin"}' \
    http://localhost:8080/kona/authenticate
     
```
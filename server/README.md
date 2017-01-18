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
### Check that server CORS is configured correctly
```bash
$ curl -I -X OPTIONS http://localhost:9999/kona/authenticate -H "Origin: http://localhost:1234" -H "Access-Control-Request-Method: POST"
```
The response should be something like
```bash
HTTP/1.1 200 OK
Access-Control-Allow-Origin: http://localhost:1234
Vary: Origin
Access-Control-Allow-Methods: DELETE,GET,POST,PUT,HEAD
Access-Control-Allow-Credentials: true
Access-Control-Max-Age: 1800
Allow: GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH
```

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
    -H "Authorization: MTQ3ODA3NjI4MzEwMDplNzAyMmYxZTc2MjA3MGI5NjI2YzNhZmEzMTA3NDc3ZjEwMTk1NDYwN2ZiOWM5MDg4ZWNlMzE5NjhiYzkxYzZkOmFkbWluOjJhYjY4ZTMxMDU1YTY4MzU0NTFlZDAyMTM5NjExMjBhNzZjNzZkODA3MGE2MjM0ZTE3OWQzYzY4NWMyNTQzNGNkZDlhYjQ4Y2I4ZGI2ZTY0Njk2MjFlZWEyYjMzYzE0ZjM0MzQzZGU5NTNkNWExY2MzZTBkYzIzYWM3MDFjN2M1" 
```


### Database creation commands 
```sql
create user kona with encrypted password 'kona';
create database kona owner kona;

create user kona_test with encrypted password 'kona_test';
create database kona_test owner kona_test;
```


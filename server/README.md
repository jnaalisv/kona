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

## Test cases
### 1. ```/authenticate``` allows CORS preflight requests
```bash
curl -I -X OPTIONS http://localhost:9999/kona/authenticate -H "Origin: http://localhost:3000" -H "Access-Control-Request-Method: POST"
```
=>
```bash
HTTP/1.1 200 OK
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: POST
```

### 2. ```/products``` allows CORS preflight requests
```bash
curl -I -X OPTIONS http://localhost:9999/kona/products -H "Origin: http://localhost:3000" -H "Access-Control-Request-Method: GET" -H "Access-Control-Request-Headers: authorization"

```
=>
```bash
HTTP/1.1 200 OK
X-Content-Type-Options: nosniff
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET
Access-Control-Allow-Headers: authorization
```

### 3. ```/products``` is secured
```bash
curl -I -X GET http://localhost:9999/kona/products
```
=>
```bash
HTTP/1.1 401 Unauthorized: Authentication token was either missing or invalid.
WWW-Authenticate: Authorization
```

### 4. ```/authenticate``` returns a token
```bash
curl -X POST http://localhost:9999/kona/authenticate  -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'
```
=>
```bash
MTQ4NDcyODI0MDkwMzpmNzQ0MzhjOTExYmNmMTgxNmE0Y2Q0ZTRhODA3YWVjYjQxYjQzMDlkYWE5MDdlMWFjZmY0NjkxOTJkYjIxODMxOmFkbWluOmUwMTVjYjhhYWZiNmFlNDM2NDAzNWM3OGQxODIzMWQ5N2E4YTA3MDc4NzM1MjM0NzFlYmZiMWFmNWMwMmQ1NzAxZWJlYjE5MjQzZDg2MjQyNzk3YTc0YWZlY2Q1YzVkMmRmYTEwYzU5NDIwNDU2OGI0NDc0MTk4NTc1ZjdlMWRl
```

### 5. returned token allows access to ```/products```
```bash
curl -I -X GET http://localhost:9999/kona/products \
    -H "Authorization: MTQ4NDcyODI0MDkwMzpmNzQ0MzhjOTExYmNmMTgxNmE0Y2Q0ZTRhODA3YWVjYjQxYjQzMDlkYWE5MDdlMWFjZmY0NjkxOTJkYjIxODMxOmFkbWluOmUwMTVjYjhhYWZiNmFlNDM2NDAzNWM3OGQxODIzMWQ5N2E4YTA3MDc4NzM1MjM0NzFlYmZiMWFmNWMwMmQ1NzAxZWJlYjE5MjQzZDg2MjQyNzk3YTc0YWZlY2Q1YzVkMmRmYTEwYzU5NDIwNDU2OGI0NDc0MTk4NTc1ZjdlMWRl" 
```
=>
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
```
### 6. Create a new Customer
```bash
httpstat http://localhost:9999/kona/customers -X POST -H "Content-Type: application/json" \
    -H "Authorization: AUTH_TOKEN" \
    -d '{"name": "Uusi asiakas"}'
```
### 7. Look up customers by name
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


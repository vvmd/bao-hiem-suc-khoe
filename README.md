# Bảo hiểm sức khỏe (Health Insurance)
## Set up the local environment to build and run:
**1. SERVER:**
Add application.yml in resources
```bash
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fit_insurance
    username: ${your-username}
    password: ${your-password}
    driver-class-name: org.postgresql.Driver

application:
  security:
    jwt:
      secret-key: 404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
      access-token:
        expiration: 86400000 # a day
      refresh-token:
        expiration: 604800000 # 7 days

  cloudinary:
    cloud-name: dcen2xu9v
    api-key: 642984262323111
    secret-key: 82eRdF5lgYcR6bLdXV2FTtLRXWg
```
**2. CLIENT:**
Add .env in fit-insurance
```bash
VITE_SERVER_URL=http://127.0.0.1:8000
```

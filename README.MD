[![Build Status](https://travis-ci.org/CocktailsGuru/server.svg?branch=develop)](https://travis-ci.org/CocktailsGuru/server)
<br />

## CocktailsGuru backend

**Local installation guide**

First setup database. Name of the database should be "cocktail_dev".
- Initially import cocktail data from db dump
- Perform "flyway baseline" command on database (https://flywaydb.org/documentation/commandline/baseline)

Create file application-local.yml with content:
```
spring:
  datasource:
    url: \\URL to connect to DB\\
    username: \\DB user name\\
    password: \\DB user password\\


cocktails:
  resource:
    assets: \\Path to image assets\\
```
On Windows path to assets - "file:///C:/Path/...."<br>
On Unix path to assets - "file://C:/Path/...."


Inside "resources" folder create file "cocktail-security.yml" with content:
```
security:
  users:
    - name: master
      roles:
       - MODERATOR
      password: "{noop}\\moderatorPassword\\"
    - name: android
      roles:
        - MOBILE
      password: "{noop}\\androidPassword\\"
```


Start CocktailsGuruApplication
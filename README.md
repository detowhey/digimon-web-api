<img src= "https://i.pinimg.com/originals/ca/5b/a7/ca5ba7d121989a03a9e22518a3ccaab1.png" alt="Digimon logo, digital monsters" style="display: block; margin-left:auto; margin-right:auto;" width="50%" height="50%">

# Digimon Web API

Service that integrates with two REST APIs with Digimon data.
This service is **reactive**, using **Spring WebFlux**.

- [Digimon API](https://digimon-api.vercel.app/)
- [Digimon card](https://digimoncard.io/)

### Functionalities:

- Search digimon by name
- Search digimon by level
- Search cards from the digimon card game

### Technologies, frameworks and libraries used
- [Java 17](https://docs.oracle.com/en/java/javase/17/)
- [Gradle 7.0](https://docs.gradle.org/7.0/userguide/userguide.html)
- [Spring WebFlux 3.1.1](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [JUnit5](https://junit.org/junit5/docs/current/user-guide/), [Mockito](https://site.mockito.org/), [Datafaker](https://www.datafaker.net/)
- [Swagger (openapi v2.2.0)](https://springdoc.org/)

#### Application up (local)
```
gradle bootRun
```
### Documentation with Swagger (local)

http://localhost:8080/swagger-ui/index.html

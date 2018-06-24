[![Build Status](https://travis-ci.org/audit4j/audit4j-demo.svg?branch=master)](https://travis-ci.org/audit4j/audit4j-demo) 

# Audit4j-demo
Sample audit4j applications.

## Audit4j-demo-springboot

example taken from SpringBoot documentation https://spring.io/guides/gs/securing-web/

the login page is http://localhost:8080

The users passwords are user/password and user2/password2.

the following page http://localhost:8080/hello is audited.

## Audit4j-kotlin-demo-springboot
example showing the usage of audit4j with Kotlin "Petclinic" application

### Links and sources
this example is coming from the following github repositories
https://github.com/spring-petclinic/spring-petclinic-kotlin
https://github.com/spring-projects/spring-petclinic 

### Example with Audit4j and Kotlin language

Of course the annotation @Audit works fine.

#### AuditManager
```kotlin
AuditManager
val actor = MyApplicationContext.getAuthenticatedUser()
var manager = AuditManager.getInstance()
manager.audit(AuditEvent(actor, "myMethod", Field("myParam1Name", myParam1), Field("myParam2Name", myParam2)))
```
#### EventBuilder
```kotlin
val actor = MyApplicationContext.getAuthenticatedUser()
var builder = EventBuilder();
builder.addActor(actor).addAction("myMethod").addField("myParam1Name", myParam1).addField("myParam2Name", myParam2)
var manager = AuditManager.getInstance()
manager.audit(builder.build())
```




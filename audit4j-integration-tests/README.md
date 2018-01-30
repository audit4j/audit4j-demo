# Introduction integration tests 
Set of integration tests writen using JGiven framework (Behaviour Driven Development)
JGiven web site : http://jgiven.org/

launch as junit tests

the text of the tests using the syntax Given/When/Then are in the log

# Tests

## Test Class: org.audit4j.validation.jgiven.CombinedConsoleAndFileAuditHandlerJGivenTest

 Combined console and file audit handler with programming configuration receiving a message with audit event

```
   Given audit4j starting with a programming configuration using ConsoleAuditHandler and FileAuditHandler
    When a message is sent to the audit manager with audit event
     And audit manager is stopped
    Then the console log contains the audit4j logo with version "v2.5.0"
     And the console log contains the expected output for audit event
     And the file log contains 1 line
     And the file log contains the expected output for audit event
```

```
 Combined console and file audit handler with programming configuration receiving a message with event builder

   Given audit4j starting with a programming configuration using ConsoleAuditHandler and FileAuditHandler
    When a message is sent to the audit manager with event builder
     And audit manager is stopped
    Then the console log contains the audit4j logo with version "v2.5.0"
     And the console log contains the expected output for event builder
     And the file log contains 1 line
     And the file log contains the expected output for event builder
```

```
 Combined console and file audit handler with programming configuration receiving a message with event builder with pause

   Given audit4j starting with a programming configuration using ConsoleAuditHandler and FileAuditHandler
    When audit manager is disabled
     And a message is sent to the audit manager with event builder
     And audit manager is stopped
    Then the console log contains the audit4j logo with version "v2.5.0"
     But the console log does not contains messages for event builder
     And the file log contains 0 line
```


# TL;DR Kotlin delegated properties
Let’s say you have an [Entity](https://github.com/Vergil333/delegated-dto-demo/blob/master/src/main/kotlin/com/martinmachava/delegationtutorial/entities.kt#L11):

![1_WCpNS2pYdSMvro9eOW9xhg](https://user-images.githubusercontent.com/569066/157916373-820ada10-6cd5-4d67-bc12-397427e84e8c.png)

You need to create a DTO for some reason. You probably do it [like this](https://github.com/Vergil333/delegated-dto-demo/blob/master/src/main/kotlin/com/martinmachava/delegationtutorial/dtos.kt#L4):

![1_nDGVxuBf6LsAFAaCUOrkXQ](https://user-images.githubusercontent.com/569066/157916448-63fb1655-dd56-42fb-a7b5-2021ec090292.png)

**Constructor here serves as a mapper**. It maps Entity properties to DTO properties.
**Types of** DTO **properties are resolved from its Entity**. That’s good, we do not want to duplicate those types in DTO.
But you can also write [**DTO with delegated properties**](https://github.com/Vergil333/delegated-dto-demo/blob/master/src/main/kotlin/com/martinmachava/delegationtutorial/dtos.kt#L13):

![1_N__7AJ253xNeeDNxsYXG9A](https://user-images.githubusercontent.com/569066/157918973-719035ce-57d0-4164-a5df-da44321ba29c.png)

This looks almost the same as the first DTO. So **what’s the difference**?
- **delegated property is not ”real”** property of it’s class
- instead its **getter and setter is delegated to Entity** class
- when **Entity property is changed, that change is reflected in DTO** delegated property, even when DTO was instantiated before the change
- when DTO delegated property is changed, the change is reflected in Entity
- when we **do not want to modify** delegated property, we **set it to val** instead of var.

Let’s see it in [test example](https://github.com/Vergil333/delegated-dto-demo/blob/master/src/test/kotlin/com/martinmachava/delegationtutorial/unit/MediumDemoTest.kt#L11):

![1_Kw1Ig7yTktT4OmGFZTGuDw](https://user-images.githubusercontent.com/569066/157918719-4b593bca-a864-4b31-9958-75571bf72cc2.png)

> If you know a good example where to use delegated properties in practice, please, do let me know in the [comment of my Medium article](https://medium.com/@vergil333/tl-dr-kotlin-delegated-properties-5137ba50cf64).

For more examples, **unit tests** and **integration tests with Hibernate** functionality where delegation “extends” the arms of Hibernate’s Entity, [see my tests](https://github.com/Vergil333/delegated-dto-demo/tree/master/src/test/kotlin/com/martinmachava/delegationtutorial).

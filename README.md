# Spring Data Mongodb Sample #1

## Bug description

When using the `Flux<S> saveAll(Publisher<S> entityStream)` method from
the `ReactiveCrudRepository` class, the returned entities do not have their id property
populated, in the case where the field annotated with `@Id` class is immutable.

The bug only affects the version of `saveAll` that accepts a `Publisher`.
The version of `saveAll` that accepts an `Iterable` is not affected by this bug.

The bug was reproduced in the following Spring Boot / Spring data versions:
- spring-boot-starter-parent `2.4.4`, spring-data-mongodb `3.1.6`, spring-data-commons `2.4.6`
- spring-boot-starter-parent `2.3.9`, spring-data-mongodb `3.0.7`, spring-data-commons `2.3.7`
- spring-boot-starter-parent `2.2.13`, spring-data-mongodb `2.2.12`, spring-data-commons `2.2.12`

## Steps to reproduce
2. Run the unit tests  with `mvn test`
3. See that the `ImmutableEntityTest#test_saveAllFlux_deserializesWellEntity` test fails although it shouldn't
4. See that all the other unit tests are passing

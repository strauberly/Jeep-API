# Jeep API

---

I am continuing to improve my skills in creating RESTFUL API's and so 
I am taking on this project created by Promineo Tech.

They have been kind enough to provide the instruction and starter
files, and I am very excited to combine what I learn here with other projects I have created.

This file will serve as my Dev journal until 
I begin my documentation.

### Thank you for checking out what I am working on!

---

-13 July 2022-

- Now that imgae object has been created, have begun work on saving 
image object to db.
---

-9 July 2022-

- Created new test that doenst use tomcat server and configured mock mvc object, create mock multipart file object to hold image, 
then sent to controller with PK number of jeep.
- Test was successful indicating file was found.
- Focus is now on writing and reading image from database.

---

-18 May 2022-

- Discovered that in pulling methods to separate classes, errors were being caused due to
duplicate methods returning a default null value instead of the intended method returning a built object.
- Also located a few typos.
- Application is now functioning in intended manner and development continues.

---

-14 May 2022-

- Created model result set extractors.
- Made a means for adding on calculated price to order object.
- Assert that objects were missing their dependency and so was finally able to track it down and now the test can run.
- Test is currently returning 500 internal server error.
- Will circle back through lessons now that distraction of why .hasSize() was throwing a fit when nothing else was.

---

-2 May 2022-

- Created a new integration test
- Created Order JSON Object
- Create req and resp objects
- Test validated with 201 response

---

-1 May 2022-

- Created parameterized test checking for length of input,
validation for character type and object type.
- Turned on bean validation.
- added validation error handlers to the global error handler.
- Intro to mocking w Mockito.
- added test for unplanned error. Unplanned errors print stack trace.
- created inner test classes w @Nested allows for unpolluted bean registry.
- Completed all four outcomes for a request through controller.
- First operation completed.
---

---
-30 Apr 2022-

- Created Global Error Handler.
- Created DOA layer.
- Implemented a comparator for testing valid input in the trim selection,
 as well as creating a test that will return error message.

---

-24 Apr 2022-

- Discussed spring mapping based on component scan and implemented logger with Lombok @Slf4j as
an info logger.
Intending to research more for connections
to l4J.

- Implemented debug logger.

- Altered test to expect a list of jeep objects which it currently fails as expected.
- Created yaml file for debug configuration
- Discussed need for arg constructors when implementing spring builder
- Discussed dependency injection and inversion of control
- Discussed interface driven development and benefits.
- Talked about definitions of controller , service and DAO layers
- How @Autowired is needed for injection and that it requires a bean or managed object instance
- Discussed DAO a bit more JDBC, connection pools and transaction managers.
- Added mysql driver and jdbc driver to pom and added to our yaml configuration file.




---

-18 Apr 2022-

Implemented API documentation with Swagger. Created controllers and mapped them to 
end point.
---
-15 Apr 2022-

Set up test environment for http requests, and discussed 
behavior driven development and test driven development. Discussed testing cycle
of black, red, green, concepts. Began testing queries.
---
-14 Apr 2022-

Began building out the project utilizing the provided instructions and starter files.
Looking forward to it!

===
===



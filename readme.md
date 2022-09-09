# API Test framework & simple usage example for https://gorest.co.in/

The project contains custom-built API Testing Framework for gorest.co.in website. It also contains some automation scripts written with TestNG and Spring framework.
There is also a very simple cucumber integration examples.

## Used frameworks/tools
<ul>
<li>Java</li>
<li>TestNG</li>
<li>Cucumber</li>
<li>Apache HttpClient: https://hc.apache.org/httpcomponents-client-5.1.x/</li>
<li>Jackson: https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core</li>
<li>Spring Context: for cross-module dependency injection</li>
<li>Spring Data JPA: for filling and reading test data from Persistent Database</li>
<li>H2 Database: this is a handy solution to make it easy run the tests and run the project from scratch</li>
</ul>

## Project Structure
There are 3 modules for the framework and 1 for automation scripts. Automation module utilizes framework in order to execute queries to the API.
<br>

Framework modules:
<ul>
<li>gorest-test-framework-core</li>
<li>gorest-test-framework-api</li>
<li>gorest-test-framework-validation</li>
</ul>

Automation test modules:
<ul>
<li>gorest-test-automation</li>
</ul>


### - gorest-test-framework-core
Currently, this module contains small pojo classes that are used to talk to the API.
There are classes for each type of resource (e.g. User, Post) and some helper classes.
In a real-world scenarios this could contain tools for manipulating those resource objects
and other classes that are the core part of the API itself.

### - gorest-test-framework-api
This module is responsible for performing the HTTP calls to the gorest.co.in server.
It gives greater flexibility to properly test the endpoints and in the same time facilitates 
the interaction with API from Java code.<br>
The main classes, that tests use from this module are the Service classes. Each API
component (e.g. User, Comment...) has its own Service class, which tests should use in order
to create, update, fetch or delete a resource.

### - gorest-test-framework-validator
This is a tiny module for now. It contains custom assertion classes for the API resources.
For now, it contains very few classes that are not crafted very carefully, because of 
time constraints. But in real-world scenarios, this may contain complex classes that 
greatly facilitate validation process of API responses.

### - gorest-test-automation
This module utilises all the modules above to execute test cases and scenarios. It has its
own database for test data and uses TestNG in order to run the tests.
<br>
Currently H2 database is used, which is an in-memory database. This is done
to make it easy to build and run the tests from scratch. What is required from
the user, to run the tests, is only run `mvn install` command, which will build
and run all the tests. Also, individual tests can be run by TestNG, either via command line
or from an IDE (e.g. IntelliJ IDEA)
<br>
Also there is a very tiny cucumber integration with only a single Scenario, 
to showcase that Spring works with it very well. Also, it shows that
the gorest-test-framework gives greater flexibility to implement and run different 
cucumber test scenarios.
# payconiq-stock

[![Build Status](https://api.travis-ci.org/luanmalaguti0/payconiq-stock.svg)](https://travis-ci.com/luanmalaguti0/payconiq-stock) 
[![Issues](https://img.shields.io/github/issues/luanmalaguti0/payconiq-stock.svg)](https://github.com/luanmalaguti0/payconiq-stock/issues?q=is%3Aopen) 
[![Java](https://img.shields.io/badge/java-%3E%3D%20v1.8-orange)](https://openjdk.java.net/install/)

Java based backend application that implements REST endpoints to handle a simple CRUD of Stocks

## Installation
    $ git clone https://github.com/luanmalaguti0/payconiq-stock.git
    $ cd payconiq-stock
    $ mvn clean install
    $ ./mvnw spring-boot:run
The application will be running by default on [http://localhost:8080](http://localhost:8080)
    
## API spec
    GET /api/stocks 
    GET /api/stocks/{id}
    POST /api/stocks
    PUT /api/stocks/{id}

## Stack
* Java 1.8
* Maven
* Travis CI
* Spring Boot 2
* Lombok
* AspectJ

## Testing
* JUnit 5, 
* AssertJ
* Mockito

## Development Strategy

I have followed the Git Flow that allows parallel development very easy, by isolating new development from finished work.
Instead of a single master branch, this workflow uses two branches to record the history of the project. The `master` branch stores the official release history, and the `develop` branch serves as an integration branch for features. 
 ![Git Flow](https://miro.medium.com/max/823/1*uUpzVOpdFw5V-tJ_YvgFmA.png)

I have used the [Issues](https://github.com/luanmalaguti0/payconiq-stock/issues?utf8=%E2%9C%93&q=is%3Aissue) from GitHub to break the tasks into small features. 

Each feature was created as a different `branch` (from develop). As a result every issue/feature has a  branch and a Pull Request opened against `develop`

Once the code is considered good to be released, a `release` branch is created from `develop`. It will get merged into `master` and back to `develop`, then the release branch will be deleted.

**I have left opened the Pull Requests from the release branches into Master to be reviewed by Payconiq**

### Future improvements
* Add Swagger to offer OpenAPI Specification 
* Upgrade to Java 11
* Add Integration tests - for now only unit tests were implemented
* Add a security layer such as Spring security
* Create deployment pipelines to build and deploy the artifact into different environments




    
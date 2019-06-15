# jsonrpc-bind

[![Build Status](https://travis-ci.org/jinahya/jsonrpc-bind.svg?branch=develop)](https://travis-ci.org/jinahya/jsonrpc-bind)
[![CircleCI](https://circleci.com/gh/jinahya/jsonrpc-bind/tree/develop.svg?style=svg)](https://circleci.com/gh/jinahya/jsonrpc-bind/tree/develop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.jinahya%3Ajsonrpc-bind%3Adevelop&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.jinahya%3Ajsonrpc-bind%3Adevelop)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/jsonrpc-bind/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/jinahya/jsonrpc-bind?targetFile=pom.xml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/jsonrpc-bind.svg)](https://search.maven.org/artifact/com.github.jinahya/jsonrpc-bind)
[![Javadocs](https://javadoc.io/badge/com.github.jinahya/jsonrpc-bind.svg?label=javadoc)](https://javadoc.io/doc/com.github.jinahya/jsonrpc-bind)

Classes for binding [JSON-RPC](https://www.jsonrpc.org) objects (with no external dependencies).

## Abstract

## Classes

### `JsonrpcObject<IdType>`

#### Why `<IdType>`?

### `RequestObject<IdType, ParamsType>`

```java
class AdditionParams {
    @Setter @Getter private BigDecimal augend;
    @Setter @Getter private BigDecimal addend;
}
```
```java
class AdditionRequest extends RequestObject<String, AdditionParams> {
}
```
```java
final AdditionRequest request = new AdditionRequest();
request.setParams(new AdditionParams());
request.getParams().setAugend(BigDecimal.ONE);
request.getParams().setAddend(BigDecimal.ONE);
```

### `ResponseObject<IdType, ResultType, ErrorType extends ErrorObject<?>>`

```java
public class SubtractionResponse
        extends ResponseObject<String, BigDecimal, CaluclationError> {
} 
```
```java
final SubtractionResponse response = new SubtractionResponse();
final CalulationError error = new CalculationError();
error.setData(new AtithmeticException());
response.setErrorExclusive(error);
```

#### `ErrorObject<DataType>`

```java
public class CalculationError extends ErrorObject<ArithmeticException> {
    
}
```

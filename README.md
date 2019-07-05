# jsonrpc-bind

[![Build Status](https://travis-ci.org/jinahya/jsonrpc-bind.svg?branch=develop)](https://travis-ci.org/jinahya/jsonrpc-bind)
[![CircleCI](https://circleci.com/gh/jinahya/jsonrpc-bind/tree/develop.svg?style=svg)](https://circleci.com/gh/jinahya/jsonrpc-bind/tree/develop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.jinahya%3Ajsonrpc-bind%3Adevelop&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.jinahya%3Ajsonrpc-bind%3Adevelop)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/jsonrpc-bind/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/jinahya/jsonrpc-bind?targetFile=pom.xml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/jsonrpc-bind.svg)](https://search.maven.org/artifact/com.github.jinahya/jsonrpc-bind)
[![Javadocs](https://javadoc.io/badge/com.github.jinahya/jsonrpc-bind.svg?label=javadoc)](https://javadoc.io/doc/com.github.jinahya/jsonrpc-bind)

Classes for binding [JSON-RPC](https://www.jsonrpc.org) objects (with no external dependencies).

## Abstract

## 1.0

## 2.0

### `JsonrpcObject<IdType>`
An abstract class for both request objects and response objects.

#### `<IdType>`
The type parameter for `$.id`.

### `RequestObject<IdType, ParamsType>`

#### `<ParamsType>`
The type parameter for `$.params`.

```java
class AdditionParams {
    private BigDecimal augend;
    private BigDecimal addend;
}
```
```java
class AdditionRequest extends RequestObject<AdditionParams, String> {
}
```
```java
final AdditionRequest request = new AdditionRequest();
request.setMethod("add");
request.setParams(new AdditionParams());
request.getParams().setAugend(BigDecimal.ONE);
request.getParams().setAddend(BigDecimal.ONE);
request.setId("1");
```

### `ResponseObject<ResultType, ErrorType extends ErrorObject<?>, IdType>`

#### `<ResultType>`
The type parameter for `$.result`.

#### `<ErrorType>`
The type parameter for `$.error`.

```java
class AdditionResponse
        extends ResponseObject<BigDecimal, CalculationError, String> {
} 
```
```java
ResponseObject<AdditionResult, AdditionError, String> response = new AdditionResponse();

response.setCode(20000);
response.setMessage("Ok");
response.setResult(BigDecimal.ONE.add(BigDecimal.ONE));

response.setCode(40000);
response.setMessage("bad request");
ErrorObject<ArithmeticException> error = new CalculationError();
response.setErrorExclusive(error); // clear the result
response.getError().setData(new AtithmeticException());
```

#### `ErrorObject<DataType>`

##### `<DataType>`
The type parameter for `$.error.data`.

```java
class CalculationError extends ResponseObject.ErrorObject<ArithmeticException> {
    
}
```

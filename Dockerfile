FROM maven:latest

WORKDIR /

ADD pom.xml .
RUN mvn dependency:go-offline

COPY src .
RUN mvn package

FROM ubuntu
FROM openjdk:8-jre-slim
FROM maven:3.5-jdk-8-slim
FROM browserless/chrome

  
#creating Directory

RUN mkdir -p /usr/src/DEMOQA
WORKDIR /usr/src/DEMOQA
COPY . /usr/src/DEMOQA
#copying dependencies and environment variables

RUN mvn clean
CMD ["mvn","install"]
FROM openjdk:8u171-jdk-alpine3.8

LABEL maintainer="tomjavastdy@gmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD build/libs/beerstore-0.0.1-SNAPSHOT.jar /app/app.jar

CMD java -jar /app/app.jar $APP_OPTIONS

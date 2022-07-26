#!/bin/sh

cd movies 
./mvnw clean package
cd -
cd movies-payment-service/
./mvnw clean package
cd -
cd movies-email-service/
./mvnw clean package
cd -

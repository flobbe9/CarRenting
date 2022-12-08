# CarRenting
Practice project simulating a car renting company's website. <br />
A simple rest api is used with spring-boot and express.js, and MySQL as database.

## Features
A user can add, update, get and delete cars. 5 are already in the database.

## How to use with Docker
To use CarRenting with Docker, pull the following images from dockerhub: <br />
- `docker pull mysql` <br />
- `docker pull flobbe10/car_renting-spring_boot` <br />
- `docker pull flobbe10/car_renting-express` <br />

After that, copy the docker-compose.yml at the project root and `docker-compose up` will do the rest for you.

### Try requests with Postman
https://www.postman.com/grey-shuttle-863417/workspace/carrenting/collection/23751197-cb6b7126-01ae-45d5-b80f-ced5a47b45c5?action=share&creator=23751197
# CarRenting
Practice project simulating a car renting company's website. <br />
A simple rest api is used with spring-boot and express.js, and MySQL as database.

## Features
A user can add, update, get and delete cars. 5 are already in the database.

### Try these immediatly (no further params needed, all get requests)
http://localhost:4001/car/getAll

http://localhost:4001/car/getAllByBrandAndModel?brand=Volkswagen&model=Caddy

http://localhost:4001/car/getAllByIsAvailable?isAvailable=true

http://localhost:4001/car/getAllByFuelType?fuelType=benzine

http://localhost:4001/car/getAllByColor?color=red

http://localhost:4001/car/existsByModel?model=Caddy

http://localhost:4001/car/existsByModel?model=Caddy

### Add (postman)
http://localhost:4001/car/saveCar <br />
Note: 
- expecting a request body with a car <br />
- method: post

### Update (postman)
http://localhost:4001/car/update <br />
Note: 
- expecting a request body with a car but attributes may be null or missing completely <br />
- expecting request parameter 'id' with the id of the car to update <br />
- method: put 

### Get (postman)
http://localhost:4001/car/getCar <br />
Note: 
- expecting a request body with a specification <br />
- expecting request parameters 'brand', 'model', 'color' and 'fuelType' <br />
- method: post

### Delete (postman)
http://localhost:4001/car/delete <br />
Note: <br />
- expecting a request body with a <br />
- method: delete

## How to use with Docker
To use CarRenting with Docker, pull the following images from dockerhub: <br />
- `docker pull mysql` <br />
- `docker pull flobbe10/car_renting-spring_boot` <br />
- `docker pull flobbe10/car_renting-express` <br />

After that, copy the docker-compose.yml at the project root and `docker-compose up` will do the rest for you.
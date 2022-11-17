# CarRenting
Practice project simulating a car renting company's website. 
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
http://localhost:4001/car/saveCar 
    Note: - expecting a request body with a car
          - method: post

### Update (postman)
http://localhost:4001/car/update
    Note: - expecting a request body with a car but attributes may be null or missing completely
          - expecting request parameter 'id' with the id of the car to update
          - method: put

### Get (postman)
http://localhost:4001/car/getCar
    Note: - expecting a request body with a specification
          - expecting request parameters 'brand', 'model', 'color' and 'fuelType'
          - method: post

### Delete (postman)
http://localhost:4001/car/delete
    Note: - expecting a request body with a car

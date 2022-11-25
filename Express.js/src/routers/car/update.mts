import { Car } from "../../interfaces/car.mjs";
import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";
import { redirect } from "./redirect.mjs";


/**
 * Expects object with car properties, gets the old version of the car and
 * updates it with the new properties. Then makes post request to spring in order
 * to save the new car.
 * 
 * @param req 
 * @param res 
 * @param next 
 * @returns response of redirect() request or false if one check failed.
 */
async function update(req, res, next) {

    const id: number = req.query.id;
    const newCarAttributes = req.body;
    
    // case: id not a number
    if (isNaN(id)) {
        res.send("Failed to update car. Car id is not a number.");
        return false;
    }
    
    // get old car
    const oldCar = await getOldCar(id);

    // case: request unsuccessful
    if (!oldCar) {
        res.send(`Failed to update car. Could not get car with id ${id}.`);
        return false;
    }

    // update car
    const updatedCar: (Car | boolean) = updateCar(oldCar, newCarAttributes);

    // case: failed to update
    if (!updatedCar) {
        res.send("Failed to update car. Invalid new car attribute.");
        return false;
    }

    // make request to spring
    req.body = updatedCar;
    req.method = "post";
    req.originalUrl = "/car/saveCar";
    return redirect(req, res, next);
}


///// hlper methods:


async function getOldCar(id: number) {

    // get response with either oldCar from db or a status code
    const oldCar = await getById(id);

    // case: request unsuccessful, got http status code instead
    if (typeof oldCar === "number") 
        return false;

    return oldCar;
}


async function getById(id: number) {

    const fetchHeaders: FetchHeader = {
        method: "get",
        headers: {
            "Content-Type": "application/json"
        }
    }
    
    const response = await makeRequest(springDomain + `/car/getById?id=${id}`, fetchHeaders);

    return response;
}


/**
 * Iterates obj with new attributes and uses them as replacement inside the old car. The newAttributes obj
 * only has to use the correct key names for a car, but some keys may be missing and values may be null.
 * Checks every attribute with {@link objectValid()} and {@link carValid()} before replacing it. 
 * Sends response if one check fails.
 * 
 * @param oldCar car to be updated.
 * @param newCarAttributes car object with new attributes. May be incomplete or contain null values.
 * @param res response callback from router.
 * @return the updated car or undefined if one of the new attributes is invalid.
 */
function updateCar(oldCar, newCarAttributes): Car | boolean {

    let valid = true;

    Object.entries(newCarAttributes).forEach(([key, value]) => {
        if (value !== null && value !== undefined) {

            // if value is another object, make recursion
            if (typeof value === "object") {
                const updatedObj = updateCar(oldCar[key], value);
                if (!updatedObj) {
                    valid = false;
                    return;
                }

                value = updatedObj;
            }
            
            // case: wrong attribute type
            if (typeof value !== typeof oldCar[key]) {
                valid = false;
                return;
            }

            oldCar[key] = value;
        }
    });

    // return false if one check has failed or else the updated car
    return (valid) ? oldCar : false;
}


export { update };
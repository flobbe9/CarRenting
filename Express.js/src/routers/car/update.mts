import { Car } from "../../interfaces/car.mjs";
import { isObjectEmpty, objectValid } from "../../logic/objectValid.mjs";
import { saveCar } from "./saveCar.mjs";
import { carValid } from "../../logic/carValid.mjs";
import { getAllBy } from "./getAllBy.mjs";
import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";


/**
 * Expects object with car properties
 * @param req 
 * @param res 
 * @param next 
 * @returns response of savCar() request or false if one check failed.
 */
async function update(req, res, next) {

    const id: number = req.query.id;
    const newCarAttributes = req.body;
    
    // case: id not a number or no new attributes found
    if (!isUpdateInputValid(id, newCarAttributes)) {
        res.send("Failed to update car. Something wrong with request body or queries.");
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
    return saveCar(req, res, next);
}


///// hlper methods:


function isUpdateInputValid(id, newCarAttributes): boolean {

    // case: id from query is not a number
    if (isNaN(id)) 
        return false;
    
    // case: request body empty
    if (isObjectEmpty(newCarAttributes)) 
        return false;

    return true;
}


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

            // case: value is another object
            if (typeof value === "object") {
                const updatedObj = updateCar(oldCar[key], value);
                if (!updatedObj) {
                    valid = false;
                    return;
                }

                value = updatedObj;
            }
            
            // case: attribute invalid
            if (!objectValid(value)) {
                valid = false;
                return;
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
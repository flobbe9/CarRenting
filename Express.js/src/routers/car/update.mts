import { getById } from "./getById.mjs";
import { Car } from "../../interfaces/car.mjs";
import { isObjectEmpty, objectValid } from "../../logic/objectValid.mjs";
import { saveCar } from "./saveCar.mjs";
import { carValid } from "../../logic/carValid.mjs";


async function update(req, res, next) {

    const id = req.query.id;
    const newCarAttributes = req.body;
    
    // case: id from query is not a number
    if (isNaN(id)) {
        res.send(`Car id '${id}' is not a number.`);
        return;
    }
    
    // case: request body empty
    if (isObjectEmpty(newCarAttributes)) {
        res.send("Failed to update car. Request body is not a car object.");
        return;
    }
    
    // getting Car with old attributes from db
    const oldCar = await getById(id);
    
    // request unsuccessful, got http status code
    if (typeof oldCar === "number") {
        res.sendStatus(oldCar);
        return;
    }

    // update car
    const updatedCar: Car = updateCar(oldCar, newCarAttributes, res);

    // case: updateCar() returned undefined because one car attribute is invalid
    if (updatedCar === undefined) return;

    // make saveCar request to spring
    req.body = updatedCar;
    return saveCar(req, res, next);
}


///// hlper methods:


/**
 * Iterates car with new attributes and uses them as replacement for the old car. Checks
 * every attribute with {@link objectValid()} and {@link carValid()} before replacing it.
 * 
 * @param oldCar car to be updated.
 * @param newCarAttributes car object with new attributes. May contain null values.
 * @param res response callback from router.
 */
function updateCar(oldCar, newCarAttributes: Car, res) {

    let objValid = true;

    Object.entries(newCarAttributes).forEach(([key, value]) => {
        if (value !== null && value !== undefined) {

            // case: attribute invalid
            if (!objectValid(value)) {
                objValid = false;
                return;
            }

            // case: wrong attribute type
            if (typeof value !== typeof oldCar[key]){
                objValid = false;
                return;
            }

            oldCar[key] = value;
        }
    });

    // case: one attribute was invalid
    if (!objValid) {
        res.send("Failed to update car. Invalid new attribute.");
        return;
    }

    return oldCar;
}


export { update };
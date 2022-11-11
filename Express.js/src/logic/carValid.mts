import { Car } from "../interfaces/car.mjs";
import { specificationValid } from "./specificationValid.mjs";


/**
 * Checks attributes of Car object.
 * 
 * @param car to check.
 * @returns true if all checks were successful.
 */
function carValid(car: Car): boolean {

    if (specificationValid(car.specification)) 
        return false;

    return true;
}


export { carValid };